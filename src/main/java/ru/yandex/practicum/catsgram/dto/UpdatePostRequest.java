package ru.yandex.practicum.catsgram.dto;

import lombok.Data;

@Data
public class UpdatePostRequest {
    private Integer id;
    private long authorId;
    private String description;

    public boolean hasDescription() {
        return ! (description == null || description.isBlank());
    }
}
