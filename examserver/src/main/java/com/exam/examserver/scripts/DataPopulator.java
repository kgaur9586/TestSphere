package com.exam.examserver.scripts;

import com.exam.examserver.entities.exam.Category;
import com.exam.examserver.entities.exam.Quiz;
import com.exam.examserver.entities.exam.Question;
import com.exam.examserver.services.CategoryService;
import com.exam.examserver.services.QuizService;
import com.exam.examserver.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DataPopulator implements CommandLineRunner {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private QuestionService questionService;

    private static final Logger logger = LoggerFactory.getLogger(DataPopulator.class);

    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String apiKey = "AIzaSyBpO1heRqTr2vhLQf7ESrJ_8_uXm1SRryc";
        String llmApiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="
                + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String prompt = "Generate 1 random category with 2 quizzes. For each quiz, generate 10 questions. Return the data in the following JSON format: { 'categories': [ { 'title': '', 'description': '', 'quizzes': [ { 'title': '', 'description': '', 'maxMarks': '', 'numberOfQuestions': 10, 'active': true, 'questions': [ { 'content': '', 'option1': '', 'option2': '', 'option3': '', 'option4': '', 'answer': '' } ] } ] } ] }";
        Map<String, Object> part = Map.of("text", prompt);
        Map<String, Object> content = Map.of("parts", List.of(part));
        Map<String, Object> body = Map.of("contents", List.of(content));
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(llmApiUrl, request, String.class);
        String llmResponse = response.getBody();
        logger.info("Received LLM response: {}", llmResponse);
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(llmResponse);
            JsonNode candidatesNode = root.get("candidates");
            if (candidatesNode != null && candidatesNode.isArray() && candidatesNode.size() > 0) {
                JsonNode contentNode = candidatesNode.get(0).get("content");
                if (contentNode != null && contentNode.has("parts")) {
                    JsonNode partsNode = contentNode.get("parts");
                    if (partsNode.isArray() && partsNode.size() > 0) {
                        String jsonText = partsNode.get(0).get("text").asText();
                        // Remove markdown code block if present
                        if (jsonText.startsWith("```json")) {
                            jsonText = jsonText.replaceFirst("```json", "").trim();
                        }
                        if (jsonText.endsWith("```")) {
                            jsonText = jsonText.substring(0, jsonText.length() - 3).trim();
                        }
                        logger.info("Extracted JSON text: {}", jsonText);
                        
                        // Robust extraction: only keep the part between the first '{' and the last '}'
                        int start = jsonText.indexOf('{');
                        int end = jsonText.lastIndexOf('}');
                        if (start != -1 && end != -1 && end > start) {
                            jsonText = jsonText.substring(start, end + 1);
                        } else {
                            logger.error("Malformed JSON: could not find valid object boundaries.");
                            return;
                        }
                        
                        logger.info("Trimmed JSON text for parsing: {}", jsonText);
                        if (!jsonText.trim().endsWith("}")) {
                            logger.error("LLM response JSON is incomplete or truncated. Skipping parsing.");
                            return;
                        }

                        JsonNode actualRoot = mapper.readTree(jsonText);
                        JsonNode categoriesNode = actualRoot.get("categories");
                        if (categoriesNode != null && categoriesNode.isArray()) {
                            for (JsonNode categoryNode : categoriesNode) {
                                Category category = new Category();
                                category.setTitle(categoryNode.path("title").asText());
                                category.setDescription(categoryNode.path("description").asText());
                                category = categoryService.addCategory(category);
                                logger.info("Saved category: {}", category);

                                JsonNode quizzesNode = categoryNode.get("quizzes");
                                if (quizzesNode != null && quizzesNode.isArray()) {
                                    for (JsonNode quizNode : quizzesNode) {
                                        Quiz quiz = new Quiz();
                                        quiz.setTitle(quizNode.path("title").asText());
                                        quiz.setDescription(quizNode.path("description").asText());
                                        quiz.setMaxMarks(quizNode.path("maxMarks").asText());
                                        quiz.setNumberOfQuestions(quizNode.path("numberOfQuestions").asText());
                                        quiz.setActive(quizNode.path("active").asBoolean());
                                        quiz.setCategoryId(category.getId());
                                        quiz = quizService.addQuiz(quiz);
                                        logger.info("Saved quiz: {}", quiz);

                                        JsonNode questionsNode = quizNode.get("questions");
                                        if (questionsNode != null && questionsNode.isArray()) {
                                            for (JsonNode questionNode : questionsNode) {
                                                Question question = new Question();
                                                question.setQuizId(quiz.getId());
                                                question.setContent(questionNode.path("content").asText());
                                                question.setOption1(questionNode.path("option1").asText());
                                                question.setOption2(questionNode.path("option2").asText());
                                                question.setOption3(questionNode.path("option3").asText());
                                                question.setOption4(questionNode.path("option4").asText());
                                                question.setAnswer(questionNode.path("answer").asText());
                                                questionService.addQuestion(question);
                                                logger.info("Saved question: {}", question);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            logger.warn("No categories found in extracted JSON.");
                        }
                    } else {
                        logger.warn("No parts found in Gemini response.");
                    }
                } else {
                    logger.warn("No content found in Gemini response.");
                }
            } else {
                logger.warn("No candidates found in Gemini response.");
            }
        } catch (Exception e) {
            logger.error("Error processing LLM response: {}", e.getMessage());
            e.printStackTrace();
        }
        
        logger.info("LLM-generated categories, quizzes, and questions populated!");
    }
}