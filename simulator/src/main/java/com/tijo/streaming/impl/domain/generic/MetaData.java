package com.tijo.streaming.impl.domain.generic;

public class MetaData
{
  private String dimension;
  private String type;
  private Double limit;
  private Double start;
  private Double end;
  private String file;
  private Integer cardinality;
  private String[] constants;

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
    if (!this.type.equalsIgnoreCase( GenericEventGenerator.FIXED) &&  getFile()!= null){
      throw new IllegalStateException("Unexpected value: "+getFile() +" for "+this.type );
    }
    return file;
  }

  public int getCardinality()
  {
    return this.cardinality;
  }

  public void setFile(String file)
  {
    this.file = file;
  }

  public Double getStart()
  {
    return start;
  }

  public void setStart(Double start)
  {
    this.start = start;
  }

  public Double getEnd()
  {
    return end;
  }

  public void setEnd(Double end)
  {
    this.end = end;
  }


  public String[] getConstants()
  {
    return this.constants;
  }
}
