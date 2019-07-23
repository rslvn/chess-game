package com.game.chess.db.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(value = "api-key")
public class ApiKey {

    @Id
    private String id;

//    @ToString.Exclude
    @DBRef(lazy = true)
    private User user;
}
