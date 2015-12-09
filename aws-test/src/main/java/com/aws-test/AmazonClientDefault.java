package com.aws-test;

import static com.globalsources.messagecenter.util.TimingUtils.formattedDisplay;

import java.util.List;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.common.base.Stopwatch;

@Service
@Slf4j
public class AmazonClientDefault implements AmazonClient {

    private final String bucketName;
    private final String attachmentPath;

    private AmazonS3Connection amazonS3Connection;

    @Inject
    public AmazonClientDefault(@Value("${amazon.s3.bucket.name}") String bucketName, //
            @Value("${messagecenter.attachment.path}") String attachmentPath, AmazonS3Connection amazonS3Connection) {
        this.bucketName = bucketName;
        this.attachmentPath = attachmentPath;
        this.amazonS3Connection = amazonS3Connection;
    }

    @Override
    public void store(MultipartFile uploadedFile) throws Exception {
        Stopwatch storeInAmazon = Stopwatch.createStarted();
        AmazonS3 s3 = amazonS3Connection.getConnection();
        try {
            log.info("Uploading a new object to S3 from a file\n");
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(uploadedFile.getContentType());
            objectMetadata.setContentLength(uploadedFile.getSize());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uploadedFile.getOriginalFilename(),
                    uploadedFile.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);
            PutObjectResult putObjectResult = s3.putObject(putObjectRequest);
            log.info("ETag is: " + putObjectResult.getETag());
            storeInAmazon.stop();
            log.info("Done uploading to Amazon " + formattedDisplay(storeInAmazon));

        } catch (AmazonServiceException ase) {
            log.info("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            log.info("Error Message:    " + ase.getMessage());
            log.info("HTTP Status Code: " + ase.getStatusCode());
            log.info("AWS Error Code:   " + ase.getErrorCode());
            log.info("Error Type:       " + ase.getErrorType());
            log.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            log.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ace.getMessage());
        }

    }

    @Override
    public S3Object download(String key) {
        Stopwatch downloadFromAmazon = Stopwatch.createStarted();
        AmazonS3 s3 = amazonS3Connection.getConnection();
        S3Object object = null;
        try {
            log.info("Downloading from S3\n");
            object = s3.getObject(new GetObjectRequest(bucketName, key));

        } catch (AmazonServiceException ase) {
            log.info("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            log.info("Error Message:    " + ase.getMessage());
            log.info("HTTP Status Code: " + ase.getStatusCode());
            log.info("AWS Error Code:   " + ase.getErrorCode());
            log.info("Error Type:       " + ase.getErrorType());
            log.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            log.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ace.getMessage());
        }
        downloadFromAmazon.stop();

        log.info("Done downloading from Amazon " + formattedDisplay(downloadFromAmazon));
        return object;
    }

    @Override
    public List<S3ObjectSummary> display() {
        Stopwatch getListFromAmazon = Stopwatch.createStarted();
        AmazonS3 s3 = amazonS3Connection.getConnection();
        ObjectListing objectListing = new ObjectListing();
        try {
            objectListing = s3.listObjects(new ListObjectsRequest().withBucketName(bucketName));

        } catch (AmazonServiceException ase) {
            log.info("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            log.info("Error Message:    " + ase.getMessage());
            log.info("HTTP Status Code: " + ase.getStatusCode());
            log.info("AWS Error Code:   " + ase.getErrorCode());
            log.info("Error Type:       " + ase.getErrorType());
            log.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            log.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ace.getMessage());
        }
        getListFromAmazon.stop();

        log.info("Done getting data from Amazon " + formattedDisplay(getListFromAmazon));

        return objectListing.getObjectSummaries();
    }
}
