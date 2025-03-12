package ru.yandex.practicum.catsgram.dto;

import lombok.Data;

@Data
public class UpdateImageRequest {
    private Long id;
    private String originalName;
    private String filePath;
    private long postId;
}
