package com.exam.examserver.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "roles")
public class Role {
    @Id
    private String id;          // MongoDB’s ObjectId as String
    private String roleName;
}
