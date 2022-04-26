package com.example.s3spring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class FileService {

    private final S3Service s3Service;

    public URI uploadFileToS3(MultipartFile multipartFile) {
        return s3Service.uploadFile(multipartFile);
    }
}
