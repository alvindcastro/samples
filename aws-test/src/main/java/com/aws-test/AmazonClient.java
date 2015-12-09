package com.aws-test;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public interface AmazonClient {
    void store(MultipartFile file) throws Exception;

    public S3Object download(String key);

    public List<S3ObjectSummary> display();
}
