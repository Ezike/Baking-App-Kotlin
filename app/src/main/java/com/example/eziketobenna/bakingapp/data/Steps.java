package com.example.eziketobenna.bakingapp.data;

import com.google.gson.annotations.SerializedName;

public class Steps {
    @SerializedName("videoURL")
    private String videoURL;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private int id;

    @SerializedName("shortDescription")
    private String shortDescription;

    @SerializedName("thumbnailURL")
    private String thumbnailURL;

    public Steps() {
    }

    public Steps(String videoURL, String description, int id, String shortDescription, String thumbnailURL) {
        this.videoURL = videoURL;
        this.description = description;
        this.id = id;
        this.shortDescription = shortDescription;
        this.thumbnailURL = thumbnailURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
