
package com.example;
import com.tijo.streaming.impl.domain.Event;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dwh_create_timestamp",
    "auction_start_timestamp",
    "auction_end_timestamp",
    "auction_id",
    "auction_type",
    "ad_unit_id",
    "application_id",
    "provider_id",
    "provider_name",
    "instance_id",
    "instance_type_id",
    "bid_type",
    "bid_request_id",
    "bid_request_timestamp",
    "bid_response_timestamp",
    "bid_price",
    "suggested_bid",
    "epsilon",
    "clearance",
    "bidder_clearence",
    "competitive_bid",
    "bid_below",
    "rank",
    "bidder_rank",
    "is_auction_winner",
    "auction_winner_provider",
    "auction_winner_instance",
    "number_of_bid_requests",
    "number_of_bid_response",
    "number_of_instances",
    "mediation_session_id",
    "session_depth",
    "instance_manual_position",
    "instance_manual_type",
    "bidder_error_id",
    "bidder_error_msg",
    "mediation_sdk_version",
    "device_id",
    "advertising_id_type",
    "is_limit_ad_tracking",
    "ip",
    "device_country_code",
    "user_agent",
    "device_os",
    "device_type",
    "device_os_version",
    "application_user_id",
    "app_bundle_id",
    "application_version",
    "application_user_age",
    "application_user_gender",
    "client_timestamp",
    "connection_type",
    "screen_size_resolution_height",
    "screen_size_resolution_width",
    "device_make",
    "device_model",
    "carrier",
    "event_timestamp",
    "ab_internal",
    "is_test",
    "kafka_partition",
    "kafka_offset",
    "mediation_sdk_version_numeric",
    "external_mediation",
    "abt",
    "event_day",
    "hour",
    "upsolver_schema_version"
})
public class CGX0TTUC3T extends Event {

    @JsonProperty("dwh_create_timestamp")
    private Integer dwhCreateTimestamp;
    @JsonProperty("auction_start_timestamp")
    private Integer auctionStartTimestamp;
    @JsonProperty("auction_end_timestamp")
    private Integer auctionEndTimestamp;
    @JsonProperty("auction_id")
    private String auctionId;
    @JsonProperty("auction_type")
    private String auctionType;
    @JsonProperty("ad_unit_id")
    private Integer adUnitId;
    @JsonProperty("application_id")
    private Integer applicationId;
    @JsonProperty("provider_id")
    private Integer providerId;
    @JsonProperty("provider_name")
    private String providerName;
    @JsonProperty("instance_id")
    private Integer instanceId;
    @JsonProperty("instance_type_id")
    private Integer instanceTypeId;
    @JsonProperty("bid_type")
    private String bidType;
    @JsonProperty("bid_request_id")
    private String bidRequestId;
    @JsonProperty("bid_request_timestamp")
    private String bidRequestTimestamp;
    @JsonProperty("bid_response_timestamp")
    private String bidResponseTimestamp;
    @JsonProperty("bid_price")
    private Double bidPrice;
    @JsonProperty("suggested_bid")
    private Double suggestedBid;
    @JsonProperty("epsilon")
    private Integer epsilon;
    @JsonProperty("clearance")
    private Double clearance;
    @JsonProperty("bidder_clearence")
    private String bidderClearence;
    @JsonProperty("competitive_bid")
    private Double competitiveBid;
    @JsonProperty("bid_below")
    private Double bidBelow;
    @JsonProperty("rank")
    private Integer rank;
    @JsonProperty("bidder_rank")
    private String bidderRank;
    @JsonProperty("is_auction_winner")
    private Boolean isAuctionWinner;
    @JsonProperty("auction_winner_provider")
    private Integer auctionWinnerProvider;
    @JsonProperty("auction_winner_instance")
    private Integer auctionWinnerInstance;
    @JsonProperty("number_of_bid_requests")
    private Integer numberOfBidRequests;
    @JsonProperty("number_of_bid_response")
    private Integer numberOfBidResponse;
    @JsonProperty("number_of_instances")
    private Integer numberOfInstances;
    @JsonProperty("mediation_session_id")
    private String mediationSessionId;
    @JsonProperty("session_depth")
    private Integer sessionDepth;
    @JsonProperty("instance_manual_position")
    private String instanceManualPosition;
    @JsonProperty("instance_manual_type")
    private String instanceManualType;
    @JsonProperty("bidder_error_id")
    private String bidderErrorId;
    @JsonProperty("bidder_error_msg")
    private String bidderErrorMsg;
    @JsonProperty("mediation_sdk_version")
    private String mediationSdkVersion;
    @JsonProperty("device_id")
    private String deviceId;
    @JsonProperty("advertising_id_type")
    private String advertisingIdType;
    @JsonProperty("is_limit_ad_tracking")
    private Boolean isLimitAdTracking;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("device_country_code")
    private String deviceCountryCode;
    @JsonProperty("user_agent")
    private String userAgent;
    @JsonProperty("device_os")
    private String deviceOs;
    @JsonProperty("device_type")
    private String deviceType;
    @JsonProperty("device_os_version")
    private String deviceOsVersion;
    @JsonProperty("application_user_id")
    private String applicationUserId;
    @JsonProperty("app_bundle_id")
    private String appBundleId;
    @JsonProperty("application_version")
    private String applicationVersion;
    @JsonProperty("application_user_age")
    private Integer applicationUserAge;
    @JsonProperty("application_user_gender")
    private String applicationUserGender;
    @JsonProperty("client_timestamp")
    private String clientTimestamp;
    @JsonProperty("connection_type")
    private String connectionType;
    @JsonProperty("screen_size_resolution_height")
    private Integer screenSizeResolutionHeight;
    @JsonProperty("screen_size_resolution_width")
    private Integer screenSizeResolutionWidth;
    @JsonProperty("device_make")
    private String deviceMake;
    @JsonProperty("device_model")
    private String deviceModel;
    @JsonProperty("carrier")
    private String carrier;
    @JsonProperty("event_timestamp")
    private String eventTimestamp;
    @JsonProperty("ab_internal")
    private String abInternal;
    @JsonProperty("is_test")
    private Boolean isTest;
    @JsonProperty("kafka_partition")
    private Integer kafkaPartition;
    @JsonProperty("kafka_offset")
    private Integer kafkaOffset;
    @JsonProperty("mediation_sdk_version_numeric")
    private Integer mediationSdkVersionNumeric;
    @JsonProperty("external_mediation")
    private String externalMediation;
    @JsonProperty("abt")
    private String abt;
    @JsonProperty("event_day")
    private Integer eventDay;
    @JsonProperty("hour")
    private Integer hour;
    @JsonProperty("upsolver_schema_version")
    private Integer upsolverSchemaVersion;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dwh_create_timestamp")
    public Integer getDwhCreateTimestamp() {
        return dwhCreateTimestamp;
    }

