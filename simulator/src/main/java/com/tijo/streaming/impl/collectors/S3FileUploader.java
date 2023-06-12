package com.tijo.streaming.impl.collectors;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.io.FileUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class S3FileUploader
{

  private AmazonS3 s3client;

  private final String accessKey;
  private final String secretKey;
  private final String bucketName;
  private final String s3Folder;
  private final String region;

  public S3FileUploader(String accessKey, String secretKey, String bucketName,String s3Folder, String region) {

    this.accessKey = accessKey;
    this.secretKey = secretKey;
    this.bucketName = bucketName;
    this.s3Folder = s3Folder;
    this.region = region;
    initAWSClient();

  }

  private void initAWSClient() {
    AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
    this.s3client = AmazonS3ClientBuilder.standard()
                                         .withCredentials(new AWSStaticCredentialsProvider(credentials))
                                         .withRegion(region)
                                         .build();
  }




  public void uploadAsync(Path filePath, UploadListener listener){
    Executors.newSingleThreadExecutor().execute(new Runnable()
    {
      @Override
      public void run()
      {
        try {
          String objectKey = s3Folder +"/"+ filePath.toFile().getName();
          s3client.putObject(new PutObjectRequest(bucketName, objectKey, filePath.toFile()));
          listener.uploadSuccess(filePath.toString());
        }
        catch (Exception e) {
            listener.uploadFailed(e,filePath.toString());
        }
      }
    });
  }
  public void upload(Path filePath){

      try {
        String objectKey = s3Folder +"/"+ filePath.toFile().getName();
        s3client.putObject(new PutObjectRequest(bucketName, objectKey, filePath.toFile()));
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
      }

  }

}
