package com.prototype.fedonnikovds.materialmosbymvpprototype.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Material {

    @SerializedName("material_id")
    int id;
    @SerializedName("ebook_id")
    String ebookId;
    @SerializedName("title")
    String title;
    @SerializedName("authors")
    String authors;
    @SerializedName("average_rating")
    Float rating;
    @SerializedName("accepted_at")
    Date acceptDate;
    @SerializedName("cover")
    MaterialCover cover;
}
