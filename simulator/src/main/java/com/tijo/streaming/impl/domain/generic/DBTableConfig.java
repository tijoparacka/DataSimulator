package com.tijo.streaming.impl.domain.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DBTableConfig
{
  @JsonProperty("table")
  String table;
  @JsonProperty("columns")
  String[] columns;
  @JsonProperty("indexes")
  int[] indexes;

  public String getTableName()
  {
    return table;
  }
  public String[] getColName()
  {
    return columns;
  }
  public int[] getIndexes()
  {
    return indexes;
  }

}
