package com.coffee.controller;

import com.coffee.model.Order;
import com.coffee.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 咖啡控制器
 * 
 * 设计说明：
 * 这是REST API控制器，提供前后端交互的接口。
 * 使用Spring Boot的注解简化API开发。
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // 允许跨域访问
public class CoffeeController {
    
    @Autowired
    private CoffeeService coffeeService;
    
    /**
     * 获取所有支持的咖啡类型
     * 
     * GET /api/coffees/types
     * 
     * @return 咖啡类型列表
     */
    @GetMapping("/coffees/types")
    public Map<String, Object> getCoffeeTypes() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", coffeeService.getCoffeeTypes());
        return response;
    }
    
    /**
     * 创建订单并支付
     * 
     * POST /api/order/create
     * 
     * 请求体示例：
     * {
     *   "coffeeType": "LATTE",
     *   "size": "MEDIUM",
     *   "paymentType": "WECHAT"
     * }
     * 
     * @param request 订单请求参数
     * @return 订单结果
     */
    @PostMapping("/order/create")
    public Map<String, Object> createOrder(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 获取请求参数
            String coffeeType = request.get("coffeeType");
            String size = request.get("size");
            String paymentType = request.get("paymentType");
            
            // 参数验证
            if (coffeeType == null || size == null || paymentType == null) {
                response.put("success", false);
                response.put("message", "参数不完整");
                return response;
            }
            
            // 调用服务层创建订单并支付
            Order order = coffeeService.createOrderAndPay(coffeeType, size, paymentType);
            
            // 返回成功响应
            response.put("success", true);
            response.put("message", "订单创建成功");
            response.put("data", Map.of(
                "orderId", order.getOrderId(),
                "coffeeName", order.getCoffee().getName(),
                "size", order.getSize(),
                "totalAmount", order.getTotalAmount(),
                "paymentType", order.getPaymentType(),
                "status", order.getStatus()
            ));
            
        } catch (IllegalArgumentException e) {
            // 参数错误
            response.put("success", false);
            response.put("message", e.getMessage());
        } catch (Exception e) {
            // 其他错误
            response.put("success", false);
            response.put("message", "系统错误: " + e.getMessage());
            e.printStackTrace();
        }
        
        return response;
    }
    
    /**
     * 查询订单详情
     * 
     * GET /api/order/{orderId}
     * 
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("/order/{orderId}")
    public Map<String, Object> getOrder(@PathVariable String orderId) {
        Map<String, Object> response = new HashMap<>();
        
        Order order = coffeeService.getOrderById(orderId);
        
        if (order != null) {
            response.put("success", true);
            response.put("data", Map.of(
                "orderId", order.getOrderId(),
                "coffeeName", order.getCoffee().getName(),
                "size", order.getSize(),
                "totalAmount", order.getTotalAmount(),
                "paymentType", order.getPaymentType(),
                "status", order.getStatus(),
                "createTime", order.getCreateTime().toString()
            ));
        } else {
            response.put("success", false);
            response.put("message", "订单不存在");
        }
        
        return response;
    }
}
