package ru.test.poll.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "VOTE")
public class Vote implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTE_ID_SEQ")
    @SequenceGenerator(name = "VOTE_ID_SEQ", sequenceName = "VOTE_ID_SEQ", allocationSize = 1)
    @Id
    @Column(name = "ID")
    @ApiModelProperty(hidden = true)
    private long id;

    @ApiModelProperty(notes = "The title of the vote", value="Some title")
    @Column(name = "NAME")
    private String name;

    @ApiModelProperty(dataType = "java.sql.Date", value="2019-10-01")
    @Column(name = "DATE_FROM")
    private LocalDate dateFrom;

    @ApiModelProperty(dataType = "java.sql.Date", value="2019-10-01")
    @Column(name = "DATE_TO")
    private LocalDate dateTo;

    @Column(name = "ACTIVE")
    private Boolean active;

    @ApiModelProperty(notes = "The question of the vote")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "vote", cascade = CascadeType.ALL)
    private Question question;

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return id == vote.id &&
                Objects.equals(name, vote.name) &&
                Objects.equals(dateFrom, vote.dateFrom) &&
                Objects.equals(dateTo, vote.dateTo) &&
                Objects.equals(active, vote.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateFrom, dateTo, active);
    }
}

