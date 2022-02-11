package com.example.newsdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class NewsList implements Serializable {

    private List<News> articles;
}
