package com.yasir.microservices.products.controllers;
import com.yasir.microservices.products.services.ImageUploader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/s3")
public class S3Controller {

    private ImageUploader uploader;

    public S3Controller(ImageUploader uploader){
        this.uploader = uploader;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(MultipartFile file){
        return ResponseEntity.ok(uploader.fileUploadImage(file));
    }
}
