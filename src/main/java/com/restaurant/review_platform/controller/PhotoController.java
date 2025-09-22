package com.restaurant.review_platform.controller;

import com.restaurant.review_platform.domain.dto.PhotoDto;
import com.restaurant.review_platform.domain.entity.Photo;
import com.restaurant.review_platform.mapper.PhotoMapper;
import com.restaurant.review_platform.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/photos")
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @PostMapping
    public PhotoDto uploadPhoto(@RequestParam("file")MultipartFile file) {
        Photo savedPhoto = photoService.uploadPhoto(file);
        return photoMapper.toDto(savedPhoto);
    }

    @GetMapping(path = "/{id:.+}")
    public ResponseEntity<Resource> getPhoto(@PathVariable("id") String id) {
        return photoService.getPhotoAsResource(id).map(photo ->
            ResponseEntity.ok()
                .contentType(MediaTypeFactory.getMediaType(photo)
                    .orElse(MediaType.APPLICATION_OCTET_STREAM)
                )
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .body(photo)
        ).orElse(ResponseEntity.notFound().build());
    }
}
