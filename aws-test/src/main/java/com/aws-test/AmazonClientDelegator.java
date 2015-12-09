package com.aws-test;

import javax.inject.Inject;

import lombok.experimental.Delegate;

import org.springframework.stereotype.Service;

@Service
public class AmazonClientDelegator implements AmazonClient {
    @Delegate
    private AmazonClient amazonClient;

    @Inject
    public AmazonClientDelegator(AmazonClientDefault amazonClient) {

        this.amazonClient = amazonClient; //
    }

    public void setAmazonClient(AmazonClient amazonClient) {

        this.amazonClient = amazonClient; //
    }

}
