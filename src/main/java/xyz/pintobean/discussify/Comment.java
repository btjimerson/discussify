package xyz.pintobean.discussify;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {

    private String author;
    private String comment;
    private Date commentDate;
}
