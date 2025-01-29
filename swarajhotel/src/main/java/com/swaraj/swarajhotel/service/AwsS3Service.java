package com.swaraj.swarajhotel.service;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.swaraj.swarajhotel.exception.OurException;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class AwsS3Service {

    private final String bucketName = "";

    @Value("${}")
    private String awsS3AccessKey;


    @Value("${}")
    private String awsS3SecretKey;

    public String saveImageToS3(MultipartFile photo){
        String s3LocationImage = null;

        try{

            String s3Filename = photo.getOriginalFilename();
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsS3AccessKey , awsS3SecretKey);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .withRegion(Regions.US_EAST_2)
                    .build();

            InputStream inputStream = photo.getInputStream();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/jpeg");

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName , s3Filename , inputStream , metadata);
            s3Client.putObject(putObjectRequest);
            return "https://"+bucketName+ ".s3.amazonaws.com/"+s3Filename;

        }catch (Exception e){
            e.printStackTrace();
            throw  new OurException("Unable to Upload image to s3 bucket"+ e.getMessage());
        }

    }
}
