package com.tijo.streaming.impl.collectors;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.messages.StopSimulation;
import com.tijo.streaming.results.SimulationResultsSummary;
import scala.runtime.StringFormat;

import java.io.File;
import java.nio.file.Path;

public class S3FileEventCollector extends FileEventCollector {

 private String accessKey ;
 private String secretKey ;
 private String bucketName;
 private String region;
  private String s3Folder;
 //private AmazonS3 s3client;
  public S3FileEventCollector() {
    super();
    try {
        ConfigUtil config = ConfigUtil.getInstance();
        accessKey = config.getConfig("sim.s3.accesskey");
        secretKey = config.getConfig("sim.s3.secretKey");
        bucketName = config.getConfig("sim.s3.bucket");
        s3Folder = config.getConfig("sim.s3.folder");
        region = config.getConfig("sim.s3.region");
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        if(accessKey != null &&  accessKey.trim().length() >0 &&
           secretKey != null &&  secretKey.trim().length() >0 &&
           bucketName != null &&  bucketName.trim().length() >0 &&
           region != null &&  region.trim().length() >0 ) {
          logger.info (" Required S3 parameters are provided ");
        }else{
          logger.info (" Please check if sim.s3.accesskey ,im.s3.secretKey ,sim.s3.bucket ,sim.s3.folder sim.s3.region are provided ");
          throw new Exception(" s3 parameters are not provided.");
        }
      } catch(Exception e){
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        System.exit(1);
      }
    }
    protected Path getNewPath(int fileNameLength) throws Exception{
      if(path != null)
        uploadToS3();
      return super.getNewPath(fileNameLength);
    }

  private void uploadToS3()
  {
    S3FileUploader s3FileUploader  = new S3FileUploader(accessKey,secretKey,bucketName,s3Folder,region);
    s3FileUploader.uploadAsync(
        getFileName(),
        new UploadListener()
        {
          @Override
          public void uploadSuccess(String uploadedFile)
          {
            logger.info(String.format("File %s uploaded successfully!!",uploadedFile) );
            new File(uploadedFile).delete();
          }
          @Override
          public void uploadFailed(Exception e, String failedFile)
          {
            logger.error( String.format("File %s upload failed !!",failedFile) );
            e.printStackTrace();
          }
        }
    );
  }
  protected void shutDown(){
    S3FileUploader s3FileUploader  = new S3FileUploader(accessKey,secretKey,bucketName,s3Folder,region);
    s3FileUploader.upload(getFileName());
    new File(getFileName().toFile().getAbsolutePath()).delete();
    Thread.sleep(5000);
    super.shutDown();
  }
    @Override
    public void onReceive(Object message) throws Exception {
      if (message instanceof StopSimulation) {
        uploadToS3();
      }else {
        super.onReceive(message);
     }
    }
  }

