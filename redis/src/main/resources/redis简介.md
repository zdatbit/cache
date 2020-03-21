## 是一个nosql的key-value数据库  

* nosql数据库的产生就是为了解决大规模数据集合多重数据种类带来的挑战，尤其是大数据应用难题。

	* nosql：  
	   非关系型数据库：数据与数据之间没有关联关系  
		一下的几种情况下比较实用：
		1.数据模型比较简单
		2.需要灵活性更强的IT系统
		3.对数据库性能要求更公告
		4.不需要高度的数据一致性
		5.对于给定key，比较容易映射复杂值得环境
	
	* sql：  
	   关系型数据库：表与表之间时间里关联关系
	
* nosql数据库的四大分类  
	1. 键值存储数据库 redis
	2. 列存储数据库   hbase
	3. 文档型数据库  mongodb
	4. 图形数据库    neo4j
	
	
* redis有三个特点：  
	 1. 可以存储在磁盘
	 2. 丰富的数据类型
	 3. 支持数据备份，集群高可用
	 
	 * 性能极高：读110000次/s，写81000次/s  
	 * 丰富的数据类型
	 * 原子性
	 
	 * key存入512M大小
	 * 单线程 原子性
	 * 可以持久化
	 * 支持集群
	 * 还可以做消息队列
	 
	 
   * save 多少秒   多少次操作   save 900 1
   * 保存的数据库名称dump.rdb
	 
   * maxmemory最大存储机制，如果达到最大值，不能写可以读
	 
   * 数据是存在内存中的，很占空间，有两种方式
	 1. 设置过期时间  
	    设置最大存储空间 建议不要超过1G   256-512M
	 2. 采用LRU算法动态将不用的数据删除
	 
	 
## 命令
   1. keys *  获取所有的key
   2. del key 删除可以			例如:del gradeName
   3. dump key  序列化的值		例如：dump gradeName
   4. exists key	值是否存在
   5. expire key	为给定的值设置过期时间，单位是秒
   6. ttl key		查看过期时间，单位是秒
   7. pexpire key 	为给定的值设置过期时间，单位是毫秒
   8. pttl key
   9. persist key	移除过期时间，使key永久有效
   10. keys * 或 keys ？	？代表一个字符
   11. select 0-15 切换数据库
   12. rename key newkey   给原有的key改名
   13. move key db	移动key到新的库中
   14. type key	查看key的数据类型

---	 
### redis支持的类型 string hash list set zset 
	 
	 
	 
1. String类型命令  
	 set key value  
	 setnx key value 只有key不存在时才会存入，分布式锁的问题，解决方案之一  
	 getrange key start end   
	 strlen key 获取值得长度  
	 incr key			自增1，key值不存在，初始化为0  
	 decr key			自减  
	 incrby key num  		
	 decrby key num		自增加上num  
	 
2. hash类型  
	 hash中最多可以存储2^32-1键值对  
	 hset	key		filed	value基本赋值功能  
	 hget	key		field  
	 hmset	key	filed value field value 批量赋值功能  
	 hmget	key field field 			批量获取所有值  
	 hgetall							获取所有的hash  
	 hkeys key							获取所有hash表中的field  
	 hlen key							获取有多少字段  
	 hdel key field  
	 hincrby	key	field  
	 hexists	key	field  
	 
	 `使用场景`：常用于存储一个对象  
 3. List类型  
	 List类似Java中的LinkedList，最多存储2^32-1个key  
	 
	 List命令  
	 lpush key value1 [value2]  
	 lrange key start end  -1代表最末尾 可以正序也可以倒序，从后面查的话-1是最后一个，-2是倒数第二个元素  
	 lpushx	key value   头部追加  
	 rpushx key value    尾部追加  
	 llen key  
	 lindex key index  
	 
	 删除语法  
	 lpop key  删除并返回  
	 rpop key  删除并返回  
	 blpop key1 [key2] timeout  移除并获取列表的最后一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止  
	 brpop key1 [key2] timeout  
	 ltrim key start end 		保留指定区间的元素，其他删除  
	 lset key index value  
	 linsert key before|after vworld value  
	 rpoplpush source destination  

	 
   * List使用场景:
	 1. 对数据量大的集合数据删减 
	 2. 任务队列	 
 4. set  
	 set是String类型的无序集合  
	 
	 set命令  
	 sadd key member [member]   
	 scard key 获取集合的成员数  
	 smemebers key 返回集合中的所有成员  
	 sismember key member 判断member元素是否是集合key的成员  
	 srandmember key count 返回集合中一个或多个随机数  
	 srem key member [member] 移除集合中一个或多个成员  
	 spop key count           移除集合中的一个随机元素  
	 smove source distination member    将member元素从source集合移动到destination集合  
	 
	 sdiff key1 key2  查两个集合的差集，以左侧为准   
	 sdiffstore distinationkey key1 key2  
	  
	 sinter key1 key2 查两个集合的交集  
	 sinterstore distinationkey key1 key2  
	 
	 sunion key1 key2 查两个集合的并集  
	 sunionstore distination key1 key2  
	 
	 `使用场景`：
	 对两个集合间的数据计算交集、并集、差集运算	 
 
 5. zset  
	 也是String类型元素的集合，且不允许重复的成员，有序
	  
	 命令  
	 zadd key score member1   
	 zcard key   
	 zrange key start stop  
	 zrangebyscore key min max   
	 zcount key min max 在min和max之间有多少成员  
	 zrank key member 查看key的索引值  
	 zrevrange key start stop 倒序  
	 zrem key member [member]移除有序集合中的一个或多个成员  
	 zscore key member  
	 zremrangebyrank key start stop  
	 zremrangebyscore key min max   
	  
	 `使用场景`：排行榜	 
---

## java连接redis
```java
    Jedis jedis = new Jedis(host,port);  
    jedis.auth(password);  
    jedis.set(key,value);  
    jedis.get(key);  
    jedis.close();  
	 
	 jedis连接池
	 //连接池 Redis Pool基本配置信息
	 JedisPoolConfig  poolConfig = new JedisPoolConfig();
	 poolConfig.setMaxTotal(num);//最大连接数
	 poolConfig.setMaxIdle(num);//最大空闲数
	 //连接池
	 JedisPool pool = new JedisPool(poolConfig,host,port);
	 
	 Jedis jedis = pool.getResource();

```
	  
	 
##	发布订阅：
   subscribe channel [channel]  麻阅  
   publish 	channel message 向频道发送消息  
   unsubscribe channel   退订  
   `应用场景`：构建实时消息系统
	 
	 
## redis事务
   multi 串行化开始事务，输入的命令都会依次进入命令队列中，但不会执行  
   exec 事务执行，将之前的命令队列中的依次执行     
   discard 取消事务执行  
   watch key 在事务之前,监视某个key，如果被改变，事务不会被执行  
   例如：  
   watch key   
   multi   
   。。。  
   。。。  
   exec  
   
   `使用场景`：
   一组命令必须同时都执行，或者都不执行
	 
	 
* 缓存穿透：
	 由于数据库中不存在，造成无法同步到redis，使得每次都去请求数据库，造成缓存穿透  
* 缓存雪崩：
	 缓存集中在某一段时间愉失效，发生大量的缓存穿透  
	 
* 主从复制  
	 Redis cluster集群
	 redis 3.0之后版本支持redis-cluster集群,至少3Master+3slave才能建立集群，采用无中心结构