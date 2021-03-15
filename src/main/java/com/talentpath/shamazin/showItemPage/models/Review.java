package com.talentpath.shamazin.showItemPage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Reviews")
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @NotBlank
    private ItemFamily itemFamily;

    private String title;

    private String content;

    private Integer score;

    private Integer starValue;

    @OneToMany(mappedBy="review", cascade=CascadeType.ALL)
    private List<ReviewPhoto> reviewPhotos;

    public Review(){

    }

    public Review(ItemFamily itemFamily, String title, String content, Integer score, Integer starValue){
        this.itemFamily = itemFamily;
        this.title = title;
        this.content = content;
        this.score = score;
        this.starValue = starValue;
    }

    public Review(Review review){
        this.id = review.id;
        this.itemFamily = review.itemFamily;
        this.title = review.title;
        this.content = review.content;
        this.score = review.score;
        this.starValue = review.starValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemFamily getItemFamily() {
        return itemFamily;
    }

    public void setItemFamily(ItemFamily itemFamily) {
        this.itemFamily = itemFamily;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStarValue() {
        return starValue;
    }

    public void setStarValue(Integer starValue) {
        this.starValue = starValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) &&
                Objects.equals(itemFamily, review.itemFamily) &&
                Objects.equals(title, review.title) &&
                Objects.equals(content, review.content) &&
                Objects.equals(score, review.score) &&
                Objects.equals(starValue, review.starValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemFamily, title, content, score, starValue);
    }
}
