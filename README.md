# <center>合同签署功能后端实现</center>

## 1.合同信息的增加

​        数据库中增加一个合同表：contract，每一条合同字段主键为合同的id，外键为用户的id。其余信息包括：甲方与乙方的姓名，合同名称，合同有效期，合同状态（即除了URL外的基本信息）。具体参考下表：

| 字段名        | 类型         | 解释                                                       |
| ------------- | ------------ | ---------------------------------------------------------- |
| contract_id   | varchar(20)  | 合同的ID，主码<br />唯一定义一个合同                       |
| user_id       | varcahr(20)  | 用户的id，参考user表<br />合同拥有者id                     |
| party_A       | varchar(30)  | 甲方的姓名                                                 |
| party_B       | varchar(30)  | 乙方的姓名                                                 |
| contract_name | varchar(255) | 合同名                                                     |
| URL           | varchar(255) | URL                                                        |
| valid_period  | datetime     | 合同有效的截止日期                                         |
| has_signed    | int(1)       | 枚举类型，表示是否签署：<br />1表示已经签署，0表示没有签署 |

---

合同部分完整模式图如下：

![image-20220606090546395](https://cdn.jsdelivr.net/gh/Holmes233666/blogImage@main/img/image-20220606090546395.png)

## 2.三个接口

### 2.1 获得合同综述信息 `/getallcontracts`

功能描述：用户登录后，点击合同，返回与这个用户相关的所有的合同的概要信息。

接口信息：

- url地址：`服务器IP:端口/getallcontracts`
- 请求方式：`get`
- 参数：`user_id`	
- 接口返回格式：json，具体格式如下：

```json
{
    "contracts_info": [
        {
            "party_B_name": "林海音",
            "valid_period": "2022-07-14",
            "contract_id": "1",
            "has_sign": 0,
            "contract_name": "IPAD-2021销售合同",
            "party_A_name": "孙蕴琦"
        },
        {
            "party_B_name": "张欣",
            "valid_period": "2022-09-22",
            "contract_id": "2",
            "has_sign": 1,
            "contract_name": "西安电子科技大学家属区院11栋A211租赁合同",
            "party_A_name": "孙蕴琦"
        }
    ],
    "code": 0
}
```

其中code为0时表示操作成功，1时表示失败。后续接口相同，不再赘述。

### 2.2 获得某合同具体信息`/getdetailcontracts`

功能描述：用户登录后，点击合同，呈现出合同列表后展示点击具体的合同进行签署或者重新签署。

接口信息：

- url地址：`服务器IP:端口/getdetailcontract`
- 请求方式：`get`
- 参数：`contract_id`	
- 接口返回格式：json，具体格式如下：

```json
{
    "code": 0,
    "detail_info": {
        "party_B_name": "林海音",
        "valid_period": "2022-07-14",
        "contract_id": "1",
        "has_sign": 0,
        "contract_name": "IPAD-2021销售合同",
        "party_A_name": "孙蕴琦",
        "URL": "https://cdn.jsdelivr.net/gh/xinwuyun/facedec-SHOGOKI@main/src/assets/contracts/%E5%90%88%E5%90%8C1.pdf"
    }
}
```

### 2.3 修改合同签署状态`/sign`

功能描述：用户打开签署界面后签署完毕，需要通知后端修改合同状态==并上传新的URL==——未签署改为已签署

接口信息：

- url地址：`服务器IP:端口/sign`
- 请求方式：`post`
- 参数：`contract_id`和`URL`

请求格式如下：

```json
{
    "contract_id" : "1",
    "URL" : <新的URL>
}
```

服务器根据contract_id修改合同的签署状态为已签署（修改has_sign为1），==并修改合同表中的URL字段==。返回为只含有code的JSON。

### 2.4 上传合同模板`/uploadtemplate`

接口信息：

- url地址：`服务器IP:端口/uploadtemplate`
- 请求方式：`post`
- 参数：`type`和`URL`

这里需要给合同进行类别的编号：

- 赠与合同：`1 (int)`
- 借款合同：`2 (int)`
- 租赁合同：`3 (int)`
- 销售合同：`4 (int)`

全为int类型。

发送给后端的post请求格式如下：

```json
{
    "type" : "3",
    "URL": "https://cdn.jsdelivr.net/gh/xinwuyun/facedecSHOGOKI@main/src/assets/contracts/%E5%90%88%E5%90%8C1.pdf",
    "template_name" : "一般销售合同"
}
```

