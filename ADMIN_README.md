# Arecibo AI 后台管理系统

## 功能概述

这是一个为Arecibo AI网站创建的简单后台管理系统，提供以下功能：

### 主要功能
- 📊 **仪表板** - 显示系统统计数据和快速操作
- 📧 **联系消息管理** - 查看、删除联系表单提交的消息
- 👥 **订阅用户管理** - 管理邮件订阅用户列表
- 📤 **数据导出** - 支持CSV格式导出联系消息和订阅用户数据

### 技术特性
- 响应式设计，支持移动端访问
- 分页显示，支持大量数据
- 实时删除确认
- 美观的Bootstrap UI界面
- 简单的访问控制

## 访问方式

### 1. 启动应用
```bash
mvn spring-boot:run
```

### 2. 访问后台管理
打开浏览器访问：`http://localhost:8080/admin/login`

### 3. 登录
- 管理员密钥：`arecibo_admin_2025`
- 输入密钥后点击登录即可进入后台

## 页面说明

### 仪表板 (`/admin`)
- 显示联系消息总数
- 显示订阅用户总数
- 系统状态概览
- 快速操作按钮

### 联系消息管理 (`/admin/contacts`)
- 查看所有联系表单提交的消息
- 支持分页浏览
- 可以删除不需要的消息
- 支持导出为CSV文件
- 点击邮箱地址可直接发送邮件

### 订阅用户管理 (`/admin/subscribers`)
- 查看所有邮件订阅用户
- 支持分页浏览
- 可以删除订阅用户
- 支持导出为CSV文件
- 点击邮箱地址可直接发送邮件

## 数据库表结构

### contact 表
```sql
CREATE TABLE contact (
    contact_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    time TIMESTAMP NOT NULL
);
```

### subscribe 表
```sql
CREATE TABLE subscribe (
    subscribe_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    time TIMESTAMP NOT NULL
);
```

## 安全说明

⚠️ **重要安全提醒**：
- 当前使用的是简单的密钥验证，仅适用于开发环境
- 生产环境建议使用更安全的认证方式，如：
  - Spring Security + JWT
  - OAuth 2.0
  - 数据库用户认证
- 建议修改默认的管理员密钥
- 建议添加HTTPS支持

## 扩展建议

### 短期改进
1. 添加搜索功能
2. 添加批量操作
3. 添加数据统计图表
4. 改进错误处理

### 长期规划
1. 用户权限管理
2. 操作日志记录
3. 邮件发送历史
4. 系统配置管理
5. 数据备份功能

## 开发说明

### 项目结构
```
src/main/java/ai/arecibo/areciboweb/
├── controller/
│   └── AdminController.java          # 后台管理控制器
├── service/
│   ├── AdminService.java             # 后台管理服务接口
│   └── AdminServiceImplement.java    # 后台管理服务实现
├── dao/
│   ├── DatabaseController.java       # 数据库访问接口（已扩展）
│   └── DatabaseControllerImplement.java # 数据库访问实现（已扩展）
└── config/
    ├── AdminSecurityConfig.java      # 后台安全配置
    └── AdminAccessInterceptor.java   # 访问拦截器

src/main/resources/templates/admin/
├── index.html                        # 仪表板页面
├── contacts.html                     # 联系消息管理页面
├── subscribers.html                  # 订阅用户管理页面
└── login.html                        # 登录页面
```

### 依赖说明
- Spring Boot 3.2.0
- Thymeleaf 模板引擎
- Bootstrap 5.1.3
- MySQL 数据库
- Druid 连接池

## 联系支持

如有问题或建议，请联系开发团队。
