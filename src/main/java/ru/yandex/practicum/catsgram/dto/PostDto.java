package ru.yandex.practicum.catsgram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class PostDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private long authorId;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant postDate;
}
