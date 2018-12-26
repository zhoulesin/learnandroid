package com.zhoulesin.retrofitdemo.bean.poetry;

/**
 * Created by zhoul on 2018/9/30.
 */

public class AncientPortry2 {
    //{"title":"受代还眉","content":"久客念吾土，毎叹江山非。|及兹受代去，亦复无家归。|仕宦几半世，奔走遍九围。|竟无置锥土，安此容膝扉。|还当僦屋居，况有囊金挥。|处乡亦何有，去国空依依。","authors":"唐庚"}
    private String title;
    private String content;
    private String authors;

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public AncientPortry2(String title, String content, String authors) {

        this.title = title;
        this.content = content;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AncientPortry2(String title) {

        this.title = title;
    }
}
