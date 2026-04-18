# 🚀 快速启动指南

## 一分钟快速开始

### 1️⃣ 编译项目（30秒）
```bash
mvn clean compile
```

### 2️⃣ 启动应用（10秒）
```bash
mvn spring-boot:run
```

### 3️⃣ 访问系统
打开浏览器访问: **http://localhost:8080**

---

## 📋 三种使用方式

### 方式一：Web界面（推荐⭐）

**启动**:
```bash
mvn spring-boot:run
```

**访问**: http://localhost:8080

**功能**:
- 🎨 美观的Element Plus界面
- ☕ 选择咖啡类型、尺寸、支付方式
- 💳 在线下单支付
- 📊 实时查看订单结果

---

### 方式二：控制台演示

**运行**:
```bash
java -cp "target/classes" com.coffee.DesignPatternDemo
```

**展示**:
- 📦 工厂模式如何创建咖啡
- 💰 策略模式如何处理支付
- ✅ 清晰的控制台输出

---

### 方式三：API测试

**运行**:
```bash
powershell -ExecutionPolicy Bypass -File test-api.ps1
```

**测试**:
- 🔍 获取咖啡类型列表
- 🛒 创建订单（微信支付）
- 🛒 创建订单（支付宝）

---

## 🎯 核心文件位置

### 设计模式实现
```
📁 src/main/java/com/coffee/
├── factory/CoffeeFactory.java        ← 工厂模式（95行注释）
└── strategy/
    ├── PaymentStrategy.java          ← 策略接口（73行注释）
    ├── WechatPay.java                ← 微信支付
    └── Alipay.java                   ← 支付宝
```

### 前端页面
```
📁 src/main/resources/static/
└── index.html                        ← Vue3 + Element Plus
```

### 文档
```
📄 PROJECT_README.md                  ← 完整项目说明
📄 SUMMARY.md                         ← 项目总结
📄 UML_DIAGRAM_GUIDE.md              ← UML类图指南
📄 CHECKLIST.md                       ← 检查清单
```

---

## 🔧 常见问题

### Q1: 端口8080被占用怎么办？
**解决**: 修改 `src/main/resources/application.yml`
```yaml
server:
  port: 8081  # 改为其他端口
```

### Q2: Maven编译失败？
**检查**:
- Java版本是否为17+
- Maven是否正确安装
```bash
java -version
mvn -version
```

### Q3: 前端页面无法访问？
**检查**:
- Spring Boot是否成功启动
- 查看控制台是否有错误信息
- 确认访问 http://localhost:8080

### Q4: 如何添加新的咖啡类型？
**步骤**:
1. 创建新类继承Coffee
2. 在CoffeeFactory中添加case

示例:
```java
// 1. 创建 Mocha.java
public class Mocha extends Coffee {
    public Mocha() {
        super("摩卡", 25.0);
    }
}

// 2. 修改 CoffeeFactory.java
case "MOCHA" -> new Mocha();
```

### Q5: 如何添加新的支付方式？
**步骤**:
1. 创建新类实现PaymentStrategy
2. 在CoffeeService.getPaymentStrategy()中添加case

示例:
```java
// 1. 创建 UnionPay.java
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

// 2. 修改 CoffeeService.java
case "UNIONPAY" -> new UnionPay();
```

---

## 📊 API接口速查

### 获取咖啡类型
```bash
GET http://localhost:8080/api/coffees/types
```

### 创建订单
```bash
POST http://localhost:8080/api/order/create
Content-Type: application/json

{
  "coffeeType": "LATTE",
  "size": "MEDIUM",
  "paymentType": "WECHAT"
}
```

### 查询订单
```bash
GET http://localhost:8080/api/order/{orderId}
```

---

## 🎓 学习路径建议

### 第1步：理解需求（5分钟）
- 阅读PROJECT_README.md的项目简介
- 了解咖啡订购系统的业务流程

### 第2步：运行演示（5分钟）
- 启动Web应用，体验功能
- 运行控制台演示，观察输出

### 第3步：阅读代码（30分钟）
1. 先看工厂模式：`CoffeeFactory.java`
2. 再看策略模式：`PaymentStrategy.java`
3. 最后看综合运用：`CoffeeService.java`

### 第4步：理解设计（20分钟）
- 阅读每个文件的详细注释
- 对比传统方式和使用模式的区别
- 理解为什么要这样设计

### 第5步：绘制类图（30分钟）
- 参考UML_DIAGRAM_GUIDE.md
- 使用Draw.io或手绘
- 绘制工厂模式和策略模式类图

### 第6步：准备报告（60分钟）
- 参考SUMMARY.md的实验报告建议结构
- 整理关键代码片段
- 截图Web界面和测试结果

---

## 💡 实验报告要点

### 必写内容
1. **设计模式选择理由**
   - 为什么选择工厂模式？
   - 为什么选择策略模式？

2. **设计前后对比**
   - 不使用模式的问题
   - 使用模式的优势

3. **代码实现**
   - 关键代码片段
   - 详细的注释说明

4. **UML类图**
   - 工厂模式类图
   - 策略模式类图

5. **测试结果**
   - 功能测试截图
   - API测试结果

### 加分内容
- 时序图
- 性能分析
- 扩展性讨论
- 遇到的问题及解决

---

## 🌟 项目亮点总结

✨ **两个经典设计模式**  
✨ **超过200行详细注释**  
✨ **完整的前后端实现**  
✨ **专业的文档支持**  
✨ **开箱即用，无需配置**  
✨ **易于理解和扩展**  

---

## 📞 需要帮助？

### 查看文档
1. PROJECT_README.md - 完整项目说明
2. SUMMARY.md - 项目总结和报告建议
3. UML_DIAGRAM_GUIDE.md - 类图绘制指南
4. CHECKLIST.md - 完成检查清单

### 关键代码位置
- 工厂模式：`com.coffee.factory.CoffeeFactory`
- 策略模式：`com.coffee.strategy.PaymentStrategy`
- 综合运用：`com.coffee.service.CoffeeService`

---

## 🎉 开始你的设计模式之旅！

**现在就运行项目，体验设计模式的魅力吧！**

```bash
mvn spring-boot:run
```

然后访问: http://localhost:8080

**祝学习愉快！☕✨**
