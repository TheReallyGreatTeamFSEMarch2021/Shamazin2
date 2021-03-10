package com.talentpath.shamazin.showItemPage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name="Answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    private Integer userId;

    private String answer;

    private LocalDate date;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @NotBlank
    private Question question;      // Associate the answer with a question

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer1 = (Answer) o;

        if (answerId != null ? !answerId.equals(answer1.answerId) : answer1.answerId != null) return false;
        if (userId != null ? !userId.equals(answer1.userId) : answer1.userId != null) return false;
        if (answer != null ? !answer.equals(answer1.answer) : answer1.answer != null) return false;
        if (date != null ? !date.equals(answer1.date) : answer1.date != null) return false;
        return question != null ? question.equals(answer1.question) : answer1.question == null;
    }

    @Override
    public int hashCode() {
        int result = answerId != null ? answerId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
