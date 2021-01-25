package com.edu.agh.tai.quotesservice;


import java.util.List;

public class Quote {
    private String content;
    private String author;
    private String hobbies;

    public Quote() {
    }

    public Quote(String content, String author, String hobbies) {
        this.content = content;
        this.author = author;
        this.hobbies = hobbies;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getHobbies() {
        return hobbies;
    }
}
