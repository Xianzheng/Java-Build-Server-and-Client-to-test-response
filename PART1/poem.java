/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.own;

/**
 *
 * @author markf
 */
public class poem {

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }
    String title;
    String id;
    String author;
    String content;
    public poem(String title,String id,String author,String content){
        this.title=title;
        this.id=id;
        this.author=author;
        this.content=content;
    }
}
