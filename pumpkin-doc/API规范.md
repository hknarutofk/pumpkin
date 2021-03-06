# API 接口规范

### 一、 说明

API 统一采用类似 Restful 风格, 采用 JSON 格式传输数据。

 

### 二、返回结果体 ResponseVo

返回结果对象名称为 ResponseVo，每个接口不管成功或者失败，都会返回此json结构。

| 字段名  | 类型   | 字段描述                                                     |
| ------- | ------ | ------------------------------------------------------------ |
| code    | int    | 结果码 ：0：代表成功；<br />-1：代表服务器内部错误；其他业务码可有各个业务模块划分定义 |
| message | string | 客户端提示消息                                               |
| log     | string | 错误日志，可以在生产环境关闭，用于日常调试。                 |
| data    | object |                                                              |

 

### 三、分页请求参数 PageForm

| 字段名   | 类型 | 字段描述     |
| -------- | ---- | ------------ |
| pageSize | int  | 显示分页条数 |
| pageNum  | int  | 显示当前页数 |

 

### 四、分页响应参数 PageResponseVo

| 字段名    | 类型   | 字段描述     |
| --------- | ------ | ------------ |
| pageSize  | int    | 显示分页条数 |
| pageNum   | int    | 显示当前页数 |
| totalNum  | int    | 显示总条数   |
| totalPage | int    | 显示总页数   |
| data      | object | 显示结果集   |

 

### 五、API名称定义规范

| 接口名            | 接口描述     |
| ----------------- | ------------ |
| /user/view        | 查询单个用户 |
| /user/list        | 查询用户列表 |
| /user/add         | 新增用户     |
| /user/del         | 删除用户     |
| /user/update      | 修改用户     |
| /user/batchDel    | 批量删除     |
| /user/batchUpdate | 批量更新     |
| /user/batchExport | 批量输出     |
| /user/batchImport | 批量导入     |

 