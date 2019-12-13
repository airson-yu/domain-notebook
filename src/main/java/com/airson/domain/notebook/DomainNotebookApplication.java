package com.airson.domain.notebook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.airson.domain.notebook.dao.mapper")
@SpringBootApplication
public class DomainNotebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomainNotebookApplication.class, args);
    }

}
