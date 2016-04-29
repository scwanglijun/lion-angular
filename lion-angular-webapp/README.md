### checkout此工程
- 更改pom.xml文件里面的版本引用
```
  <dependencies>
        <dependency>
            <groupId>com.newtouch.lion</groupId>
            <artifactId>lion-angular-web</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
```
- 修改其中version为需要的版本，删掉parent节点


## 导入基数据库
- 创建数据库并导入resources目录下的sql目录newtouch_lion_init.sql文件
- 修改resources/application.properties文件当中连接数据库的地址
- 默认登录用户名和密码为：wanglijun/111aaa


### 版本发布历史：
- 1.0-SNAPSHOT  2016-04-29 14:47
