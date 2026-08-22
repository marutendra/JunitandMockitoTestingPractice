package com.Junit.Student.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "students")
@Data
@Builder

public class Student {

    @Id
    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String course;

    private Double marks;

}