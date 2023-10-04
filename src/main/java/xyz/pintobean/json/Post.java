package xyz.pintobean.json;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Post {

    @Id
    private Integer id;
    private String author;
    private String post;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<Comment> comments;

}
