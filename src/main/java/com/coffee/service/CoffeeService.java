package com.coffee.service;

import com.coffee.factory.CoffeeFactory;
import com.coffee.model.Coffee;
import com.coffee.model.Order;
import com.coffee.strategy.Alipay;
import com.coffee.strategy.PaymentStrategy;
import com.coffee.strategy.WechatPay;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 咖啡服务类
 * 
 * 设计说明：
 * 这是业务逻辑层，负责处理咖啡订购的核心业务逻辑。
 * 使用内存存储订单（HashMap），无需数据库。
 * 
 * 【设计模式的运用】
 * 1. 工厂模式：通过CoffeeFactory创建咖啡对象
 * 2. 策略模式：根据支付类型选择对应的支付策略
 */
@Service
public class CoffeeService {
    
    // 使用HashMap存储订单，模拟数据库
    private final Map<String, Order> orderStore = new HashMap<>();
    
    /**
     * 创建订单并执行支付
     * 
     * 这个方法综合运用了工厂模式和策略模式：
     * 1. 使用工厂模式创建咖啡对象
     * 2. 使用策略模式执行支付
     * 
     * @param coffeeType 咖啡类型
     * @param size 尺寸
     * @param paymentType 支付类型
     * @return 订单对象
     */
    public Order createOrderAndPay(String coffeeType, String size, String paymentType) {
        // ========== 使用工厂模式创建咖啡对象 ==========
        // 客户端不需要知道具体是哪个咖啡类，只需要传入类型
        // 这体现了工厂模式的优势：封装对象创建逻辑
        Coffee coffee = CoffeeFactory.createCoffee(coffeeType);
        
        if (coffee == null) {
            throw new IllegalArgumentException("不支持的咖啡类型: " + coffeeType);
        }
        
        // 设置咖啡尺寸
        coffee.setSize(size);
        
        // 生成订单ID
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        
        // 创建订单对象
        Order order = new Order(orderId, coffee, size, paymentType);
        
        // ========== 使用策略模式执行支付 ==========
        // 根据支付类型获取对应的支付策略
        // 这体现了策略模式的优势：动态切换算法，消除if-else判断
        PaymentStrategy paymentStrategy = getPaymentStrategy(paymentType);
        
        // 执行支付
        String paymentResult = paymentStrategy.pay(order.getTotalAmount());
        
        // 更新订单状态
        order.setStatus("PAID");
        
        // 存储订单到内存
        orderStore.put(orderId, order);
        
        System.out.println("订单创建成功: " + order);
        System.out.println(paymentResult);
        
        return order;
    }
    
    /**
     * 根据支付类型获取对应的支付策略
     * 
     * 这是策略模式的关键方法，负责选择合适的支付策略。
     * 在更复杂的场景中，可以使用Spring的依赖注入自动管理策略。
     * 
     * @param paymentType 支付类型
     * @return 对应的支付策略对象
     */
    private PaymentStrategy getPaymentStrategy(String paymentType) {
        return switch (paymentType.toUpperCase()) {
            case "WECHAT" -> new WechatPay();   // 返回微信支付策略
            case "ALIPAY" -> new Alipay();       // 返回支付宝策略
            default -> throw new IllegalArgumentException("不支持的支付方式: " + paymentType);
        };
    }
    
    /**
     * 根据订单ID查询订单
     * 
     * @param orderId 订单ID
     * @return 订单对象
     */
    public Order getOrderById(String orderId) {
        return orderStore.get(orderId);
    }
    
    /**
     * 获取所有支持的咖啡类型
     * 
     * @return 咖啡类型数组
     */
    public String[] getCoffeeTypes() {
        return CoffeeFactory.getSupportedTypes();
    }
}