    @JsonProperty("dwh_create_timestamp")
    public void setDwhCreateTimestamp(Integer dwhCreateTimestamp) {
        this.dwhCreateTimestamp = dwhCreateTimestamp;
    }

    public CGX0TTUC3T withDwhCreateTimestamp(Integer dwhCreateTimestamp) {
        this.dwhCreateTimestamp = dwhCreateTimestamp;
        return this;
    }

    @JsonProperty("auction_start_timestamp")
    public Integer getAuctionStartTimestamp() {
        return auctionStartTimestamp;
    }

    @JsonProperty("auction_start_timestamp")
    public void setAuctionStartTimestamp(Integer auctionStartTimestamp) {
        this.auctionStartTimestamp = auctionStartTimestamp;
    }

    public CGX0TTUC3T withAuctionStartTimestamp(Integer auctionStartTimestamp) {
        this.auctionStartTimestamp = auctionStartTimestamp;
        return this;
    }

    @JsonProperty("auction_end_timestamp")
    public Integer getAuctionEndTimestamp() {
        return auctionEndTimestamp;
    }

    @JsonProperty("auction_end_timestamp")
    public void setAuctionEndTimestamp(Integer auctionEndTimestamp) {
        this.auctionEndTimestamp = auctionEndTimestamp;
    }

    public CGX0TTUC3T withAuctionEndTimestamp(Integer auctionEndTimestamp) {
        this.auctionEndTimestamp = auctionEndTimestamp;
        return this;
    }

    @JsonProperty("auction_id")
    public String getAuctionId() {
        return auctionId;
    }

    @JsonProperty("auction_id")
    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public CGX0TTUC3T withAuctionId(String auctionId) {
        this.auctionId = auctionId;
        return this;
    }

    @JsonProperty("auction_type")
    public String getAuctionType() {
        return auctionType;
    }

    @JsonProperty("auction_type")
    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    public CGX0TTUC3T withAuctionType(String auctionType) {
        this.auctionType = auctionType;
        return this;
    }

    @JsonProperty("ad_unit_id")
    public Integer getAdUnitId() {
        return adUnitId;
    }

    @JsonProperty("ad_unit_id")
    public void setAdUnitId(Integer adUnitId) {
        this.adUnitId = adUnitId;
    }

    public CGX0TTUC3T withAdUnitId(Integer adUnitId) {
        this.adUnitId = adUnitId;
        return this;
    }

    @JsonProperty("application_id")
    public Integer getApplicationId() {
        return applicationId;
    }

    @JsonProperty("application_id")
    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public CGX0TTUC3T withApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    @JsonProperty("provider_id")
    public Integer getProviderId() {
        return providerId;
    }

