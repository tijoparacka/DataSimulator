
package com.tijo.examples;
import com.tijo.streaming.impl.domain.generic.GenericEvent;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Wikipedia2 extends GenericEvent {

    @JsonProperty("time")
    private String time = "";
    @JsonProperty("added")
    private Integer added = 0;
    @JsonProperty("channel")
    private String channel = "";
    @JsonProperty("cityName")
    private String cityName = "";
    @JsonProperty("comment")
    private String comment = "";
    @JsonProperty("commentLength")
    private Integer commentLength = 0;
    @JsonProperty("countryIsoCode")
    private String countryIsoCode = "";
    @JsonProperty("countryName")
    private String countryName = "";
    @JsonProperty("deleted")
    private Integer deleted = 0;
    @JsonProperty("delta")
    private Integer delta = 0;
    @JsonProperty("deltaBucket")
    private Integer deltaBucket = 0;
    @JsonProperty("diffUrl")
    private String diffUrl = "";
    @JsonProperty("flags")
    private String flags = "";
    @JsonProperty("isAnonymous")
    private String isAnonymous = "";
    @JsonProperty("isMinor")
    private String isMinor = "";
    @JsonProperty("isNew")
    private String isNew = "";
    @JsonProperty("isRobot")
    private String isRobot = "";
    @JsonProperty("isUnpatrolled")
    private String isUnpatrolled = "";
    @JsonProperty("namespace")
    private String namespace = "";
    @JsonProperty("page")
    private String page = "";
    @JsonProperty("regionIsoCode")
    private String regionIsoCode = "";
    @JsonProperty("regionName")
    private String regionName = "";
    @JsonProperty("user")
    private String user = "";

}
