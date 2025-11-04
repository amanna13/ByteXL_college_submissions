package com.example.parta;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PartAApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Student s = ctx.getBean(Student.class);
        s.introduce();
        ctx.close();
    }
}
