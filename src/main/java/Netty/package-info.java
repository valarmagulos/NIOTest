/**
 * description:
 * create: 2018-08-13
 * 1000M网卡-> 单次请求的大小
 * -> 序列化协议(protbuf, json)?自定义文本协议
 * -> 长连接(dubbo) -> 短连接(http)?
 * (建立连接,释放连接)
 * -> 开几个端口, 暂定1个
 * -> 线程池多大?    10-20个线程
 * -> redis服务搭哪里, 先走本地缓存?本地缓存
 * 压测方式? 支持自定义协议的?
 * client 和server得分开
 * 自定义协议? 暂定 内容+协议分隔符
 * @author zhun.huang
 * 08-20
 * server要写完.client方案定下来
 * 本机压一下, 看下性能. 异步定时线程打印qps统计信息.
 * linux参数调整.
 * 计算机组成原理 到第5章
 */
package Netty;

