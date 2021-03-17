package com.talentpath.shamazin.showItemPage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId; // Creates a unique id for each question asked in the DB

    private Integer itemId;     // Associate the question to a particular item/product

    private Integer userId;     // Associate the question with a user / confirmed buyer

    private String question;    // The question that will be displayed to the user

    private Integer votes;      // To store the number of up/downvotes a question receives

    private LocalDate date;     // Store the date of when the question was asked

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @NotBlank
    private ItemFamily itemFamily;

    @OneToMany(mappedBy="question", cascade=CascadeType.ALL)
    private List<Answer> answers;

    public ItemFamily getItemFamily() {
        return itemFamily;
    }

    public void setItemFamily(ItemFamily itemFamily) {
        this.itemFamily = itemFamily;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Question(){

    }

    public Question(Integer questionId, Integer itemId, Integer userId, String question, Integer votes, LocalDate date,
                    ItemFamily itemFamily, List<Answer> answers){
        this.questionId = questionId;
        this.itemId = itemId;
        this.userId = userId;
        this.question = question;
        this.votes = votes;
        this.date = date;
        this.itemFamily = itemFamily;
        this.answers = answers;
    }

    public Question(Question that){
        this.questionId = that.questionId;
        this.itemId = that.itemId;
        this.userId = that.userId;
        this.question = that.question;
        this.votes = that.votes;
        this.date = that.date;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (questionId != null ? !questionId.equals(question1.questionId) : question1.questionId != null) return false;
        if (itemId != null ? !itemId.equals(question1.itemId) : question1.itemId != null) return false;
        if (userId != null ? !userId.equals(question1.userId) : question1.userId != null) return false;
        if (question != null ? !question.equals(question1.question) : question1.question != null) return false;
        if (votes != null ? !votes.equals(question1.votes) : question1.votes != null) return false;
        if (date != null ? !date.equals(question1.date) : question1.date != null) return false;
        return itemFamily != null ? itemFamily.equals(question1.itemFamily) : question1.itemFamily == null;
    }

    @Override
    public int hashCode() {
        int result = questionId != null ? questionId.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (votes != null ? votes.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (itemFamily != null ? itemFamily.hashCode() : 0);
        return result;
    }
}