    @JsonProperty("provider_id")
    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public CGX0TTUC3T withProviderId(Integer providerId) {
        this.providerId = providerId;
        return this;
    }

    @JsonProperty("provider_name")
    public String getProviderName() {
        return providerName;
    }

    @JsonProperty("provider_name")
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public CGX0TTUC3T withProviderName(String providerName) {
        this.providerName = providerName;
        return this;
    }

    @JsonProperty("instance_id")
    public Integer getInstanceId() {
        return instanceId;
    }

    @JsonProperty("instance_id")
    public void setInstanceId(Integer instanceId) {
        this.instanceId = instanceId;
    }

    public CGX0TTUC3T withInstanceId(Integer instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    @JsonProperty("instance_type_id")
    public Integer getInstanceTypeId() {
        return instanceTypeId;
    }

    @JsonProperty("instance_type_id")
    public void setInstanceTypeId(Integer instanceTypeId) {
        this.instanceTypeId = instanceTypeId;
    }

    public CGX0TTUC3T withInstanceTypeId(Integer instanceTypeId) {
        this.instanceTypeId = instanceTypeId;
        return this;
    }

    @JsonProperty("bid_type")
    public String getBidType() {
        return bidType;
    }

    @JsonProperty("bid_type")
    public void setBidType(String bidType) {
        this.bidType = bidType;
    }

    public CGX0TTUC3T withBidType(String bidType) {
        this.bidType = bidType;
        return this;
    }

    @JsonProperty("bid_request_id")
    public String getBidRequestId() {
        return bidRequestId;
    }

    @JsonProperty("bid_request_id")
    public void setBidRequestId(String bidRequestId) {
        this.bidRequestId = bidRequestId;
    }

    public CGX0TTUC3T withBidRequestId(String bidRequestId) {
        this.bidRequestId = bidRequestId;
        return this;
    }

    @JsonProperty("bid_request_timestamp")
    public String getBidRequestTimestamp() {
        return bidRequestTimestamp;
    }

    @JsonProperty("bid_request_timestamp")
    public void setBidRequestTimestamp(String bidRequestTimestamp) {
        this.bidRequestTimestamp = bidRequestTimestamp;
    }

    public CGX0TTUC3T withBidRequestTimestamp(String bidRequestTimestamp) {
        this.bidRequestTimestamp = bidRequestTimestamp;
        return this;
    }

    @JsonProperty("bid_response_timestamp")
    public String getBidResponseTimestamp() {
        return bidResponseTimestamp;
    }

    @JsonProperty("bid_response_timestamp")
    public void setBidResponseTimestamp(String bidResponseTimestamp) {
        this.bidResponseTimestamp = bidResponseTimestamp;
    }

    public CGX0TTUC3T withBidResponseTimestamp(String bidResponseTimestamp) {
        this.bidResponseTimestamp = bidResponseTimestamp;
        return this;
    }

    @JsonProperty("bid_price")
    public Double getBidPrice() {
        return bidPrice;
    }

    @JsonProperty("bid_price")
    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public CGX0TTUC3T withBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
        return this;
    }

    @JsonProperty("suggested_bid")
    public Double getSuggestedBid() {
        return suggestedBid;
    }

    @JsonProperty("suggested_bid")
    public void setSuggestedBid(Double suggestedBid) {
        this.suggestedBid = suggestedBid;
    }

    public CGX0TTUC3T withSuggestedBid(Double suggestedBid) {
        this.suggestedBid = suggestedBid;
        return this;
    }

    @JsonProperty("epsilon")
    public Integer getEpsilon() {
        return epsilon;
    }

    @JsonProperty("epsilon")
    public void setEpsilon(Integer epsilon) {
        this.epsilon = epsilon;
    }

    public CGX0TTUC3T withEpsilon(Integer epsilon) {
        this.epsilon = epsilon;
        return this;
    }

    @JsonProperty("clearance")
    public Double getClearance() {
        return clearance;
    }

    @JsonProperty("clearance")
    public void setClearance(Double clearance) {
        this.clearance = clearance;
    }

    public CGX0TTUC3T withClearance(Double clearance) {
        this.clearance = clearance;
        return this;
    }

    @JsonProperty("bidder_clearence")
    public String getBidderClearence() {
        return bidderClearence;
    }

    @JsonProperty("bidder_clearence")
    public void setBidderClearence(String bidderClearence) {
        this.bidderClearence = bidderClearence;
    }

    public CGX0TTUC3T withBidderClearence(String bidderClearence) {
        this.bidderClearence = bidderClearence;
        return this;
    }

    @JsonProperty("competitive_bid")
    public Double getCompetitiveBid() {
        return competitiveBid;
    }

    @JsonProperty("competitive_bid")
    public void setCompetitiveBid(Double competitiveBid) {
        this.competitiveBid = competitiveBid;
    }

