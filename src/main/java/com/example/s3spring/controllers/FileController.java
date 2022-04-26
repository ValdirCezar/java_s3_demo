package com.example.s3spring.controllers;

import com.example.s3spring.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/files")
public class FileController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<URI> uploadFileToS3(@RequestParam("file") MultipartFile multipartFile) {
        URI uri = fileService.uploadFileToS3(multipartFile);
        return ResponseEntity.created(uri).build();
    }
}
