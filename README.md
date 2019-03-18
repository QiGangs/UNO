# uno

##项目介绍
UNO牌的后端逻辑
大部分的游戏逻辑都在服务端
主要采用http 和 websocket通信


##待办事件
+ 解开套接字与游戏房间的耦合，完成断线重连
+ 完成游戏端的管理与监控
+ 游戏信息放入redis,不在放入内存
+ 用户角色的转换，不在使用微信的openid
+ 拓展到android与web




##接口介绍

###HTTP相关

####1.微信登录
```
/wx/login  
```
主要用于微信授权认证，获得用户的OPENID来作为用户的唯一标识 

参数
```
code //微信从微信服务器获取的code
```
返回  
```
openid 用户的唯一标识符
```

###WebScoket相关

####1.建立连接
```
/websocket/{roomid}/{playerid} 
```
+ roomid默认为“000000”，表示新建房间
+ roomid不存在也会新建房间
+ playerid表示用户的唯一标识 ，即openid


####2.服务端接受到消息
其中需要一个字段为type
+ type为-1表示退出房间或游戏，会关闭套接字
+ type为110表示开始游戏，从房间进入游戏模式
+ type为3表示游戏过程中交换的数据

data字段
+ 可以包含任意东西


####3.服务端发出的消息
这是在游戏过程中获取到信息，不包括在房间为data字段
```
    myid:-1,
    canPutPlayerId:-1,
    cardid:-1,
    cardPileNum:108,
    disCardPileNum:0,
    players:["asdasd"],
    card:{"type":"func","num":-1,"func":"stop","color":"nocolor","id":"000"},
    rudge: [{ "id": 20, "type": "num", "num": 2, "func": "trump", "color": "red" }],
    truetempcolor:""

```

type子弹也是这样
其中需要一个字段为type
+ type为-1表示退出房间或游戏，会关闭套接字
+ type为110表示开始游戏，从房间进入游戏模式
+ type为3表示游戏过程中交换的数据
+ type为4表示游戏结束了，表示游戏已经玩结束了 







#### 软件架构
软件架构说明


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx
