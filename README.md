# NIOTest 对比测试BIO java NIO 和Netty, 简单的demo

#### BIO
> 实现一个简单的单线程时间服务器, 告知客户端当前时间, 模拟服务器响应时间差, 使用线程sleep

> 服务端使用线程池, 增加并发支持