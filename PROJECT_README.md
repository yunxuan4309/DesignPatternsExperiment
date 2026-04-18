# ☕ 咖啡订购系统 - 设计模式实验

## 📋 项目简介

这是一个基于 **工厂模式** 和 **策略模式** 的咖啡订购系统，用于展示设计模式在实际项目中的应用。

### 技术栈
- **后端**: Spring Boot 3.2.0 + Java 17
- **前端**: Vue 3 + Element Plus (通过CDN引入)
- **存储**: 内存存储 (HashMap)，无需数据库

---

## 🎯 使用的设计模式

### 1. 工厂模式 (Factory Pattern)
**位置**: `com.coffee.factory.CoffeeFactory`

**作用**: 根据咖啡类型创建对应的咖啡对象

**优势**:
- ✅ 封装对象创建逻辑
- ✅ 统一创建接口
- ✅ 符合开闭原则，易于扩展
- ✅ 降低客户端与具体类的耦合

**代码示例**:
```java
// 使用工厂模式创建咖啡
Coffee coffee = CoffeeFactory.createCoffee("LATTE");
```

### 2. 策略模式 (Strategy Pattern)
**位置**: `com.coffee.strategy.*`

**作用**: 根据不同的支付方式执行不同的支付逻辑

**优势**:
- ✅ 消除大量的if-else判断
- ✅ 每种支付策略独立，易于维护
- ✅ 运行时可动态切换策略
- ✅ 新增支付方式不需修改现有代码

**代码示例**:
```java
// 使用策略模式选择支付方式
PaymentStrategy strategy = new WechatPay();
strategy.pay(25.0);
```

---

## 📁 项目结构

```
DesignPatternsExperiment/
├── src/main/java/com/coffee/
│   ├── model/                    # 数据模型
│   │   ├── Coffee.java          # 咖啡基类
│   │   ├── Americano.java       # 美式咖啡
│   │   ├── Latte.java           # 拿铁咖啡
│   │   ├── Cappuccino.java      # 卡布奇诺
│   │   └── Order.java           # 订单类
│   │
│   ├── factory/                  # 【工厂模式】
│   │   └── CoffeeFactory.java   # 咖啡工厂类
│   │
│   ├── strategy/                 # 【策略模式】
│   │   ├── PaymentStrategy.java # 支付策略接口
│   │   ├── WechatPay.java       # 微信支付
│   │   └── Alipay.java          # 支付宝
│   │
│   ├── service/                  # 业务逻辑层
│   │   └── CoffeeService.java   # 咖啡服务（综合运用两种模式）
│   │
│   ├── controller/               # 控制器层
│   │   └── CoffeeController.java # REST API
│   │
│   ├── CoffeeShopApplication.java # Spring Boot启动类
│   └── DesignPatternDemo.java    # 设计模式演示程序
│
└── src/main/resources/
    ├── static/
    │   └── index.html            # 前端页面 (Vue3 + Element Plus)
    └── application.yml           # 配置文件
```

---

## 🚀 快速开始

### 1. 编译项目
```bash
mvn clean compile
```

### 2. 运行方式

#### 方式一：启动Web应用（推荐）
```bash
mvn spring-boot:run
```
访问: http://localhost:8080

#### 方式二：运行控制台演示
```bash
java -cp "target/classes" com.coffee.DesignPatternDemo
```

---

## 🌐 API接口说明

### 1. 获取咖啡类型列表
```
GET /api/coffees/types
```

**响应示例**:
```json
{
  "success": true,
  "data": ["AMERICANO", "LATTE", "CAPPUCCINO"]
}
```

### 2. 创建订单并支付
```
POST /api/order/create
Content-Type: application/json

{
  "coffeeType": "LATTE",
  "size": "MEDIUM",
  "paymentType": "WECHAT"
}
```

**响应示例**:
```json
{
  "success": true,
  "message": "订单创建成功",
  "data": {
    "orderId": "a1b2c3d4",
    "coffeeName": "拿铁咖啡",
    "size": "MEDIUM",
    "totalAmount": 24.0,
    "paymentType": "WECHAT",
    "status": "PAID"
  }
}
```

### 3. 查询订单详情
```
GET /api/order/{orderId}
```

---

## 📊 设计模式详细说明

### 工厂模式详解

