package com.aws-test;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

@Slf4j
@Service
public class AmazonS3Connection {

    private String accessId;
    private String accessKey;

    @Inject
    public AmazonS3Connection(@Value("${amazon.aws.access.key.id}") String accessId,
            @Value("${amazon.aws.secret.access.key}") String accessKey) {
        this.accessId = accessId;
        this.accessKey = accessKey;
    }

    public AmazonS3 getConnection() {
        AWSCredentials awsCredentials = null;
        try {
            awsCredentials = new BasicAWSCredentials(accessId, accessKey);
        } catch (Exception e) {
            throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
                    + "Please make sure that your credentials file is at the correct "
                    + "location (~/.aws/credentials), and is in valid format.", e);
        }
        AmazonS3 s3 = new AmazonS3Client(awsCredentials);
        s3.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));

        return s3;
    }
}
