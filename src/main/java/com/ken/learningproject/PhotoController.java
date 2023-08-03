package com.ken.learningproject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import static org.springframework.http.RequestEntity.put;

@RestController
public class PhotoController {

   private PhotosService photosService;

    public PhotoController(PhotosService photosService) {
        this.photosService = photosService;
    }


    @GetMapping("/")
    public String sayHello()
    {
        return "Hello world";
    }

    @GetMapping("/photos")
    public Collection<Photo> getPhotos()
    {
        return photosService.get();
    }

    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable String id)
    {
        Photo photo = photosService.get(id);

        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable String id)
    {
        Photo photo = photosService.remove(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

//    @PostMapping("/photos")
//    public Photo createPhoto(@RequestBody @Valid Photo photo)
//    {
//        photo.setId(UUID.randomUUID().toString());
//        photos.put(photo.getId(), photo);
//
//        return photo;
//    }

    @PostMapping("/photos")
    public Photo uploadPhoto(@RequestPart("data") MultipartFile file) throws IOException {

       Photo photo = photosService.save(file.getOriginalFilename(), file.getBytes());

        return photo;
    }

}
