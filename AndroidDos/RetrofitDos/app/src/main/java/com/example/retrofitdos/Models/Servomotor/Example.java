package com.example.retrofitdos.Models.Servomotor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("license")
    @Expose
    private Object license;
    @SerializedName("history")
    @Expose
    private Boolean history;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("unit_type")
    @Expose
    private Object unitType;
    @SerializedName("unit_symbol")
    @Expose
    private Object unitSymbol;
    @SerializedName("last_value")
    @Expose
    private String lastValue;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("writable")
    @Expose
    private Boolean writable;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("groups")
    @Expose
    private List<Group_> groups = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getLicense() {
        return license;
    }

    public void setLicense(Object license) {
        this.license = license;
    }

    public Boolean getHistory() {
        return history;
    }

    public void setHistory(Boolean history) {
        this.history = history;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Object getUnitType() {
        return unitType;
    }

    public void setUnitType(Object unitType) {
        this.unitType = unitType;
    }

    public Object getUnitSymbol() {
        return unitSymbol;
    }

    public void setUnitSymbol(Object unitSymbol) {
        this.unitSymbol = unitSymbol;
    }

    public String getLastValue() {
        return lastValue;
    }

    public void setLastValue(String lastValue) {
        this.lastValue = lastValue;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getWritable() {
        return writable;
    }

    public void setWritable(Boolean writable) {
        this.writable = writable;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Group_> getGroups() {
        return groups;
    }

    public void setGroups(List<Group_> groups) {
        this.groups = groups;
    }
}
