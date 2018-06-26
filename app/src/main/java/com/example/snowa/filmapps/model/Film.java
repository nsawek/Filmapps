package com.example.snowa.filmapps.model;

public class Film {

    private String title;
    private String summary;
    private String rating;
    private int year;
    private String time;
    private String language;
    private String image_url;

    public Film() {
    }

    public Film(String title, String summary, String rating, int year, String time, String language, String image_url) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.year = year;
        this.time = time;
        this.language = language;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    public String getTime() {
        return time;
    }

    public String getLanguage() {
        return language;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}