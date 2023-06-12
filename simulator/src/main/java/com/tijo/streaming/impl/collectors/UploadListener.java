package com.tijo.streaming.impl.collectors;

public interface UploadListener
{
  void uploadSuccess(String uploadedFile);
  void uploadFailed(Exception e,String failedFile);
}
