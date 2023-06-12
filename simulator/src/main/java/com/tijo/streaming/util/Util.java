package com.tijo.streaming.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util
{
  public static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static MessageDigest md;

  static {
    try {
      md = MessageDigest.getInstance("MD5");
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public static String randomAlphaNumeric(double count) {
      StringBuilder builder = new StringBuilder();
      while (count-- != 0) {
          int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
          builder.append(ALPHA_NUMERIC_STRING.charAt(character));
      }
      return builder.toString();
  }

  public static String computeMD5(String input){

    md.update(input.getBytes());
    byte[] digest = md.digest();
    String hash = DatatypeConverter
        .printHexBinary(digest).toUpperCase();
    return hash;
  }
}
