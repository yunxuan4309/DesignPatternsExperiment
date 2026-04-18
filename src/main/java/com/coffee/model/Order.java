package com.coffee.model;

import java.time.LocalDateTime;

/**
 * 订单类
 * 
 * 设计说明：
 * 用于存储订单信息，包括咖啡、尺寸、支付方式和订单状态。
 * 使用内存存储，不需要数据库。
 */
public class Order {
    private String orderId;              // 订单ID
    private Coffee coffee;               // 咖啡对象
    private String size;                 // 尺寸
    private String paymentType;          // 支付方式：WECHAT, ALIPAY
    private double totalAmount;          // 总金额
    private String status;               // 订单状态：PENDING, PAID, FAILED
    private LocalDateTime createTime;    // 创建时间

    public Order() {
        this.createTime = LocalDateTime.now();
        this.status = "PENDING";
    }

    public Order(String orderId, Coffee coffee, String size, String paymentType) {
        this();
        this.orderId = orderId;
        this.coffee = coffee;
        this.size = size;
        this.paymentType = paymentType;
        this.totalAmount = coffee.calculatePrice();
    }

    // Getter和Setter方法
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