    public CGX0TTUC3T withCompetitiveBid(Double competitiveBid) {
        this.competitiveBid = competitiveBid;
        return this;
    }

    @JsonProperty("bid_below")
    public Double getBidBelow() {
        return bidBelow;
    }

    @JsonProperty("bid_below")
    public void setBidBelow(Double bidBelow) {
        this.bidBelow = bidBelow;
    }

    public CGX0TTUC3T withBidBelow(Double bidBelow) {
        this.bidBelow = bidBelow;
        return this;
    }

    @JsonProperty("rank")
    public Integer getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public CGX0TTUC3T withRank(Integer rank) {
        this.rank = rank;
        return this;
    }

    @JsonProperty("bidder_rank")
    public String getBidderRank() {
        return bidderRank;
    }

    @JsonProperty("bidder_rank")
    public void setBidderRank(String bidderRank) {
        this.bidderRank = bidderRank;
    }

    public CGX0TTUC3T withBidderRank(String bidderRank) {
        this.bidderRank = bidderRank;
        return this;
    }

    @JsonProperty("is_auction_winner")
    public Boolean getIsAuctionWinner() {
        return isAuctionWinner;
    }

    @JsonProperty("is_auction_winner")
    public void setIsAuctionWinner(Boolean isAuctionWinner) {
        this.isAuctionWinner = isAuctionWinner;
    }

    public CGX0TTUC3T withIsAuctionWinner(Boolean isAuctionWinner) {
        this.isAuctionWinner = isAuctionWinner;
        return this;
    }

    @JsonProperty("auction_winner_provider")
    public Integer getAuctionWinnerProvider() {
        return auctionWinnerProvider;
    }

    @JsonProperty("auction_winner_provider")
    public void setAuctionWinnerProvider(Integer auctionWinnerProvider) {
        this.auctionWinnerProvider = auctionWinnerProvider;
    }

    public CGX0TTUC3T withAuctionWinnerProvider(Integer auctionWinnerProvider) {
        this.auctionWinnerProvider = auctionWinnerProvider;
        return this;
    }

    @JsonProperty("auction_winner_instance")
    public Integer getAuctionWinnerInstance() {
        return auctionWinnerInstance;
    }

    @JsonProperty("auction_winner_instance")
    public void setAuctionWinnerInstance(Integer auctionWinnerInstance) {
        this.auctionWinnerInstance = auctionWinnerInstance;
    }

    public CGX0TTUC3T withAuctionWinnerInstance(Integer auctionWinnerInstance) {
        this.auctionWinnerInstance = auctionWinnerInstance;
        return this;
    }

    @JsonProperty("number_of_bid_requests")
    public Integer getNumberOfBidRequests() {
        return numberOfBidRequests;
    }

    @JsonProperty("number_of_bid_requests")
    public void setNumberOfBidRequests(Integer numberOfBidRequests) {
        this.numberOfBidRequests = numberOfBidRequests;
    }

    public CGX0TTUC3T withNumberOfBidRequests(Integer numberOfBidRequests) {
        this.numberOfBidRequests = numberOfBidRequests;
        return this;
    }

    @JsonProperty("number_of_bid_response")
    public Integer getNumberOfBidResponse() {
        return numberOfBidResponse;
    }

    @JsonProperty("number_of_bid_response")
    public void setNumberOfBidResponse(Integer numberOfBidResponse) {
        this.numberOfBidResponse = numberOfBidResponse;
    }

    public CGX0TTUC3T withNumberOfBidResponse(Integer numberOfBidResponse) {
        this.numberOfBidResponse = numberOfBidResponse;
        return this;
    }

    @JsonProperty("number_of_instances")
    public Integer getNumberOfInstances() {
        return numberOfInstances;
    }

