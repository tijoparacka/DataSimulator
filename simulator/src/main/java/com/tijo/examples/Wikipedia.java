
package com.tijo.examples;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tijo.streaming.impl.domain.generic.GenericEvent;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;


/**
 * The Root Schema
 * <p>
 * The root schema comprises the entire JSON document.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "time",
    "added",
    "channel",
    "cityName",
    "comment",
    "commentLength",
    "countryIsoCode",
    "countryName",
    "deleted",
    "delta",
    "deltaBucket",
    "diffUrl",
    "flags",
    "isAnonymous",
    "isMinor",
    "isNew",
    "isRobot",
    "isUnpatrolled",
    "namespace",
    "page",
    "regionIsoCode",
    "regionName",
    "user"
})
public class Wikipedia extends GenericEvent {

    /**
     * The time Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("time")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String time = "";
    /**
     * The Added Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("added")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Integer added = 0;
    /**
     * The Channel Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("channel")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String channel = "";
    /**
     * The Cityname Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("cityName")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String cityName = "";
    /**
     * The Comment Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("comment")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String comment = "";
    /**
     * The Commentlength Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("commentLength")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Integer commentLength = 0;
    /**
     * The Countryisocode Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("countryIsoCode")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String countryIsoCode = "";
    /**
     * The Countryname Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("countryName")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String countryName = "";
    /**
     * The Deleted Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("deleted")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Integer deleted = 0;
    /**
     * The Delta Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("delta")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Integer delta = 0;
    /**
     * The Deltabucket Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("deltaBucket")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Integer deltaBucket = 0;
    /**
     * The Diffurl Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("diffUrl")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String diffUrl = "";
    /**
     * The Flags Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("flags")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String flags = "";
    /**
     * The Isanonymous Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isAnonymous")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String isAnonymous = "";
    /**
     * The Isminor Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isMinor")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String isMinor = "";
    /**
     * The Isnew Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isNew")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String isNew = "";
    /**
     * The Isrobot Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isRobot")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String isRobot = "";
    /**
     * The Isunpatrolled Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isUnpatrolled")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String isUnpatrolled = "";
    /**
     * The Namespace Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("namespace")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    @JsonIgnore
    private String namespace = "";
    /**
     * The Page Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("page")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String page = "";
    /**
     * The Regionisocode Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("regionIsoCode")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String regionIsoCode = "";
    /**
     * The Regionname Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("regionName")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String regionName = "";
    /**
     * The User Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("user")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String user = "";
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * The time Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    /**
     * The time Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    public Wikipedia withTime(String time) {
        this.time = time;
        return this;
    }

    /**
     * The Added Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("added")
    public Integer getAdded() {
        return added;
    }

    /**
     * The Added Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("added")
    public void setAdded(Integer added) {
        this.added = added;
    }

    public Wikipedia withAdded(Integer added) {
        this.added = added;
        return this;
    }

    /**
     * The Channel Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("channel")
    public String getChannel() {
        return channel;
    }

    /**
     * The Channel Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("channel")
    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Wikipedia withChannel(String channel) {
        this.channel = channel;
        return this;
    }

    /**
     * The Cityname Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("cityName")
    public String getCityName() {
        return cityName;
    }

    /**
     * The Cityname Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("cityName")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Wikipedia withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    /**
     * The Comment Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    /**
     * The Comment Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Wikipedia withComment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * The Commentlength Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("commentLength")
    public Integer getCommentLength() {
        return commentLength;
    }

    /**
     * The Commentlength Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("commentLength")
    public void setCommentLength(Integer commentLength) {
        this.commentLength = commentLength;
    }

    public Wikipedia withCommentLength(Integer commentLength) {
        this.commentLength = commentLength;
        return this;
    }

    /**
     * The Countryisocode Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("countryIsoCode")
    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    /**
     * The Countryisocode Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("countryIsoCode")
    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public Wikipedia withCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
        return this;
    }

    /**
     * The Countryname Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    /**
     * The Countryname Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("countryName")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Wikipedia withCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    /**
     * The Deleted Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("deleted")
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * The Deleted Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("deleted")
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Wikipedia withDeleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

    /**
     * The Delta Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("delta")
    public Integer getDelta() {
        return delta;
    }

    /**
     * The Delta Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("delta")
    public void setDelta(Integer delta) {
        this.delta = delta;
    }

    public Wikipedia withDelta(Integer delta) {
        this.delta = delta;
        return this;
    }

    /**
     * The Deltabucket Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("deltaBucket")
    public Integer getDeltaBucket() {
        return deltaBucket;
    }

    /**
     * The Deltabucket Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("deltaBucket")
    public void setDeltaBucket(Integer deltaBucket) {
        this.deltaBucket = deltaBucket;
    }

    public Wikipedia withDeltaBucket(Integer deltaBucket) {
        this.deltaBucket = deltaBucket;
        return this;
    }

    /**
     * The Diffurl Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("diffUrl")
    public String getDiffUrl() {
        return diffUrl;
    }

    /**
     * The Diffurl Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("diffUrl")
    public void setDiffUrl(String diffUrl) {
        this.diffUrl = diffUrl;
    }

    public Wikipedia withDiffUrl(String diffUrl) {
        this.diffUrl = diffUrl;
        return this;
    }

    /**
     * The Flags Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("flags")
    public String getFlags() {
        return flags;
    }

    /**
     * The Flags Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("flags")
    public void setFlags(String flags) {
        this.flags = flags;
    }

    public Wikipedia withFlags(String flags) {
        this.flags = flags;
        return this;
    }

    /**
     * The Isanonymous Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isAnonymous")
    public String getIsAnonymous() {
        return isAnonymous;
    }

    /**
     * The Isanonymous Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isAnonymous")
    public void setIsAnonymous(String isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public Wikipedia withIsAnonymous(String isAnonymous) {
        this.isAnonymous = isAnonymous;
        return this;
    }

    /**
     * The Isminor Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isMinor")
    public String getIsMinor() {
        return isMinor;
    }

    /**
     * The Isminor Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isMinor")
    public void setIsMinor(String isMinor) {
        this.isMinor = isMinor;
    }

    public Wikipedia withIsMinor(String isMinor) {
        this.isMinor = isMinor;
        return this;
    }

    /**
     * The Isnew Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isNew")
    public String getIsNew() {
        return isNew;
    }

    /**
     * The Isnew Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isNew")
    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public Wikipedia withIsNew(String isNew) {
        this.isNew = isNew;
        return this;
    }

    /**
     * The Isrobot Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isRobot")
    public String getIsRobot() {
        return isRobot;
    }

    /**
     * The Isrobot Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isRobot")
    public void setIsRobot(String isRobot) {
        this.isRobot = isRobot;
    }

    public Wikipedia withIsRobot(String isRobot) {
        this.isRobot = isRobot;
        return this;
    }

    /**
     * The Isunpatrolled Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isUnpatrolled")
    public String getIsUnpatrolled() {
        return isUnpatrolled;
    }

    /**
     * The Isunpatrolled Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("isUnpatrolled")
    public void setIsUnpatrolled(String isUnpatrolled) {
        this.isUnpatrolled = isUnpatrolled;
    }

    public Wikipedia withIsUnpatrolled(String isUnpatrolled) {
        this.isUnpatrolled = isUnpatrolled;
        return this;
    }

    /**
     * The Namespace Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("namespace")
    public String getNamespace() {
        return namespace;
    }

    /**
     * The Namespace Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("namespace")

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Wikipedia withNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    /**
     * The Page Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("page")
    public String getPage() {
        return page;
    }

    /**
     * The Page Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("page")
    public void setPage(String page) {
        this.page = page;
    }

    public Wikipedia withPage(String page) {
        this.page = page;
        return this;
    }

    /**
     * The Regionisocode Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("regionIsoCode")
    public String getRegionIsoCode() {
        return regionIsoCode;
    }

    /**
     * The Regionisocode Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("regionIsoCode")
    public void setRegionIsoCode(String regionIsoCode) {
        this.regionIsoCode = regionIsoCode;
    }

    public Wikipedia withRegionIsoCode(String regionIsoCode) {
        this.regionIsoCode = regionIsoCode;
        return this;
    }

    /**
     * The Regionname Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("regionName")
    public String getRegionName() {
        return regionName;
    }

    /**
     * The Regionname Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("regionName")
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Wikipedia withRegionName(String regionName) {
        this.regionName = regionName;
        return this;
    }

    /**
     * The User Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    /**
     * The User Schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     *
     */
    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    public Wikipedia withUser(String user) {
        this.user = user;
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

    public Wikipedia withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(time).append(added).append(channel).append(cityName).append(comment).append(commentLength).append(countryIsoCode).append(countryName).append(deleted).append(delta).append(deltaBucket).append(diffUrl).append(flags).append(isAnonymous).append(isMinor).append(isNew).append(isRobot).append(isUnpatrolled).append(namespace).append(page).append(regionIsoCode).append(regionName).append(user).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Wikipedia) == false) {
            return false;
        }
        Wikipedia rhs = ((Wikipedia) other);
        return new EqualsBuilder().append(time, rhs.time).append(added, rhs.added).append(channel, rhs.channel).append(cityName, rhs.cityName).append(comment, rhs.comment).append(commentLength, rhs.commentLength).append(countryIsoCode, rhs.countryIsoCode).append(countryName, rhs.countryName).append(deleted, rhs.deleted).append(delta, rhs.delta).append(deltaBucket, rhs.deltaBucket).append(diffUrl, rhs.diffUrl).append(flags, rhs.flags).append(isAnonymous, rhs.isAnonymous).append(isMinor, rhs.isMinor).append(isNew, rhs.isNew).append(isRobot, rhs.isRobot).append(isUnpatrolled, rhs.isUnpatrolled).append(namespace, rhs.namespace).append(page, rhs.page).append(regionIsoCode, rhs.regionIsoCode).append(regionName, rhs.regionName).append(user, rhs.user).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
