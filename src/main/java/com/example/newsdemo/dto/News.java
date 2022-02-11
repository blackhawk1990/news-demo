package com.example.newsdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class News implements Serializable {

    private String title;
    private String author;
    private String description;
}
