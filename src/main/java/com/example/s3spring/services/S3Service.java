package com.example.s3spring.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.s3spring.services.exceptions.FileException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Log4j2
@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${aws.s3_bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile file)  {
        try {
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String contentType = file.getContentType();
            return uploadFile(fileName, inputStream, contentType);
        } catch (IOException e) {
            throw new FileException("Erro de IO" + e.getMessage());
        }
    }

    public URI uploadFile(String fileName, InputStream inputStream, String contentType) {
        try {
            var metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            s3Client.putObject(bucketName, fileName, inputStream, metadata);
            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter URL para URI");
        }
    }

}
