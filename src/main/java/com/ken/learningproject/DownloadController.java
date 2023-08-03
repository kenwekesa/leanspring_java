package com.ken.learningproject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {

    private PhotosService photosService;

    public DownloadController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<byte []> download(@PathVariable String id)
    {
        Photo photo = photosService.get(id);
        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

}
