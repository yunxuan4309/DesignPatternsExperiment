package com.coffee;

import com.coffee.factory.CoffeeFactory;
import com.coffee.model.Coffee;
import com.coffee.strategy.Alipay;
import com.coffee.strategy.PaymentStrategy;
import com.coffee.strategy.WechatPay;

/**
 * 设计模式演示类
 * 
 * 这个类用于在控制台演示工厂模式和策略模式的使用。
 * 可以直接运行这个类的main方法来查看设计模式的效果。
 */
public class DesignPatternDemo {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   设计模式实验演示");
        System.out.println("========================================\n");
        
        // ========== 演示工厂模式 ==========
        System.out.println("【1】工厂模式演示 - 创建不同类型的咖啡");
        System.out.println("----------------------------------------");
        
        // 使用工厂模式创建不同的咖啡对象
        // 优势：客户端不需要知道具体的类名，只需要传入类型即可
        Coffee americano = CoffeeFactory.createCoffee("AMERICANO");
        americano.setSize("MEDIUM");
        System.out.println("创建的咖啡: " + americano);
        
        Coffee latte = CoffeeFactory.createCoffee("LATTE");
        latte.setSize("LARGE");
        System.out.println("创建的咖啡: " + latte);
        
        Coffee cappuccino = CoffeeFactory.createCoffee("CAPPUCCINO");
        cappuccino.setSize("SMALL");
        System.out.println("创建的咖啡: " + cappuccino);
        
        System.out.println("\n工厂模式的优势：");
        System.out.println("✓ 统一的创建接口");
        System.out.println("✓ 封装了对象创建逻辑");
        System.out.println("✓ 符合开闭原则，易于扩展\n");
        
        
        // ========== 演示策略模式 ==========
        System.out.println("\n【2】策略模式演示 - 不同的支付方式");
        System.out.println("----------------------------------------");
        
        double amount = 25.0;
        
        // 使用微信支付策略
        PaymentStrategy wechatPay = new WechatPay();
        System.out.println("\n选择支付方式: " + wechatPay.getPaymentName());
        String wechatResult = wechatPay.pay(amount);
        System.out.println("支付结果: " + wechatResult);
        
        // 使用支付宝策略
        PaymentStrategy alipay = new Alipay();
        System.out.println("\n选择支付方式: " + alipay.getPaymentName());
        String alipayResult = alipay.pay(amount);
        System.out.println("支付结果: " + alipayResult);
        
        System.out.println("\n策略模式的优势：");
        System.out.println("✓ 消除了if-else判断");
        System.out.println("✓ 每种策略独立，易于维护");
        System.out.println("✓ 运行时可动态切换策略");
        System.out.println("✓ 符合开闭原则，新增策略不需修改现有代码\n");
        
        
        System.out.println("========================================");
        System.out.println("   演示完成！");
        System.out.println("========================================");
    }
}
