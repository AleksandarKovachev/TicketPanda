package sofia.tu.panda.ticket.ticketpanda.Objects;

import java.io.Serializable;

public class Program implements Serializable{

    private Integer id;

    private String title;

    private String smallDescription;

    private String description;

    private Integer primaryImage;

    private Integer secondaryImage;

    private Integer thirdImage;

    private String author;

    private Integer price;

    private String actors;

    private String director;

    private String production;

    private Integer bought;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(Integer primaryImage) {
        this.primaryImage = primaryImage;
    }

    public Integer getSecondaryImage() {
        return secondaryImage;
    }

    public void setSecondaryImage(Integer secondaryImage) {
        this.secondaryImage = secondaryImage;
    }

    public Integer getThirdImage() {
        return thirdImage;
    }

    public void setThirdImage(Integer thirdImage) {
        this.thirdImage = thirdImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public Integer getBought() {
        return bought;
    }

    public void setBought(Integer bought) {
        this.bought = bought;
    }
}