**问题场景**:
如果不使用工厂模式，创建咖啡的代码会是：
```java
Coffee coffee;
if (type.equals("AMERICANO")) {
    coffee = new Americano();
} else if (type.equals("LATTE")) {
    coffee = new Latte();
} else if (type.equals("CAPPUCCINO")) {
    coffee = new Cappuccino();
}
```

**问题**:
- ❌ 客户端需要知道所有具体的咖啡类
- ❌ 每次新增咖啡类型都要修改多处代码
- ❌ 代码重复，难以维护

**解决方案**:
使用工厂模式后：
```java
Coffee coffee = CoffeeFactory.createCoffee(type);
```

**优势**:
- ✅ 客户端只需传入类型，不需要知道具体类
- ✅ 对象创建逻辑集中在工厂类
- ✅ 新增咖啡类型只需修改工厂类一处

---

### 策略模式详解

**问题场景**:
如果不使用策略模式，支付代码会是：
```java
if (paymentType.equals("WECHAT")) {
    // 微信支付逻辑（几十行代码）
    connectToWechatAPI();
    processPayment();
    // ...
} else if (paymentType.equals("ALIPAY")) {
    // 支付宝支付逻辑（几十行代码）
    connectToAlipayAPI();
    processPayment();
    // ...
}
```

**问题**:
- ❌ 代码臃肿，难以阅读
- ❌ 违反单一职责原则
- ❌ 新增支付方式需要修改已有代码（违反开闭原则）
- ❌ 难以单元测试

**解决方案**:
使用策略模式后：
```java
PaymentStrategy strategy = getPaymentStrategy(paymentType);
strategy.pay(amount);
```

**优势**:
- ✅ 每种支付方式独立成一个类
- ✅ 消除if-else判断
- ✅ 新增支付方式只需添加新类
- ✅ 易于单元测试

---

## 🎨 前端界面

前端使用 **Element Plus** 组件库，提供美观的用户界面：

- 📋 下拉框选择咖啡类型
- 📏 单选按钮组选择尺寸
- 💳 单选按钮组选择支付方式
- 📊 实时显示订单结果

---

## 📝 实验报告要点

### 1. 设计模式选择理由
- **工厂模式**: 适合处理多种相似对象的创建
- **策略模式**: 适合处理同一问题的多种不同解决方案

### 2. 设计前后对比
| 维度 | 不使用设计模式 | 使用设计模式 |
|------|---------------|-------------|
| 代码复杂度 | 高（大量if-else） | 低（清晰的结构） |
| 可维护性 | 差 | 好 |
| 可扩展性 | 差 | 好 |
| 可测试性 | 差 | 好 |

### 3. 符合的设计原则
- ✅ **开闭原则**: 对扩展开放，对修改关闭
- ✅ **单一职责原则**: 每个类只负责一项职责
- ✅ **依赖倒置原则**: 依赖抽象而非具体实现

---

## 🔧 如何扩展

### 添加新的咖啡类型
1. 创建新的咖啡类继承 `Coffee`
2. 在 `CoffeeFactory` 中添加新的case

```java
// 1. 创建新类
public class Mocha extends Coffee {
    public Mocha() {
        super("摩卡", 25.0);
    }
}

// 2. 在工厂中添加
case "MOCHA" -> new Mocha();
```

### 添加新的支付方式
1. 创建新的支付类实现 `PaymentStrategy`
2. 在 `getPaymentStrategy` 方法中添加新的case

```java
// 1. 创建新类
public class UnionPay implements PaymentStrategy {
    @Override
    public String pay(double amount) {
        return "银联支付成功，金额: ¥" + amount;
    }
    
    @Override
    public String getPaymentName() {
        return "银联支付";
    }
}

// 2. 在策略选择中添加
case "UNIONPAY" -> new UnionPay();
```

---

## 👨‍🎓 学习建议

1. **先运行演示程序**: 观察控制台输出，理解两种模式的工作方式
2. **查看关键代码**: 
   - `CoffeeFactory.java` - 工厂模式实现
   - `PaymentStrategy.java` - 策略模式接口
   - `CoffeeService.java` - 两种模式的综合运用
3. **尝试扩展**: 添加新的咖啡类型或支付方式
4. **绘制UML图**: 画出类图，加深理解

---

## 📞 技术支持

如有问题，请检查：
1. Java版本是否为17或更高
2. Maven是否正确安装
3. 端口8080是否被占用

---

## 📄 许可证

本项目仅用于学习和实验目的。
