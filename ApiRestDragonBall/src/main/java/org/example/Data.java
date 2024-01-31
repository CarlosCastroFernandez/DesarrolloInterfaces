package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data implements Serializable {

    private String imdb_id;
    private String title;
    public Data(){

    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + imdb_id + '\'' +
                ", titulo='" + title + '\'' +
                '}';
    }
}
