package ru.yandex.practicum.catsgram.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Film {
    private long id;
    private String title;
    private int producedYear;
    private Date releaseDate;
    private String kind;
    private int MinLength;
}
