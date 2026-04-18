package com.coffee.model;

/**
 * 拿铁咖啡
 * 
 * 设计说明：
 * 这是Coffee的具体实现类之一，代表拿铁咖啡。
 */
public class Latte extends Coffee {
    
    public Latte() {
        super("拿铁咖啡", 20.0);
        this.description = "香浓牛奶与 espresso 的完美结合";
    }
}
