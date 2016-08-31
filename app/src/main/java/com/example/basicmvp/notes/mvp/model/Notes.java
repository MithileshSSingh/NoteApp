package com.example.basicmvp.notes.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mithilesh on 8/28/16.
 */
public class Notes implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    @SerializedName("date_created")
    private String dateCreated;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }
}
