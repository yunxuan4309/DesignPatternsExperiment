package com.coffee.strategy;

/**
 * 支付宝支付策略实现
 * 
 * 设计说明：
 * 这是PaymentStrategy接口的具体实现之一，代表支付宝支付方式。
 * 在真实场景中，这里会调用支付宝的API进行支付处理。
 */
public class Alipay implements PaymentStrategy {
    
    @Override
    public String pay(double amount) {
        // 模拟支付宝支付流程
        System.out.println("=== 支付宝支付处理 ===");
        System.out.println("连接支付宝平台...");
        System.out.println("生成支付宝订单...");
        System.out.println("等待用户确认支付...");
        System.out.println("支付成功！");
        
        return String.format("支付宝支付成功，金额: ¥%.2f", amount);
    }
    
    @Override
    public String getPaymentName() {
        return "支付宝";
    }
}
