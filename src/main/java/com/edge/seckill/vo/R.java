package com.edge.seckill.vo;

import lombok.Data;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
@Data
public class R {
    private int code;
    private String msg;
    private Object data;

    public R(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public R() {}

    public static R ok() {
        return new R(0, "OK", null);
    }
    public static R ok(Object data) {
        return new R(0, "OK", data);
    }
    public static R fail() {
        return new R(1, "ERROR", null);
    }
    public static R fail(String msg) {
        return new R(1, msg, null);
    }
}
