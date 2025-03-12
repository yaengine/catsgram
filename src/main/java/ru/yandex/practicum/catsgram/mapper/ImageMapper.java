package ru.yandex.practicum.catsgram.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.catsgram.dto.NewImageRequest;
import ru.yandex.practicum.catsgram.dto.ImageDto;
import ru.yandex.practicum.catsgram.dto.UpdateImageRequest;
import ru.yandex.practicum.catsgram.model.Image;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageMapper {
    public static Image mapToImage(NewImageRequest request) {
        Image image = new Image();
        image.setOriginalFileName(request.getOriginalName());
        image.setFilePath(request.getFilePath());
        image.setPostId(request.getPostId());

        return image;
    }

    public static ImageDto mapToImageDto(Image image) {
        ImageDto dto = new ImageDto();
        dto.setId(image.getId());
        dto.setOriginalName(image.getOriginalFileName());
        dto.setFilePath(image.getFilePath());
        dto.setPostId(image.getPostId());
        return dto;
    }

    public static Image updateImageFields(Image image, UpdateImageRequest request) {
        image.setOriginalFileName(request.getOriginalName());
        image.setFilePath(request.getFilePath());
        image.setPostId(request.getPostId());
        return image;
    }
}
