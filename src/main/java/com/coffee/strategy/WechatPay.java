package com.coffee.strategy;

/**
 * 微信支付策略实现
 * 
 * 设计说明：
 * 这是PaymentStrategy接口的具体实现之一，代表微信支付方式。
 * 在真实场景中，这里会调用微信支付的API进行支付处理。
 */
public class WechatPay implements PaymentStrategy {
    
    @Override
    public String pay(double amount) {
        // 模拟微信支付流程
        System.out.println("=== 微信支付处理 ===");
        System.out.println("连接微信支付平台...");
        System.out.println("生成微信支付订单...");
        System.out.println("等待用户确认支付...");
        System.out.println("支付成功！");
        
        return String.format("微信支付成功，金额: ¥%.2f", amount);
    }
    
    @Override
    public String getPaymentName() {
        return "微信支付";
    }
}
