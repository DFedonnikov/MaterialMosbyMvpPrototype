package com.prototype.fedonnikovds.materialmosbymvpprototype.service;

import com.prototype.fedonnikovds.materialmosbymvpprototype.model.Material;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IMaterialService {

    String MATERIALS_URL = "https://uchebnik.mos.ru/cms/api/materials";


    @GET(MATERIALS_URL)
    @Headers({"accept: application/vnd.cms-v3+json"})
    Flowable<List<Material>> getMaterialsList(@HeaderMap Map <String, String> headers, @Query("book_type_ids") String bookType);
}
