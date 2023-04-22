package com.maibaduoduo.api.facade;

import com.maibaduoduo.api.annotation.FeignApi;
import feign.Headers;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;

@FeignApi(serviceUrl = "http://loclahost:8181")
public interface UserFacade {
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("GET /api/test/user")
    ResponseEntity<String> getUser();
}
