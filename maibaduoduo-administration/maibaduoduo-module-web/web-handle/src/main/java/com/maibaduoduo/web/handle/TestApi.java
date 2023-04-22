
package com.maibaduoduo.web.handle;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/test")
public class TestApi {
    @RequestMapping("/user")
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok().body("test api ");
    }
}
