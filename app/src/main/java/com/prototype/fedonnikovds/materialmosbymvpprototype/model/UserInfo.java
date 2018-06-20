package com.prototype.fedonnikovds.materialmosbymvpprototype.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfo {

    @SerializedName("authentication_token")
    private String authToken;

    @SerializedName("id")
    private String id;

    @SerializedName("profiles")
    private List<Profile> profiles;


    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}
