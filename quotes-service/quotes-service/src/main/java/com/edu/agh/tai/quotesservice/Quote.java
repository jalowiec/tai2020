package com.edu.agh.tai.quotesservice;

import java.util.List;


public class Quote {
    private String content;
    private String lang;
    private String author;
    private List<Tag> tagList;

    public Quote(String content, String lang, String author, List<Tag> tagList) {
        this.content = content;
        this.lang = lang;
        this.author = author;
        this.tagList = tagList;
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

    public List<Tag> getTagList() {
        return tagList;
    }
}