    @JsonProperty("number_of_instances")
    public void setNumberOfInstances(Integer numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public CGX0TTUC3T withNumberOfInstances(Integer numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
        return this;
    }

    @JsonProperty("mediation_session_id")
    public String getMediationSessionId() {
        return mediationSessionId;
    }

    @JsonProperty("mediation_session_id")
    public void setMediationSessionId(String mediationSessionId) {
        this.mediationSessionId = mediationSessionId;
    }

    public CGX0TTUC3T withMediationSessionId(String mediationSessionId) {
        this.mediationSessionId = mediationSessionId;
        return this;
    }

    @JsonProperty("session_depth")
    public Integer getSessionDepth() {
        return sessionDepth;
    }

    @JsonProperty("session_depth")
    public void setSessionDepth(Integer sessionDepth) {
        this.sessionDepth = sessionDepth;
    }

    public CGX0TTUC3T withSessionDepth(Integer sessionDepth) {
        this.sessionDepth = sessionDepth;
        return this;
    }

    @JsonProperty("instance_manual_position")
    public String getInstanceManualPosition() {
        return instanceManualPosition;
    }

    @JsonProperty("instance_manual_position")
    public void setInstanceManualPosition(String instanceManualPosition) {
        this.instanceManualPosition = instanceManualPosition;
    }

    public CGX0TTUC3T withInstanceManualPosition(String instanceManualPosition) {
        this.instanceManualPosition = instanceManualPosition;
        return this;
    }

    @JsonProperty("instance_manual_type")
    public String getInstanceManualType() {
        return instanceManualType;
    }

    @JsonProperty("instance_manual_type")
    public void setInstanceManualType(String instanceManualType) {
        this.instanceManualType = instanceManualType;
    }

    public CGX0TTUC3T withInstanceManualType(String instanceManualType) {
        this.instanceManualType = instanceManualType;
        return this;
    }

    @JsonProperty("bidder_error_id")
    public String getBidderErrorId() {
        return bidderErrorId;
    }

    @JsonProperty("bidder_error_id")
    public void setBidderErrorId(String bidderErrorId) {
        this.bidderErrorId = bidderErrorId;
    }

    public CGX0TTUC3T withBidderErrorId(String bidderErrorId) {
        this.bidderErrorId = bidderErrorId;
        return this;
    }

    @JsonProperty("bidder_error_msg")
    public String getBidderErrorMsg() {
        return bidderErrorMsg;
    }

    @JsonProperty("bidder_error_msg")
    public void setBidderErrorMsg(String bidderErrorMsg) {
        this.bidderErrorMsg = bidderErrorMsg;
    }

    public CGX0TTUC3T withBidderErrorMsg(String bidderErrorMsg) {
        this.bidderErrorMsg = bidderErrorMsg;
        return this;
    }

    @JsonProperty("mediation_sdk_version")
    public String getMediationSdkVersion() {
        return mediationSdkVersion;
    }

    @JsonProperty("mediation_sdk_version")
    public void setMediationSdkVersion(String mediationSdkVersion) {
        this.mediationSdkVersion = mediationSdkVersion;
    }

    public CGX0TTUC3T withMediationSdkVersion(String mediationSdkVersion) {
        this.mediationSdkVersion = mediationSdkVersion;
        return this;
    }

    @JsonProperty("device_id")
    public String getDeviceId() {
        return deviceId;
    }

    @JsonProperty("device_id")
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public CGX0TTUC3T withDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @JsonProperty("advertising_id_type")
    public String getAdvertisingIdType() {
        return advertisingIdType;
    }

    @JsonProperty("advertising_id_type")
    public void setAdvertisingIdType(String advertisingIdType) {
        this.advertisingIdType = advertisingIdType;
    }

    public CGX0TTUC3T withAdvertisingIdType(String advertisingIdType) {
        this.advertisingIdType = advertisingIdType;
        return this;
    }

    @JsonProperty("is_limit_ad_tracking")
    public Boolean getIsLimitAdTracking() {
        return isLimitAdTracking;
    }

    @JsonProperty("is_limit_ad_tracking")
    public void setIsLimitAdTracking(Boolean isLimitAdTracking) {
        this.isLimitAdTracking = isLimitAdTracking;
    }

    public CGX0TTUC3T withIsLimitAdTracking(Boolean isLimitAdTracking) {
        this.isLimitAdTracking = isLimitAdTracking;
        return this;
    }

    @JsonProperty("ip")
    public String getIp() {
        return ip;
    }

    @JsonProperty("ip")
    public void setIp(String ip) {
        this.ip = ip;
    }

    public CGX0TTUC3T withIp(String ip) {
        this.ip = ip;
        return this;
    }

    @JsonProperty("device_country_code")
    public String getDeviceCountryCode() {
        return deviceCountryCode;
    }

    @JsonProperty("device_country_code")
    public void setDeviceCountryCode(String deviceCountryCode) {
        this.deviceCountryCode = deviceCountryCode;
    }

    public CGX0TTUC3T withDeviceCountryCode(String deviceCountryCode) {
        this.deviceCountryCode = deviceCountryCode;
        return this;
    }

    @JsonProperty("user_agent")
    public String getUserAgent() {
        return userAgent;
    }

    @JsonProperty("user_agent")
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public CGX0TTUC3T withUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    @JsonProperty("device_os")
    public String getDeviceOs() {
        return deviceOs;
    }

    @JsonProperty("device_os")
    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public CGX0TTUC3T withDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
        return this;
    }

    @JsonProperty("device_type")
    public String getDeviceType() {
        return deviceType;
    }

    @JsonProperty("device_type")
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public CGX0TTUC3T withDeviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    @JsonProperty("device_os_version")
    public String getDeviceOsVersion() {
        return deviceOsVersion;
    }

