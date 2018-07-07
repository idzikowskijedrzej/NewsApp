package com.example.jedrzej.newspart1;

public class News {
    //String containing News title
    String title;
    //String containing News author
    String author;
    //String containing date of News publication
    String date;
    //String containing News category (ie. Sports / Lifestyle
    String category;
    //String containing News URL address
    String url;

    /**
     * Create a new News object.
     * Obligatory empty constructor.
     */
    public News(String title, String author, String url, String date, String category) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.category = category;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}