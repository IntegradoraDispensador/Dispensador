package com.example.retrofitdos.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    public Example(String value) {
        this.value = value;
    }

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("feed_id")
    @Expose
    private Integer feedId;
    @SerializedName("feed_key")
    @Expose
    private String feedKey;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("created_epoch")
    @Expose
    private Integer createdEpoch;
    @SerializedName("expiration")
    @Expose
    private String expiration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public String getFeedKey() {
        return feedKey;
    }

    public void setFeedKey(String feedKey) {
        this.feedKey = feedKey;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedEpoch() {
        return createdEpoch;
    }

    public void setCreatedEpoch(Integer createdEpoch) {
        this.createdEpoch = createdEpoch;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

}


