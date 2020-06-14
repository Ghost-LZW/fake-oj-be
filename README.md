# Fake OJ

## 简介

java web 大作业 , 取OJ形而不取其意， 仿欧拉计划, 答案判题
<del>(O(1)判题，judge姬几句话，无敌</del>

使用流行技术栈, springboot+vue+element-ui， 前后端分离

前端: <https://github.com/Ghost-LZW/fake-oj-fe>

使用shiro做授权管理，redis实现缓存处理

演示地址: [fakeoj.soullan.com](http://fakeoj.soullan.com)

## 技术栈

### SpringBoot

* 持久层 -- mybatisPlus + 分页插件

* 授权管理 -- shiro

* 缓存处理 -- Redis

* 日志 -- Slf4j

### 数据库

* Mysql: 业务数据

* Redis: 用户缓存

## 工程结构

``` text
├─src
│  ├─main
│  │  ├─java
│  │  │  ├─com.soullanfakeojbe
│  │  │  │  ├─config / 框架配置
│  │  │  │  ├─controller / 请求接口
│  │  │  │  ├─dao / 持久层
│  │  │  │  │  ├─mapper
│  │  │  │  │  └─session
│  │  │  │  ├─modle / 实体类
│  │  │  │  │  ├─bo
│  │  │  │  │  ├─constant
│  │  │  │  │  ├─entity
│  │  │  │  │  ├─response
│  │  │  │  │  └─vo
│  │  │  │  ├─service / 服务类
│  │  │  │  │  └─impl
│  │  │  │  ├─util / 工具类
│  │  │  │  └─GaoOjBeApplication.java / 启动类
│  │  │  └─META-INF
│  │  └─resources
│  │      └─application.yaml / 配置文件
│  └─test / 测试文件
```

## 项目预览

![project show](./image/show.gif)
