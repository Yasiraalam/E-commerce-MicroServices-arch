package com.yasir.microservices.products.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.yasir.microservices.products.services.ImageUploader;
import exceptions.ImageUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class S3ImageUploader implements ImageUploader {

    @Autowired
    private AmazonS3 client;
    @Value("${app.s3.bucket}")
    private String bucketName;


    @Override
    public String fileUploadImage(MultipartFile image) {

        if(image==null){
            throw new ImageUploadException("Image is null!!");
        }
        String actualFileName = image.getOriginalFilename();
        String fileName = UUID.randomUUID() + actualFileName.substring(actualFileName.lastIndexOf("."));

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());
        try {
            PutObjectResult putObjectRequest = client.putObject(new PutObjectRequest(bucketName, fileName, image.getInputStream(), metadata));
            return fileName;

        } catch (IOException e) {
            throw new ImageUploadException("error in uploading image "+ e.getMessage());
        }
    }

    @Override
    public List<String> allFiles() {
        return List.of();
    }

    @Override
    public String PreSignedUrl() {
        return "";
    }
}
