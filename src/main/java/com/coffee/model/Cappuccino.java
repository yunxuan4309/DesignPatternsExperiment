package com.coffee.model;

/**
 * 卡布奇诺咖啡
 * 
 * 设计说明：
 * 这是Coffee的具体实现类之一，代表卡布奇诺咖啡。
 */
public class Cappuccino extends Coffee {
    
    public Cappuccino() {
        super("卡布奇诺", 22.0);
        this.description = "丰富的奶泡与浓缩咖啡的经典搭配";
    }
}
