# Arecibo AI Email Templates

这个项目现在包含了美观的HTML邮件模板，用于替换原来的纯文本邮件。

## 功能特性

### 🎨 美观的设计
- 现代化的渐变色彩设计
- 响应式布局，适配各种邮件客户端
- 专业的品牌形象展示

### 📧 三种邮件模板

1. **联系确认邮件** (`contact-confirmation-template`)
   - 发送给提交联系表单的用户
   - 包含用户消息详情
   - 提供下一步操作指引

2. **订阅确认邮件** (`subscription-confirmation-template`)
   - 发送给订阅新闻通讯的用户
   - 欢迎信息和使用说明
   - 预期内容预览

3. **内部通知邮件** (`internal-notification-template`)
   - 发送给团队成员
   - 包含完整的联系信息
   - 快速回复链接

## 文件结构

```
src/main/resources/
├── templates/
│   └── email-templates.html          # 邮件模板文件
├── static/
│   └── email-preview.html            # 模板预览页面
└── application.yml                   # 邮件配置

src/main/java/ai/arecibo/areciboweb/tool/
├── EmailService.java                 # 邮件发送服务（已更新）
└── EmailTemplateService.java         # 模板处理服务（新增）
```

## 使用方法

### 1. 查看模板预览
启动应用后，访问：`http://localhost:8080/email-preview.html`

### 2. 自动使用
邮件模板已经集成到现有的邮件发送逻辑中：
- 联系表单提交 → 自动发送HTML确认邮件
- 订阅表单提交 → 自动发送HTML欢迎邮件
- 团队成员 → 自动接收HTML通知邮件

### 3. 手动发送HTML邮件
```java
@Autowired
private EmailService emailService;

// 发送联系确认邮件
emailService.sendContactConfirmationEmail("from@example.com", "to@example.com", "用户名", "用户邮箱", "用户消息");

// 发送订阅确认邮件
emailService.sendSubscriptionConfirmationEmail("from@example.com", "to@example.com", "用户邮箱");

// 发送内部通知邮件
emailService.sendInternalNotificationEmail("from@example.com", "to@example.com", "用户名", "用户邮箱", "用户消息");
```

## 模板变量

模板使用 `{{变量名}}` 格式的占位符：

- `{{name}}` - 用户姓名
- `{{email}}` - 用户邮箱
- `{{message}}` - 用户消息
- `{{timestamp}}` - 提交时间

## 自定义样式

### 主要颜色
- 主色调：`#667eea` (蓝色)
- 渐变色：`#764ba2` (紫色)
- 背景色：`#f8f9fa` (浅灰)
- 文字色：`#333333` (深灰)

### 修改样式
编辑 `src/main/resources/templates/email-templates.html` 文件中的CSS样式部分。

## 邮件客户端兼容性

模板已针对以下邮件客户端进行了优化：
- ✅ Gmail
- ✅ Outlook
- ✅ Apple Mail
- ✅ Thunderbird
- ✅ 移动端邮件应用

## 注意事项

1. **图片支持**：当前模板使用纯CSS设计，不依赖外部图片
2. **字体安全**：使用系统默认字体，确保在所有客户端正常显示
3. **响应式**：模板在桌面和移动设备上都能良好显示
4. **编码**：使用UTF-8编码，支持中文等特殊字符

## 故障排除

### 模板无法加载
- 检查 `templates/email-templates.html` 文件是否存在
- 确认文件路径正确

### 邮件发送失败
- 检查邮件服务器配置
- 查看应用日志获取详细错误信息

### 样式显示异常
- 某些邮件客户端可能不支持某些CSS属性
- 模板已使用兼容性最好的CSS属性

## 更新日志

- **v1.0** - 初始版本，包含三种邮件模板
- 支持HTML邮件发送
- 添加模板预览功能
- 集成到现有邮件发送逻辑
