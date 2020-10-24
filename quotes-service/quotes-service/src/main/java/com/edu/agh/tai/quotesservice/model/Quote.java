package com.edu.agh.tai.quotesservice.model;
//TODO dodanie listy tagow
public class Quote {
    private String content;
    private String lang;
    private String author;

    public Quote(String content, String lang, String author) {
        this.content = content;
        this.lang = lang;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getLang() {
        return lang;
    }

    public String getAuthor() {
        return author;
    }
}
