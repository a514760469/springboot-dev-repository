package com.cplh.rate.web;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhanglifeng
 * @since 2020-03-12 13:30
 * 漏桶实现
 */
@RestController
public class LeakyBucketController {

    public static final String SUCCESS = "SUCCESS";
    public static Map<String, Integer> MAP = null;
    /*** bucket size */
    public static final int BUCKET_SIZE = 1000;
    /*** minus size */
    public static final int MINUS_SIZE = 10;
    /*** current size identity */
    private static final String CURRENT_SIZE_IDENTITY = "CURRENT_SIZE_IDENTITY";

    @PostConstruct
    public void init() {
        //init container
        MAP = new ConcurrentHashMap<>(1);
        MAP.put(CURRENT_SIZE_IDENTITY, 0);
    }

    /**
     * execute once every 1 seconds
     * if it has executed ,current bucket size - MINUS_SIZE
     */
//    @Scheduled(cron = "0/1 * * * * ?")
    public void minus() {
        //get current bucket size
        Integer currentBucketSize = MAP.get(CURRENT_SIZE_IDENTITY);
        if (currentBucketSize >= MINUS_SIZE) {
            MAP.put(CURRENT_SIZE_IDENTITY, currentBucketSize - MINUS_SIZE);
        }
        System.out.println(currentBucketSize);

    }

    @RequestMapping("/leakyBucket")
    public String demoTest() {
        //get current bucket size
        Integer currentBucketSize = MAP.get(CURRENT_SIZE_IDENTITY);
        if (currentBucketSize > BUCKET_SIZE - 1) {
            System.out.println("sorry,request too fast!");
        } else {
            MAP.put(CURRENT_SIZE_IDENTITY, currentBucketSize + 1);
        }
        return SUCCESS;
    }
}