    @JsonProperty("device_os_version")
    public void setDeviceOsVersion(String deviceOsVersion) {
        this.deviceOsVersion = deviceOsVersion;
    }

    public CGX0TTUC3T withDeviceOsVersion(String deviceOsVersion) {
        this.deviceOsVersion = deviceOsVersion;
        return this;
    }

    @JsonProperty("application_user_id")
    public String getApplicationUserId() {
        return applicationUserId;
    }

    @JsonProperty("application_user_id")
    public void setApplicationUserId(String applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public CGX0TTUC3T withApplicationUserId(String applicationUserId) {
        this.applicationUserId = applicationUserId;
        return this;
    }

    @JsonProperty("app_bundle_id")
    public String getAppBundleId() {
        return appBundleId;
    }

    @JsonProperty("app_bundle_id")
    public void setAppBundleId(String appBundleId) {
        this.appBundleId = appBundleId;
    }

    public CGX0TTUC3T withAppBundleId(String appBundleId) {
        this.appBundleId = appBundleId;
        return this;
    }

    @JsonProperty("application_version")
    public String getApplicationVersion() {
        return applicationVersion;
    }

    @JsonProperty("application_version")
    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public CGX0TTUC3T withApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
        return this;
    }

    @JsonProperty("application_user_age")
    public Integer getApplicationUserAge() {
        return applicationUserAge;
    }

    @JsonProperty("application_user_age")
    public void setApplicationUserAge(Integer applicationUserAge) {
        this.applicationUserAge = applicationUserAge;
    }

    public CGX0TTUC3T withApplicationUserAge(Integer applicationUserAge) {
        this.applicationUserAge = applicationUserAge;
        return this;
    }

    @JsonProperty("application_user_gender")
    public String getApplicationUserGender() {
        return applicationUserGender;
    }

    @JsonProperty("application_user_gender")
    public void setApplicationUserGender(String applicationUserGender) {
        this.applicationUserGender = applicationUserGender;
    }

    public CGX0TTUC3T withApplicationUserGender(String applicationUserGender) {
        this.applicationUserGender = applicationUserGender;
        return this;
    }

    @JsonProperty("client_timestamp")
    public String getClientTimestamp() {
        return clientTimestamp;
    }

    @JsonProperty("client_timestamp")
    public void setClientTimestamp(String clientTimestamp) {
        this.clientTimestamp = clientTimestamp;
    }

    public CGX0TTUC3T withClientTimestamp(String clientTimestamp) {
        this.clientTimestamp = clientTimestamp;
        return this;
    }

    @JsonProperty("connection_type")
    public String getConnectionType() {
        return connectionType;
    }

    @JsonProperty("connection_type")
    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public CGX0TTUC3T withConnectionType(String connectionType) {
        this.connectionType = connectionType;
        return this;
    }

    @JsonProperty("screen_size_resolution_height")
    public Integer getScreenSizeResolutionHeight() {
        return screenSizeResolutionHeight;
    }

    @JsonProperty("screen_size_resolution_height")
    public void setScreenSizeResolutionHeight(Integer screenSizeResolutionHeight) {
        this.screenSizeResolutionHeight = screenSizeResolutionHeight;
    }

    public CGX0TTUC3T withScreenSizeResolutionHeight(Integer screenSizeResolutionHeight) {
        this.screenSizeResolutionHeight = screenSizeResolutionHeight;
        return this;
    }

    @JsonProperty("screen_size_resolution_width")
    public Integer getScreenSizeResolutionWidth() {
        return screenSizeResolutionWidth;
    }

    @JsonProperty("screen_size_resolution_width")
    public void setScreenSizeResolutionWidth(Integer screenSizeResolutionWidth) {
        this.screenSizeResolutionWidth = screenSizeResolutionWidth;
    }

    public CGX0TTUC3T withScreenSizeResolutionWidth(Integer screenSizeResolutionWidth) {
        this.screenSizeResolutionWidth = screenSizeResolutionWidth;
        return this;
    }

    @JsonProperty("device_make")
    public String getDeviceMake() {
        return deviceMake;
    }

    @JsonProperty("device_make")
    public void setDeviceMake(String deviceMake) {
        this.deviceMake = deviceMake;
    }

    public CGX0TTUC3T withDeviceMake(String deviceMake) {
        this.deviceMake = deviceMake;
        return this;
    }

    @JsonProperty("device_model")
    public String getDeviceModel() {
        return deviceModel;
    }

