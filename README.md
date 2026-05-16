kye# ☕ Coffee Shop System - Design Patterns Experiment

## 📋 Project Introduction

This is a coffee ordering system based on **Factory Pattern** and **Strategy Pattern**, designed to demonstrate the practical application of design patterns in software development. The system allows users to order different types of coffee with various sizes and payment methods.

### Features
- Multiple coffee types (Americano, Latte, Cappuccino)
- Different size options (Small, Medium, Large)
- Multiple payment strategies (WeChat Pay, Alipay)
- RESTful API interface
- Modern web UI with Vue 3 and Element Plus
- In-memory storage (no database required)

---

## 🎯 Design Patterns Used

### 1. Factory Pattern
**Location**: `com.coffee.factory.CoffeeFactory`

**Purpose**: Creates coffee objects based on the selected coffee type

**Benefits**:
- ✅ Encapsulates object creation logic
- ✅ Provides a unified creation interface
- ✅ Follows Open/Closed Principle for easy extension
- ✅ Reduces coupling between client and concrete classes

**Code Example**:
```java
// Using factory pattern to create coffee
Coffee coffee = CoffeeFactory.createCoffee("LATTE");
```

### 2. Strategy Pattern
**Location**: `com.coffee.strategy.*`

**Purpose**: Executes different payment logic based on the selected payment method

**Benefits**:
- ✅ Eliminates大量 if-else statements
- ✅ Each payment strategy is independent and maintainable
- ✅ Strategies can be dynamically switched at runtime
- ✅ New payment methods can be added without modifying existing code

**Code Example**:
```java
// Using strategy pattern to select payment method
PaymentStrategy strategy = new WechatPay();
strategy.pay(25.0);
```

---

## 🛠️ Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Maven** for dependency management

### Frontend
- **Vue 3** (via CDN)
- **Element Plus** UI component library (via CDN)
- **Axios** for HTTP requests

### Storage
- **In-memory HashMap** (no external database required)

---

## 📁 Project Structure

```
DesignPatternsExperiment/
├── src/main/java/com/coffee/
│   ├── model/                    # Data models
│   │   ├── Coffee.java          # Base coffee class
│   │   ├── Americano.java       # Americano coffee
│   │   ├── Latte.java           # Latte coffee
│   │   ├── Cappuccino.java      # Cappuccino coffee
│   │   └── Order.java           # Order class
│   │
│   ├── factory/                  # [Factory Pattern]
│   │   └── CoffeeFactory.java   # Coffee factory class
│   │
│   ├── strategy/                 # [Strategy Pattern]
│   │   ├── PaymentStrategy.java # Payment strategy interface
│   │   ├── WechatPay.java       # WeChat Pay implementation
│   │   └── Alipay.java          # Alipay implementation
│   │
│   ├── service/                  # Business logic layer
│   │   └── CoffeeService.java   # Coffee service (combines both patterns)
│   │
│   ├── controller/               # Controller layer
│   │   └── CoffeeController.java # REST API endpoints
│   │
│   ├── CoffeeShopApplication.java # Spring Boot application starter
│   └── DesignPatternDemo.java    # Design pattern demonstration
│
└── src/main/resources/
    ├── static/
    │   └── index.html            # Frontend page (Vue3 + Element Plus)
    └── application.yml           # Configuration file
```

---

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### 1. Build the Project
```bash
mvn clean compile
```

### 2. Run the Application

#### Option 1: Start Web Application (Recommended)
```bash
mvn spring-boot:run
```
Access the application at: http://localhost:8080

#### Option 2: Run Console Demo
```bash
java -cp "target/classes" com.coffee.DesignPatternDemo
```

---

## 🌐 API Endpoints

### 1. Get Coffee Types
```
GET /api/coffees/types
```

**Response Example**:
```json
{
  "success": true,
  "data": ["AMERICANO", "LATTE", "CAPPUCCINO"]
}
```

### 2. Create Order and Pay
```
POST /api/order/create
Content-Type: application/json

{
  "coffeeType": "LATTE",
  "size": "MEDIUM",
  "paymentType": "WECHAT"
}
```

