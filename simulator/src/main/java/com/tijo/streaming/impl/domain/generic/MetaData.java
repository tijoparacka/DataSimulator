package com.tijo.streaming.impl.domain.generic;

public class MetaData
{
  private String dimension;
  private String type;
  private Double limit;
  private String file;
  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getDimension()
  {
    return dimension;
  }

  public void setDimension(String dimension)
  {
    this.dimension = dimension;
  }

  public Double getLimit()
  {
    return limit;
  }

  public void setLimit(Double limit)
  {
    this.limit = limit;
  }

  public String getFile()
  {
    return file;
  }

  public void setFile(String file)
  {
    this.file = file;
  }



}
