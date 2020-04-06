package com.tijo.streaming.impl.domain.generic;

import java.text.SimpleDateFormat;

public class MetaData
{
  private String dimension;
  private String type;
  private Double limit;
  private String start;
  private String end;
  private String file;
  private String format;
  private Integer cardinality;
  private String[] constants;
  private long longStart;
  private long longEnd;

  public SimpleDateFormat getDateFormatter()
  {
    return dateFormatter;
  }

  public void setDateFormatter(SimpleDateFormat dateFormatter)
  {
    this.dateFormatter = dateFormatter;
  }

  private SimpleDateFormat dateFormatter ;

  public long getLongStart()
  {
    return longStart;
  }

  public long getLongEnd()
  {
    return longEnd;
  }

  public void setLongStart(long longStart)
  {
    this.longStart = longStart;
  }

  public void setLongEnd(long longEnd)
  {
    this.longEnd = longEnd;
  }

  public String getFormat()
  {
    return format;
  }

  public void setFormat(String format)
  {
    this.format = format;
  }

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

  public String getStart()
  {
    return start;
  }

  public void setStart(String start)
  {
    this.start = start;
  }

  public String getEnd()
  {
    return end;
  }

  public void setEnd(String end)
  {
    this.end = end;
  }


  public String[] getConstants()
  {
    return this.constants;
  }
}
