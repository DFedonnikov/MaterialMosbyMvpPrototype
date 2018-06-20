package com.prototype.fedonnikovds.materialmosbymvpprototype.service;


import com.prototype.fedonnikovds.materialmosbymvpprototype.model.UserInfo;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ILoginService {

    String SESSION_ROUTE = "/api/sessions";
    String HOST = "Host: uchebnik.mos.ru";
    String ACCEPT = "Accept: application/json";
    String COOKIE = "Cookie: ";
    String USER_AGENT = "User-Agent: mobile";

    @POST(SESSION_ROUTE)
    @Headers({
            HOST,
            ACCEPT,
            COOKIE,
            USER_AGENT
    })
    Flowable<UserInfo> login(@Body Map<String, String> body);

}
