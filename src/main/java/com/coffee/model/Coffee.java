package com.coffee.model;

/**
 * 咖啡基类
 * 
 * 设计说明：
 * 这是所有咖啡类型的抽象基类，定义了咖啡的基本属性和方法。
 * 具体的咖啡类型（美式、拿铁、卡布奇诺）将继承此类。
 */
public abstract class Coffee {
    protected String name;        // 咖啡名称
    protected double basePrice;   // 基础价格
    protected String size;        // 尺寸：SMALL, MEDIUM, LARGE
    protected String description; // 描述

    public Coffee(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    /**
     * 计算最终价格（考虑尺寸因素）
     * 小杯：基础价格
     * 中杯：基础价格 * 1.2
     * 大杯：基础价格 * 1.5
     */
    public double calculatePrice() {
        double multiplier = switch (size) {
            case "SMALL" -> 1.0;
            case "MEDIUM" -> 1.2;
            case "LARGE" -> 1.5;
            default -> 1.0;
        };
        return basePrice * multiplier;
    }

    // Getter和Setter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - ¥%.2f", name, size, calculatePrice());
    }
}
