package models;

public class Book {
    private String id;
    private String author;
    private String title;
    private String genre;
    private String price;
    private String publish_date;
    private String description;
    private boolean authorFull = false;
    private boolean titleFull = false;
    private boolean genreFull = false;
    private boolean priceFull = false;
    private boolean publish_dateFull = false;
    private boolean descriptionFull = false;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getPrice() {
        return price;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        if(!authorFull) {
            authorFull = true;
            this.author = author;
        }
    }

    public void setTitle(String title) {
        if (!titleFull) {
            titleFull = true;
            this.title = title;
        }
    }

    public void setGenre(String genre) {
        if (!genreFull) {
            genreFull = true;
            this.genre = genre;
        }
    }

    public void setPrice(String price) {
        if (!priceFull) {
            priceFull = true;
            this.price = price;
        }
    }

    public void setPublish_date(String publish_date) {
        if (!publish_dateFull) {
            publish_dateFull = true;
            this.publish_date = publish_date;
        }
    }

    public void setDescription(String description) {
        if(!descriptionFull) {
            descriptionFull = true;
            this.description = description;
        }
    }
}