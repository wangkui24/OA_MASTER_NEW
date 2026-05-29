# Employee Manage Master - Open Code Developer Rules
## 1. 技术栈概述 (Technology Stack)
- **框架**: Spring Boot (v2.2.5.RELEASE) 
- **基础语言**: Java 8+
- **持久层**: Spring Data JPA
- **数据库**: MySQL 8.x + Druid (连接池)
- **缓存**: Redis (spring-boot-starter-data-redis)
- **安全**: Spring Security + JWT + OAuth2
- **接口文档**: Swagger2 (v3.0.0)
- **工具类库**: Lombok, FastJson, ModelMapper / Orika, Jasypt (加密)
## 2. 目录架构与职责划分
- `config`: 自定义配置类 (Redis, DB, Async, Cors, Security等)。
- `controller`: 表现层，使用 `@RestController`。负责请求的分发和参数校验。
- `service`: 业务逻辑层。必须采用 `接口 + Impl实现类` 形式，实现类放在 `service/impl` 下。
- `repository`: 数据访问层。继承 `JpaRepository` 接口，使用 Spring Data JPA 开发。
- `domain`: 数据库实体类，通过 JPA 注解映射数据表。
- `model/dto`: 数据传输对象 (DTO)，用于向前端请求和返回的数据实体。
- `model/vo`: 视图返回对象 (或者使用带有前缀V的domain实体，例如 `VEmployee`)。
- `utils`: 通用工具类 (如 `PageableUtil`, `HttpStatus`)。
- `core`: 核心常量和通用类代码 (如 `Const`, `MessageCode`)。
## 3. 代码规范与编写风格 (Code Style)
### 3.1 统一响应和异常处理
- **控制层返回必须被 `Response<T>` 包装**。不能直接返回业务对象或原生数据。
- 业务异常不直接使用 `throw` 抛出，而是在 `Service` 层通过 `try-catch` 捕获所有代码。捕获异常后返回带有错误状态码的 `Response<T>` 对象：
  ```java
  try {
      // 业务逻辑
      return new Response<>(true); 
  } catch (Exception e) {
      log.error("xxx error [{}]", e.getMessage());
      return new Response<>(HttpStatus.ERROR, MessageCode.System.SERVER_ERROR);
  }
  ```
- **常量使用**: 返回状态和信息需要使用 `HttpStatus` 常量以及 `MessageCode` 常量维护。
### 3.2 控制层 Controller 规范
- 类顶部添加 `@Slf4j`, `@RestController`, `@RequestMapping`。
- API文档注解: 方法上使用 `@ApiOperation(value="接口描述", notes="")`。
- 权限控制: 使用 `@PreAuthorize("hasAuthority('xxx')")` 或者 `@PreAuthorize("hasAnyAuthority(...)")`。
- 分页参数提取: 必须设置默认值，参考 `Const.DEFAULT_PAGE_INDEX` 及 `Const.DEFAULT_PAGE_SIZE`。
- 请求体: 使用 `@RequestBody` 来处理 JSON 请求 (使用Dto接收)。
### 3.3 业务服务 Service 规范
- 需声明接口 (如 `EmployeeService`)。
- 实现类 (如 `EmployeeServiceImpl`) 必须位于 `impl` 包下，标记 `@Service`, `@Slf4j`。
- 所有依赖使用 `@Autowired` 注入。
- 所有业务方法内部通过 `try/catch` 捕获异常，将异常通过日志 `.error` 记录。
### 3.4 实体类 Domain / Entity 规范
- **类注解**: 必须包含 `@Entity`, `@Table(name="xxx")`, `@Data` (Lombok), `@NoArgsConstructor`, `@ApiModel`。
- **字段类型注解**: `@Column`, 需要指定Swagger文档的 `@ApiModelProperty("xxx")`。
- **数据校验**: 必须增加如 `@NotBlank(message = "...")` 之类的 `javax.validation` 注解校验必填字段。
- **审计与时间**: 
  - 通过 `@CreatedDate`, `@CreatedBy`, `@LastModifiedDate`, `@LastModifiedBy` 字段完成数据变动审计追踪。
  - 对于日期需要采用 `@JsonFormat` 定义返回的时间格式 (如 `pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"`)。
- **枚举**: 对于状态/类型等字段，统一收敛在 `BaseEnum` 类下创建内部枚举 (如 `BaseEnum.Employee.EmployeeStatusEnum`)，不要直接使用字符串类型。
### 3.5 DTO 规范
- **类注解**: 必须继承 `Serializable`，并且加上以下 Jackson 处理配置：
  ```java
  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonIgnoreProperties(ignoreUnknown = true)
  ```
- 避免直接将 DTO 保存至数据库实体中，应手动设置或使用工具类 (如封装 `saveXxxByDto` 方法进行字段复制转成 Domain)。
### 3.6 数据库和查询 Repository 规范
- 基于 JPA 实现分页，使用 `Pageable`。 封装创建代码：`PageableUtil.createPageable(page, size, sortField, sortOrder)`。
- 对于关联表较复杂的查询可以使用 `V` 开头的视图实体类 (例如 `VEmployee` 及 `VEmployeeRepository`) 返回数据。
### 3.7 文件与文档备注风格
- 所有类顶部都需要保留统一的注释签名:
  ```java
  /**
   * 功能：{说明}
   * 作者：kwang43 (保持本作者署名)
   * 日期：yyyy/MM/dd HH:mm
   */
  ```
