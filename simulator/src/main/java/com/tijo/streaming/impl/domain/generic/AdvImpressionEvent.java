package com.tijo.streaming.impl.domain.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class AdvImpressionEvent extends GenericEvent {

    @JsonProperty("dwh_create_timestamp")
    private long dwh_create_timestamp;

  @JsonProperty("auction_start_timestamp")
    private long auction_start_timestamp;

  @JsonProperty("auction_end_timestamp")
    private long auction_end_timestamp;

  @JsonProperty("auction_id")
    private String auction_id;

  @JsonProperty("auction_type")
    private String auction_type;

  @JsonProperty("ad_unit_id")
    private int ad_unit_id;

  @JsonProperty("application_id")
    private int application_id;

  @JsonProperty("provider_id")
    private int provider_id;

  @JsonProperty("provider_name")
    private String provider_name;

  @JsonProperty("instance_id")
    private int instance_id;

  @JsonProperty("instance_type_id")
    private int instance_type_id;

  @JsonProperty("bid_type")
    private String bid_type;

  @JsonProperty("bid_request_id")
    private String bid_request_id;

  @JsonProperty("bid_request_timestamp")
    private String bid_request_timestamp;

  @JsonProperty("bid_response_timestamp")
    private String bid_response_timestamp;

  @JsonProperty("bid_price")
    private double bid_price;

  @JsonProperty("suggested_bid")
    private double suggested_bid;

  @JsonProperty("epsilon")
    private int epsilon;

  @JsonProperty("clearance")
    private double clearance;

  @JsonProperty("bidder_clearence")
    private String bidder_clearence;

  @JsonProperty("competitive_bid")
    private double competitive_bid;

  @JsonProperty("bid_below")
    private double bid_below;

  @JsonProperty("rank")
    private int rank;

  @JsonProperty("bidder_rank")
    private String bidder_rank;

  @JsonProperty("is_auction_winner")
    private boolean is_auction_winner;

  @JsonProperty("auction_winner_provider")
    private int auction_winner_provider;

  @JsonProperty("auction_winner_instance")
    private int auction_winner_instance;

  @JsonProperty("number_of_bid_requests")
    private int number_of_bid_requests;

  @JsonProperty("number_of_bid_response")
    private int number_of_bid_response;

  @JsonProperty("number_of_instances")
    private int number_of_instances;

  @JsonProperty("mediation_session_id")
    private String mediation_session_id;

  @JsonProperty("session_depth")
    private int session_depth;

  @JsonProperty("instance_manual_position")
    private String instance_manual_position;

  @JsonProperty("instance_manual_type")
    private String instance_manual_type;

  @JsonProperty("bidder_error_id")
    private String bidder_error_id;

  @JsonProperty("bidder_error_msg")
    private String bidder_error_msg;

  @JsonProperty("mediation_sdk_version")
    private String mediation_sdk_version;

  @JsonProperty("device_id")
    private String device_id;

  @JsonProperty("advertising_id_type")
    private String advertising_id_type;

  @JsonProperty("is_limit_ad_tracking")
    private boolean is_limit_ad_tracking;

  @JsonProperty("ip")
    private String ip;

  @JsonProperty("device_country_code")
    private String device_country_code;

  @JsonProperty("user_agent")
    private String user_agent;

  @JsonProperty("device_os")
    private String device_os;

  @JsonProperty("device_type")
    private String device_type;

  @JsonProperty("device_os_version")
    private String device_os_version;

  @JsonProperty("application_user_id")
    private String application_user_id;

  @JsonProperty("app_bundle_id")
    private String app_bundle_id;

  @JsonProperty("application_version")
    private String application_version;

  @JsonProperty("application_user_age")
    private int application_user_age;

  @JsonProperty("application_user_gender")
    private String application_user_gender;

  @JsonProperty("client_timestamp")
    private String client_timestamp;

  @JsonProperty("connection_type")
    private String connection_type;

  @JsonProperty("screen_size_resolution_height")
    private int screen_size_resolution_height;

  @JsonProperty("screen_size_resolution_width")
    private int screen_size_resolution_width;

  @JsonProperty("device_make")
    private String device_make;

  @JsonProperty("device_model")
    private String device_model;

  @JsonProperty("carrier")
    private String carrier;

  @JsonProperty("event_timestamp")
    private String event_timestamp;

  @JsonProperty("ab_internal")
    private String ab_internal;

  @JsonProperty("is_test")
    private boolean is_test;

  @JsonProperty("kafka_partition")
    private int kafka_partition;

  @JsonProperty("kafka_offset")
    private int kafka_offset;

  @JsonProperty("mediation_sdk_version_numeric")
    private int mediation_sdk_version_numeric;

  @JsonProperty("external_mediation")
    private String external_mediation;

  @JsonProperty("abt")
    private String abt;

  @JsonProperty("event_day")
    private Date event_day;

  @JsonProperty("hour")
    private int hour;

  @JsonProperty("upsolver_schema_version")
    private int upsolver_schema_version;


}

