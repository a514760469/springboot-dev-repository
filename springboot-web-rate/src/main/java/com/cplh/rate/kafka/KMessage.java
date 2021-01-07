package com.cplh.rate.kafka;

import lombok.Data;

import java.util.Date;

/**
 * @author zhanglifeng
 * @since 2021-01-07
 */
@Data
public class KMessage {

    private Long id;

    private String message;

    private Date date;
}