    @JsonProperty("device_model")
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public CGX0TTUC3T withDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
        return this;
    }

    @JsonProperty("carrier")
    public String getCarrier() {
        return carrier;
    }

    @JsonProperty("carrier")
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public CGX0TTUC3T withCarrier(String carrier) {
        this.carrier = carrier;
        return this;
    }

    @JsonProperty("event_timestamp")
    public String getEventTimestamp() {
        return eventTimestamp;
    }

    @JsonProperty("event_timestamp")
    public void setEventTimestamp(String eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public CGX0TTUC3T withEventTimestamp(String eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
        return this;
    }

    @JsonProperty("ab_internal")
    public String getAbInternal() {
        return abInternal;
    }

    @JsonProperty("ab_internal")
    public void setAbInternal(String abInternal) {
        this.abInternal = abInternal;
    }

    public CGX0TTUC3T withAbInternal(String abInternal) {
        this.abInternal = abInternal;
        return this;
    }

    @JsonProperty("is_test")
    public Boolean getIsTest() {
        return isTest;
    }

    @JsonProperty("is_test")
    public void setIsTest(Boolean isTest) {
        this.isTest = isTest;
    }

    public CGX0TTUC3T withIsTest(Boolean isTest) {
        this.isTest = isTest;
        return this;
    }

    @JsonProperty("kafka_partition")
    public Integer getKafkaPartition() {
        return kafkaPartition;
    }

    @JsonProperty("kafka_partition")
    public void setKafkaPartition(Integer kafkaPartition) {
        this.kafkaPartition = kafkaPartition;
    }

    public CGX0TTUC3T withKafkaPartition(Integer kafkaPartition) {
        this.kafkaPartition = kafkaPartition;
        return this;
    }

    @JsonProperty("kafka_offset")
    public Integer getKafkaOffset() {
        return kafkaOffset;
    }

    @JsonProperty("kafka_offset")
    public void setKafkaOffset(Integer kafkaOffset) {
        this.kafkaOffset = kafkaOffset;
    }

    public CGX0TTUC3T withKafkaOffset(Integer kafkaOffset) {
        this.kafkaOffset = kafkaOffset;
        return this;
    }

    @JsonProperty("mediation_sdk_version_numeric")
    public Integer getMediationSdkVersionNumeric() {
        return mediationSdkVersionNumeric;
    }

    @JsonProperty("mediation_sdk_version_numeric")
    public void setMediationSdkVersionNumeric(Integer mediationSdkVersionNumeric) {
        this.mediationSdkVersionNumeric = mediationSdkVersionNumeric;
    }

    public CGX0TTUC3T withMediationSdkVersionNumeric(Integer mediationSdkVersionNumeric) {
        this.mediationSdkVersionNumeric = mediationSdkVersionNumeric;
        return this;
    }

    @JsonProperty("external_mediation")
    public String getExternalMediation() {
        return externalMediation;
    }

    @JsonProperty("external_mediation")
    public void setExternalMediation(String externalMediation) {
        this.externalMediation = externalMediation;
    }

    public CGX0TTUC3T withExternalMediation(String externalMediation) {
        this.externalMediation = externalMediation;
        return this;
    }

    @JsonProperty("abt")
    public String getAbt() {
        return abt;
    }

    @JsonProperty("abt")
    public void setAbt(String abt) {
        this.abt = abt;
    }

    public CGX0TTUC3T withAbt(String abt) {
        this.abt = abt;
        return this;
    }

    @JsonProperty("event_day")
    public Integer getEventDay() {
        return eventDay;
    }

    @JsonProperty("event_day")
    public void setEventDay(Integer eventDay) {
        this.eventDay = eventDay;
    }

    public CGX0TTUC3T withEventDay(Integer eventDay) {
        this.eventDay = eventDay;
        return this;
    }

    @JsonProperty("hour")
    public Integer getHour() {
        return hour;
    }

    @JsonProperty("hour")
    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public CGX0TTUC3T withHour(Integer hour) {
        this.hour = hour;
        return this;
    }

    @JsonProperty("upsolver_schema_version")
    public Integer getUpsolverSchemaVersion() {
        return upsolverSchemaVersion;
    }

    @JsonProperty("upsolver_schema_version")
    public void setUpsolverSchemaVersion(Integer upsolverSchemaVersion) {
        this.upsolverSchemaVersion = upsolverSchemaVersion;
    }

    public CGX0TTUC3T withUpsolverSchemaVersion(Integer upsolverSchemaVersion) {
        this.upsolverSchemaVersion = upsolverSchemaVersion;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public CGX0TTUC3T withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(dwhCreateTimestamp).append(auctionStartTimestamp).append(auctionEndTimestamp).append(auctionId).append(auctionType).append(adUnitId).append(applicationId).append(providerId).append(providerName).append(instanceId).append(instanceTypeId).append(bidType).append(bidRequestId).append(bidRequestTimestamp).append(bidResponseTimestamp).append(bidPrice).append(suggestedBid).append(epsilon).append(clearance).append(bidderClearence).append(competitiveBid).append(bidBelow).append(rank).append(bidderRank).append(isAuctionWinner).append(auctionWinnerProvider).append(auctionWinnerInstance).append(numberOfBidRequests).append(numberOfBidResponse).append(numberOfInstances).append(mediationSessionId).append(sessionDepth).append(instanceManualPosition).append(instanceManualType).append(bidderErrorId).append(bidderErrorMsg).append(mediationSdkVersion).append(deviceId).append(advertisingIdType).append(isLimitAdTracking).append(ip).append(deviceCountryCode).append(userAgent).append(deviceOs).append(deviceType).append(deviceOsVersion).append(applicationUserId).append(appBundleId).append(applicationVersion).append(applicationUserAge).append(applicationUserGender).append(clientTimestamp).append(connectionType).append(screenSizeResolutionHeight).append(screenSizeResolutionWidth).append(deviceMake).append(deviceModel).append(carrier).append(eventTimestamp).append(abInternal).append(isTest).append(kafkaPartition).append(kafkaOffset).append(mediationSdkVersionNumeric).append(externalMediation).append(abt).append(eventDay).append(hour).append(upsolverSchemaVersion).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CGX0TTUC3T) == false) {
            return false;
        }
        CGX0TTUC3T rhs = ((CGX0TTUC3T) other);
        return new EqualsBuilder().append(dwhCreateTimestamp, rhs.dwhCreateTimestamp).append(auctionStartTimestamp, rhs.auctionStartTimestamp).append(auctionEndTimestamp, rhs.auctionEndTimestamp).append(auctionId, rhs.auctionId).append(auctionType, rhs.auctionType).append(adUnitId, rhs.adUnitId).append(applicationId, rhs.applicationId).append(providerId, rhs.providerId).append(providerName, rhs.providerName).append(instanceId, rhs.instanceId).append(instanceTypeId, rhs.instanceTypeId).append(bidType, rhs.bidType).append(bidRequestId, rhs.bidRequestId).append(bidRequestTimestamp, rhs.bidRequestTimestamp).append(bidResponseTimestamp, rhs.bidResponseTimestamp).append(bidPrice, rhs.bidPrice).append(suggestedBid, rhs.suggestedBid).append(epsilon, rhs.epsilon).append(clearance, rhs.clearance).append(bidderClearence, rhs.bidderClearence).append(competitiveBid, rhs.competitiveBid).append(bidBelow, rhs.bidBelow).append(rank, rhs.rank).append(bidderRank, rhs.bidderRank).append(isAuctionWinner, rhs.isAuctionWinner).append(auctionWinnerProvider, rhs.auctionWinnerProvider).append(auctionWinnerInstance, rhs.auctionWinnerInstance).append(numberOfBidRequests, rhs.numberOfBidRequests).append(numberOfBidResponse, rhs.numberOfBidResponse).append(numberOfInstances, rhs.numberOfInstances).append(mediationSessionId, rhs.mediationSessionId).append(sessionDepth, rhs.sessionDepth).append(instanceManualPosition, rhs.instanceManualPosition).append(instanceManualType, rhs.instanceManualType).append(bidderErrorId, rhs.bidderErrorId).append(bidderErrorMsg, rhs.bidderErrorMsg).append(mediationSdkVersion, rhs.mediationSdkVersion).append(deviceId, rhs.deviceId).append(advertisingIdType, rhs.advertisingIdType).append(isLimitAdTracking, rhs.isLimitAdTracking).append(ip, rhs.ip).append(deviceCountryCode, rhs.deviceCountryCode).append(userAgent, rhs.userAgent).append(deviceOs, rhs.deviceOs).append(deviceType, rhs.deviceType).append(deviceOsVersion, rhs.deviceOsVersion).append(applicationUserId, rhs.applicationUserId).append(appBundleId, rhs.appBundleId).append(applicationVersion, rhs.applicationVersion).append(applicationUserAge, rhs.applicationUserAge).append(applicationUserGender, rhs.applicationUserGender).append(clientTimestamp, rhs.clientTimestamp).append(connectionType, rhs.connectionType).append(screenSizeResolutionHeight, rhs.screenSizeResolutionHeight).append(screenSizeResolutionWidth, rhs.screenSizeResolutionWidth).append(deviceMake, rhs.deviceMake).append(deviceModel, rhs.deviceModel).append(carrier, rhs.carrier).append(eventTimestamp, rhs.eventTimestamp).append(abInternal, rhs.abInternal).append(isTest, rhs.isTest).append(kafkaPartition, rhs.kafkaPartition).append(kafkaOffset, rhs.kafkaOffset).append(mediationSdkVersionNumeric, rhs.mediationSdkVersionNumeric).append(externalMediation, rhs.externalMediation).append(abt, rhs.abt).append(eventDay, rhs.eventDay).append(hour, rhs.hour).append(upsolverSchemaVersion, rhs.upsolverSchemaVersion).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
