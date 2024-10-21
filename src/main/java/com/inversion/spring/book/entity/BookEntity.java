package com.inversion.spring.book.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookEntity {

    private Integer id;
    private String title;
    private String description;

    public BookEntity() {
    }

    public BookEntity(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
