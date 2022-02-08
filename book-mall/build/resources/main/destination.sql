CREATE TABLE user_dest (
   id       INT NOT NULL,
   username     VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   email      VARCHAR(255)
) WITH (
  'connector.type' = 'jdbc', -- 使用 jdbc connector
  'connector.url' = 'jdbc:mysql://127.0.0.1:3306/bookmall?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&serverTimezone=Asia/Shanghai', -- jdbc url
  'connector.table' = 'user_copy', -- 表名
  'connector.username' = 'root', -- 用户名
  'connector.password' = 'mysql7794818', -- 密码
  'connector.write.flush.max-rows' = '1' -- 默认5000条，为了演示改为1条
)