package com.payudon.common.entity;

import lombok.Data;

/**
 * @ClassName ResultData
 * @Description TODO
 * @Author pay
 * @DATE 2019/5/13 11:32
 **/
@Data
public class ResultData {
    private boolean success;
    private Object data;
    private String msg;
}
