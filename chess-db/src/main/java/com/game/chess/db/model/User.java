package com.game.chess.db.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@Document(value = "user")
public class User implements Serializable {

    @Id
    private String id;

    private String email;
    private String password;


    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
