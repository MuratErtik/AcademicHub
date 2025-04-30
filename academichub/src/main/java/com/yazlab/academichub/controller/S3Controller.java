package com.yazlab.academichub.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.yazlab.academichub.service.S3Service;


@RestController
@RequestMapping("/api/s3")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws  java.io.IOException {
        return s3Service.uploadFile(file);
    }

    @DeleteMapping("/delete/{key}")
    public void deleteFile(@PathVariable String key) {
        s3Service.deleteFile(key);
    }
}