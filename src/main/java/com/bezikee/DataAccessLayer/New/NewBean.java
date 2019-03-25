package com.bezikee.DataAccessLayer.New;

import java.sql.Date;
import java.sql.Timestamp;

public class NewBean {

    private int id;
    private String title;
    private String content;
    private String image;
    private String video;
    private String date;

    public NewBean(int id, String title, String content, String image, String video, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.video = video;
        this.date = date;
    }

    public NewBean(String title, String content, String image, String video, String date) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.video = video;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