**Response Example**:
```json
{
  "success": true,
  "message": "Order created successfully",
  "data": {
    "orderId": "a1b2c3d4",
    "coffeeName": "Latte Coffee",
    "size": "MEDIUM",
    "totalAmount": 24.0,
    "paymentType": "WECHAT",
    "status": "PAID"
  }
}
```

### 3. Get Order Details
```
GET /api/order/{orderId}
```

---

## 📊 Design Pattern Details

### Factory Pattern Explained

**Problem Scenario**:
Without factory pattern, coffee creation code would look like:
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

**Problems**:
- ❌ Client needs to know all concrete coffee classes
- ❌ Adding new coffee types requires changes in multiple places
- ❌ Code duplication and maintenance issues

**Solution**:
With factory pattern:
```java
Coffee coffee = CoffeeFactory.createCoffee(type);
```

**Advantages**:
- ✅ Client only needs to provide the type
- ✅ Object creation logic is centralized in the factory
- ✅ Adding new coffee types only requires changes in one place

---

### Strategy Pattern Explained

**Problem Scenario**:
Without strategy pattern, payment code would look like:
```java
if (paymentType.equals("WECHAT")) {
    // WeChat payment logic (many lines of code)
    connectToWechatAPI();
    processPayment();
    // ...
} else if (paymentType.equals("ALIPAY")) {
    // Alipay payment logic (many lines of code)
    connectToAlipayAPI();
    processPayment();
    // ...
}
```

**Problems**:
- ❌ Bulky code that's hard to read
- ❌ Violates Single Responsibility Principle
- ❌ Adding new payment methods requires modifying existing code (violates Open/Closed Principle)
- ❌ Difficult to unit test

**Solution**:
With strategy pattern:
```java
PaymentStrategy strategy = getPaymentStrategy(paymentType);
strategy.pay(amount);
```

**Advantages**:
- ✅ Each payment method is an independent class
- ✅ Eliminates if-else statements
- ✅ New payment methods only require adding new classes
- ✅ Easy to unit test

---

## 🎨 Frontend Interface

The frontend uses **Element Plus** component library to provide a beautiful user interface:

- 📋 Dropdown to select coffee type
- 📏 Radio button group to select size
- 💳 Radio button group to select payment method
- 📊 Real-time display of order results

---

## 🔧 How to Extend

### Add New Coffee Type
1. Create a new coffee class extending `Coffee`
2. Add a new case in `CoffeeFactory`

```java
// 1. Create new class
public class Mocha extends Coffee {
    public Mocha() {
        super("Mocha", 25.0);
    }
}

// 2. Add to factory
case "MOCHA" -> new Mocha();
```

### Add New Payment Method
1. Create a new payment class implementing `PaymentStrategy`
2. Add a new case in the strategy selection method

```java
// 1. Create new class
public class UnionPay implements PaymentStrategy {
    @Override
    public String pay(double amount) {
        return "UnionPay successful, amount: ¥" + amount;
    }
    
    @Override
    public String getPaymentName() {
        return "UnionPay";
    }
}

// 2. Add to strategy selection
case "UNIONPAY" -> new UnionPay();
```

---

## 👨‍🎓 Learning Suggestions

1. **Run the demo program first**: Observe console output to understand how both patterns work
2. **Examine key code files**: 
   - `CoffeeFactory.java` - Factory pattern implementation
   - `PaymentStrategy.java` - Strategy pattern interface
   - `CoffeeService.java` - Combined use of both patterns
3. **Try extending**: Add new coffee types or payment methods
4. **Draw UML diagrams**: Create class diagrams to deepen understanding

---

## 📞 Technical Support

If you encounter issues, please check:
1. Java version is 17 or higher
2. Maven is properly installed
3. Port 8080 is not occupied

---

## 📄 License

This project is for educational and experimental purposes only.

---

## 👤 Author

Created by Xie Yunxuan (Student ID: 632307060623)
Computer Science Class 2304