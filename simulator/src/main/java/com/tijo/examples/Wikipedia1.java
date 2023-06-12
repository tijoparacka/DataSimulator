
package com.tijo.examples;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tijo.streaming.impl.domain.generic.GenericEvent;


public class Wikipedia1 extends GenericEvent {

    @JsonProperty("time")
    private String time = "";
    @JsonProperty("added")
    private Integer added = 0;
    @JsonProperty("channel")
    @JsonIgnore
    private String channel = "";
    @JsonProperty("cityName")
    @JsonIgnore
    private String cityName = "";
    @JsonProperty("comment")
    @JsonIgnore
    private String comment = "";
    @JsonProperty("commentLength")
    @JsonIgnore
    private Integer commentLength = 0;
    @JsonProperty("countryIsoCode")
    @JsonIgnore
    private String countryIsoCode = "";
    @JsonProperty("countryName")
    @JsonIgnore
    private String countryName = "";
    @JsonProperty("deleted")
    @JsonIgnore
    private Integer deleted = 0;
    @JsonProperty("delta")
    @JsonIgnore
    private Integer delta = 0;
    @JsonProperty("deltaBucket")
    @JsonIgnore
    private Integer deltaBucket = 0;
    @JsonProperty("diffUrl")
    @JsonIgnore
    private String diffUrl = "";
    @JsonProperty("flags")
    @JsonIgnore
    private String flags = "";
    @JsonProperty("isAnonymous")
    @JsonIgnore
    private String isAnonymous = "";
    @JsonProperty("isMinor")
    @JsonIgnore
    private String isMinor = "";
    @JsonProperty("isNew")
    @JsonIgnore
    private String isNew = "";
    @JsonProperty("isRobot")
    @JsonIgnore
    private String isRobot = "";
    @JsonProperty("isUnpatrolled")
    @JsonIgnore
    private String isUnpatrolled = "";
    @JsonProperty("namespace")
    @JsonIgnore
    private String namespace = "";
    @JsonProperty("page")
    @JsonIgnore
    private String page = "";
    @JsonProperty("regionIsoCode")
    @JsonIgnore
    private String regionIsoCode = "";
    @JsonProperty("regionName")
    @JsonIgnore
    private String regionName = "";
    @JsonProperty("user")
    @JsonIgnore
    private String user = "";
}

