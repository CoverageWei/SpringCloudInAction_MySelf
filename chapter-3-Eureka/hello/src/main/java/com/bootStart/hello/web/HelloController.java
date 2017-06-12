package com.bootStart.hello.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * Created by weiwei on 2017/6/10.
 */
@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger("HelloController");

    @Autowired
    private DiscoveryClient client;

    @Value("${book.name}")
    private String bookName;

    @Value("${book.desc}")
    private String bookDesc;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello, host:" + instance.getHost() + ", service_id" + instance.getServiceId());
        return "hello world" + String.format("[ %s - %s]", bookName, bookDesc);         // hello world[ SpringCloudInAction - inAction]
    }
}

