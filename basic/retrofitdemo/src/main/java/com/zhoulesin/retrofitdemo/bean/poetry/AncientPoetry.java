package com.zhoulesin.retrofitdemo.bean.poetry;

/**
 * Created by zhoul on 2018/9/30.
 */

public class AncientPoetry {
    //{"author":"杜甫","origin":"石壕吏","category":"古诗文-人物-女子","content":"老翁逾墙走，老妇出门看。"}
    private String author;
    private String origin;
    private String category;
    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AncientPoetry(String author, String origin, String category, String content) {

        this.author = author;
        this.origin = origin;
        this.category = category;
        this.content = content;
    }

    public AncientPoetry() {

    }
}
