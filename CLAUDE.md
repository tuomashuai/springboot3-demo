# CLAUDE.md

本文件为 Claude Code (claude.ai/code) 在本仓库中工作时提供指导。

## 项目概述

Spring Boot 3 学习/演示项目（`springboot3-demo`），包含基础 Web 接口、登录鉴权、数据库访问等示例。

- **Java 21** + **Spring Boot 3.3.4** + **Maven**（pom.xml，非 Gradle）
- 主要依赖：Spring Web / Actuator / Validation、Lombok、Sa-Token（鉴权）、MyBatis-Plus、MySQL 驱动、spring-security-crypto（仅 BCrypt，不引入完整 Spring Security）

## 常用命令

```bash
# 编译（跳过测试）
mvn compile

# 运行测试
mvn test

# 运行单个测试类
mvn test -Dtest=BubbleSortTest

# 打包（含 repackage，生成可执行 jar）
mvn package

# 启动应用（默认 dev profile，端口 8080）
mvn spring-boot:run
```

## 代码结构

包根：`com.zood.springboot3demo`

- `controller/` — REST 接口（DemoController、SortController、AuthController）
- `service/` — 业务逻辑（AuthService、BubbleSortService）
- `mapper/` — MyBatis-Plus Mapper 接口（SysUserMapper）
- `entity/` — 数据库实体（SysUser）
- `model/`、`model/dto/` — 响应模型与请求/响应 DTO
- `config/` — 配置类（SaTokenConfig、MybatisPlusConfig、PasswordConfig、GlobalExceptionHandler、DemoProperties）
- `util/` — 工具类（BubbleSort）

资源文件：`src/main/resources/application.yml`（主配置，激活 `dev` profile）、`application-dev.yml`（开发环境数据库凭据）。

## 约定与注意事项

- **鉴权**：使用 Sa-Token，token 通过请求头 `satoken` 携带；路由拦截规则在 `SaTokenConfig` 中配置。
- **密码**：BCrypt 加密，通过 `PasswordConfig` 提供的 `PasswordEncoder` 使用。
- **数据库**：MySQL，连接信息通过环境变量 `MYSQL_HOST/PORT/DB/USER/PASSWORD` 覆盖，默认 `127.0.0.1:3306/springboot3_demo`（root/root）。
- **MyBatis-Plus**：开启了下划线转驼峰（`map-underscore-to-camel-case`），id 策略为自增；Mapper XML 放在 `classpath*:/mapper/**/*.xml`。
- **配置属性类**：使用 `@ConfigurationProperties` + setter 绑定（见 `DemoProperties`）。
- **编译需 `-parameters`**：pom 中已配置，勿移除，否则 `@PathVariable`/`@RequestParam` 参数名推断失效。
- **注释语言**：代码注释使用中文，请保持一致。
- **Lombok**：已启用（`@Slf4j` 等），注意 pom 中通过 `annotationProcessorPaths` 指定。