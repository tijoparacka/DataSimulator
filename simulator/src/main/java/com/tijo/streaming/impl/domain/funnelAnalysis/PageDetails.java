package com.tijo.streaming.impl.domain.funnelAnalysis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tijo.streaming.impl.domain.Event;

import java.sql.Timestamp;

public class PageDetails extends Event {
   @JsonIgnore
   private int conversion ;
    @JsonProperty("Page")
   private String pageId ;
    @JsonProperty("Cookie")
    private String cookie;
    @JsonProperty("Timestamp")
   private long timestamp;
    @JsonProperty("ClickId")
   private int clickId;
   PageDetails(int conversion , String pageId, String cookie){
        this.conversion = conversion;
        this.pageId = pageId;
        this.cookie = cookie;
        this.timestamp = System.currentTimeMillis();
    }

    public void setClickId(int clickId)
    {
      this.clickId=clickId;
    }
}
