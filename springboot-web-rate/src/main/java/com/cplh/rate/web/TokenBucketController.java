package com.cplh.rate.web;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhanglifeng
 * @since 2020-03-12 15:02
 * 令牌桶算法实现
 */
@RestController
public class TokenBucketController {

    private static Map<String, Integer> MAP = null;
    /*** bucket size */
    private static final int BUCKET_SIZE = 10000;
    /*** add size */
    private static final int ADD_SIZE = 100;
    /*** current size identity */
    private static final String CURRENT_SIZE_IDENTITY = "CURRENT_SIZE_IDENTITY";

    @PostConstruct
    public void init() {
        //init container
        MAP = new ConcurrentHashMap<>(1);
        MAP.put(CURRENT_SIZE_IDENTITY, 1000);
    }

    /**
     * execute once every 1 seconds
     * if it has executed ,current bucket size - MINUS_SIZE
     */
//    @Scheduled(cron = "0/1 * * * * ?")
    public void minus() {
        //get current bucket size
        Integer currentBucketSize = MAP.get(CURRENT_SIZE_IDENTITY);
        // currentBucketSize <= 9900
        if (currentBucketSize <= BUCKET_SIZE - ADD_SIZE) {
            MAP.put(CURRENT_SIZE_IDENTITY, currentBucketSize + ADD_SIZE);
        }
        System.out.println(currentBucketSize);

    }

    @RequestMapping("/tokenBucket")
    public String demoTest() {
        //get current bucket size
        Integer currentBucketSize = MAP.get(CURRENT_SIZE_IDENTITY);
        if (currentBucketSize == 0) {
            System.out.println("sorry,request too fast!");
        } else {
            MAP.put(CURRENT_SIZE_IDENTITY, currentBucketSize - 1);
        }
        return "SUCCESS";
    }

    @RequestMapping("/rest")
    public String rest() {
        return "SUCCESS";
    }

}
