package ru.test.poll.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "QUESTION")
public class Question implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "foreign")
    @GenericGenerator(strategy = "foreign", name="foreign",
            parameters = @Parameter(name = "property", value="vote"))
    @ApiModelProperty(hidden = true)
    private long id;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "ORDERING")
    private Integer ordering;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Vote vote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                Objects.equals(text, question.text) &&
                Objects.equals(ordering, question.ordering);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, ordering);
    }
}
