package com.edge.seckill.execption;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
public class OrderException extends Exception{
    public OrderException(String msg) {
        super(msg);
    }

    public OrderException() {
        super();
    }
}
