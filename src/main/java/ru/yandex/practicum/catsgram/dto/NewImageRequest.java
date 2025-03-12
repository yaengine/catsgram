package ru.yandex.practicum.catsgram.dto;

import lombok.Data;

@Data
public class NewImageRequest {
    private String originalName;
    private String filePath;
    private long postId;
}
