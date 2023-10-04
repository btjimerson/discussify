package xyz.pintobean.discussify;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "author")
    private String author;

    @Column(name = "post_date")
    @CreationTimestamp
    private Date postDate;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "comments")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Comment> comments;

}
