package com.yasir.microservices.products.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUploader {

    String fileUploadImage(MultipartFile image);

    List<String> allFiles();

    String PreSignedUrl(String fileName);

}
