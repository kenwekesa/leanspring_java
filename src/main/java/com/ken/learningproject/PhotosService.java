package com.ken.learningproject;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@Component
@Service
public class PhotosService {

    private Map<String, Photo> photos =new HashMap<>() {{
        put("1", new Photo("1","My photo one"));
    }};

    public Collection<Photo> get() {

        return photos.values();
    }

    public Photo get(String id) {

        return photos.get(id);
    }

    public Photo remove(String id) {
        return photos.remove(id);
    }

    public Photo save(String fileName, byte [] data) {

        Photo photo= new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setPhotoName(fileName);
        photo.setData(data);
        photos.put(photo.getId(), photo);
        return photo;
    }
}
