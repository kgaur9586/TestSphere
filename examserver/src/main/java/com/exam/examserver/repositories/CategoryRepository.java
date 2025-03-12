package com.exam.examserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examserver.entities.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
