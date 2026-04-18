package com.coffee.model;

/**
 * 美式咖啡
 * 
 * 设计说明：
 * 这是Coffee的具体实现类之一，代表美式咖啡。
 * 每个具体的咖啡类只需要定义自己的名称和基础价格。
 */
public class Americano extends Coffee {
    
    public Americano() {
        super("美式咖啡", 15.0);
        this.description = "经典美式咖啡，浓郁香醇";
    }
}
