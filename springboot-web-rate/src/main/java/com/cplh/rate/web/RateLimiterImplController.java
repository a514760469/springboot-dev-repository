package com.cplh.rate.web;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * @author zhanglifeng
 * @since 2020-03-12 15:35
 * 使用guava实现令牌桶
 */
@RestController
@RequestMapping("/guava")
public class RateLimiterImplController {

    private final static ExecutorService executor = Executors.newFixedThreadPool(10);

    private RateLimiter rateLimiter = RateLimiter.create(100);

    @RequestMapping("/acquire")
    public String acquire() {

        doProcess(() -> {
            System.out.println("开始处理 acquire");
            double acquire = rateLimiter.acquire(10);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Acquire success and wait: %s(s)\n", acquire);
        });

        return "SUCCESS";
    }


    private RateLimiter tryAcquireLimiter = RateLimiter.create(10);

    @RequestMapping("/tryAcquire")
    public String tryAcquire() {
        doProcess(() -> {
            System.out.println("开始处理 tryAcquire");
            if (tryAcquireLimiter.tryAcquire(90, TimeUnit.MILLISECONDS)) {
                System.out.println("Try acquire success 获取成功");
            } else {
                System.out.println("Try acquire failure 获取失败，丢弃请求。");
            }
        });
        return "SUCCESS";
    }

    /**
     * 需要设置“预热期”的方式
     */
    private RateLimiter warmingUpLimiter =  RateLimiter.create(10, 5, TimeUnit.SECONDS);

    @RequestMapping("/warmingUp")
    public String warmingUp() {
        doProcess(() -> {
            double acquire = warmingUpLimiter.acquire();
            System.out.printf("Acquire success and wait: %s(s), 当前：%s\n", acquire, System.currentTimeMillis() / 1000);
        });
        return "SUCCESS";
    }

    private static void doProcess(Runnable task) {

        Future<?> future = executor.submit(task);
        try {
            if (null != future.get()) {
                System.out.println("future.get()：" + future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}
