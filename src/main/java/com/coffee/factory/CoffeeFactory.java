package com.coffee.factory;

import com.coffee.model.Americano;
import com.coffee.model.Cappuccino;
import com.coffee.model.Coffee;
import com.coffee.model.Latte;

/**
 * ============================================================================
 * 咖啡工厂类 - 工厂模式 (Factory Pattern) 的实现
 * ============================================================================
 * 
 * 【设计模式说明】
 * 工厂模式是一种创建型设计模式，它提供了一种创建对象的最佳方式。
 * 
 * 【为什么要使用工厂模式？】
 * 1. 封装对象创建逻辑：客户端不需要知道具体类的实例化过程
 * 2. 统一创建接口：通过一个方法创建不同类型的对象
 * 3. 符合开闭原则：新增咖啡类型时，只需修改工厂类，不需修改客户端代码
 * 4. 降低耦合：客户端只依赖抽象的Coffee类，不依赖具体实现类
 * 
 * 【传统方式的问题】
 * 如果不使用工厂模式，客户端代码会是这样：
 *   if (type.equals("AMERICANO")) {
 *       coffee = new Americano();
 *   } else if (type.equals("LATTE")) {
 *       coffee = new Latte();
 *   }
 * 这样会导致：
 * - 客户端需要知道所有具体的咖啡类
 * - 每次新增咖啡类型都要修改多处代码
 * - 代码重复，难以维护
 * 
 * 【工厂模式的优势】
 * 使用工厂后，客户端只需要：
 *   Coffee coffee = CoffeeFactory.createCoffee("AMERICANO");
 * 简洁明了，易于扩展！
 * 
 * 【应用场景】
 * - 当一个类不知道它所必须创建的对象的类的时候
 * - 当一个类希望由它的子类来指定它所创建的对象的时候
 * - 当将创建对象的职责委托给多个帮助子类中的某一个时
 * ============================================================================
 */
public class CoffeeFactory {
    
    /**
     * 根据咖啡类型创建对应的咖啡对象
     * 
     * 这是工厂模式的核心方法，负责根据传入的类型参数创建相应的咖啡对象。
     * 客户端无需关心具体是如何创建的，只需要传入类型即可。
     * 
     * @param coffeeType 咖啡类型枚举值：AMERICANO, LATTE, CAPPUCCINO
     * @return 对应的Coffee对象，如果类型不支持则返回null
     */
    public static Coffee createCoffee(String coffeeType) {
        if (coffeeType == null) {
            return null;
        }
        
        // 根据类型创建不同的咖啡对象
        // 这里体现了工厂模式的核心思想：将对象创建逻辑集中管理
        return switch (coffeeType.toUpperCase()) {
            case "AMERICANO" -> {
                // 创建美式咖啡
                yield new Americano();
            }
            case "LATTE" -> {
                // 创建拿铁咖啡
                yield new Latte();
            }
            case "CAPPUCCINO" -> {
                // 创建卡布奇诺咖啡
                yield new Cappuccino();
            }
            default -> {
                // 未知类型，返回null
                System.out.println("未知的咖啡类型: " + coffeeType);
                yield null;
            }
        };
    }
    
    /**
     * 获取所有支持的咖啡类型列表
     * 
     * 这个方法用于前端展示可选的咖啡类型。
     * 
     * @return 咖啡类型数组
     */
    public static String[] getSupportedTypes() {
        return new String[]{"AMERICANO", "LATTE", "CAPPUCCINO"};
    }
}
