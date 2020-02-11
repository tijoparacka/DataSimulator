package com.tijo.streaming.impl.domain.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdvImpressionEvent extends GenericEvent
{
  @JsonProperty("Dia1")
  private String dia1;
  @JsonProperty("Dia2")
  private String dia2;
  @JsonProperty("Metric1")
  private int metric1;
  @JsonProperty("Time")
  private long time;
}
