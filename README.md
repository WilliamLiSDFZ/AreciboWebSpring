# AreciboWeb Spring Boot 项目

这是一个基于Spring Boot的Web应用程序，用于处理联系表单和邮件发送。

## 项目结构

```
src/main/java/ai/arecibo/areciboweb/
├── AreciboWebApplication.java    # Spring Boot主启动类
├── aspects/                      # AOP切面
├── config/                       # 配置类
├── controller/                   # 控制器
├── dao/                         # 数据访问层
├── service/                     # 服务层
└── tool/                        # 工具类
```

## 运行项目

### 1. 环境要求
- Java 18+
- Maven 3.6+
- MySQL 8.0+

### 2. 数据库配置
确保MySQL服务正在运行，并创建数据库：
```sql
CREATE DATABASE arecibo;
```

### 3. 配置文件
编辑 `src/main/resources/application.yml` 文件，更新数据库和邮件配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/arecibo
    username: your-username
    password: your-password
  
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-app-password
```

### 4. 运行项目

#### 方法一：使用Maven
```bash
mvn spring-boot:run
```

#### 方法二：打包后运行
```bash
mvn clean package
java -jar target/AreciboWeb.jar
```

### 5. 访问应用
应用启动后，访问：http://localhost:8080

## API接口

### 联系表单接口
- **URL**: `/contact`
- **方法**: POST/GET
- **参数**: 
  - `name`: 姓名
  - `email`: 邮箱
  - `message`: 消息内容
- **返回**: JSON格式
  ```json
  {"result": "success"}
  ```

## 主要变更

从Spring项目转换为Spring Boot项目的主要变更：

1. **依赖管理**: 使用Spring Boot Starters替代手动管理Spring依赖
2. **配置**: 使用`application.yml`替代`web.xml`和`mainDB.properties`
3. **启动**: 使用`@SpringBootApplication`注解的主类启动应用
4. **控制器**: 将Servlet转换为`@RestController`
5. **邮件服务**: 使用Spring Boot的`JavaMailSender`
6. **数据源**: 使用Spring Boot自动配置的Druid数据源

## 技术栈

- Spring Boot 3.2.0
- Spring MVC
- Spring JDBC
- Druid连接池
- MySQL数据库
- JavaMail
- Log4j2日志
- Maven构建工具 