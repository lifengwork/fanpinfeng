package com.maibaduoduo.gateway.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Desc: //TODO
 * @Author: fanpingfeng
 * @Date 2023-4-21
 */
@AllArgsConstructor
public class HealthExamination implements IPing
{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean isAlive(Server server)
    {
        String url = "http://" + server.getId() + "/actuator/health";
        try
        {
            ResponseEntity<String> heath = restTemplate.getForEntity(url, String.class);
            if (heath.getStatusCode() == HttpStatus.OK)
            {
                System.out.println("ping " + url + " success and response is " + heath.getBody());
                return true;
            }
            System.out.println("ping " + url + " error and response is " + heath.getBody());
            return false;
        }
        catch (Exception e)
        {
            System.out.println("ping " + url + " failed");
            return false;
        }
    }
}