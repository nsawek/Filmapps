package com.example.snowa.filmapps.model;

public class ListaFilmowfull{
    private String title ;
    private String summary;
    private String rating;
    private String image_url;
    private String category;
    private String language;
    private int year;

    public ListaFilmowfull() {
    }

    public ListaFilmowfull(String title, String summary, String rating, String image_url, String category, String language, int year) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.image_url = image_url;
        this.category = category;
        this.language = language;
        this.year = year;
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

    public String getImage_url() {
        return image_url;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public int getYear() {
        return year;
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

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setYear(int year) {
        this.year = year;
    }
}