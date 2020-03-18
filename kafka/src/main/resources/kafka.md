### 为什么要用kafaka，解决什么问题
```xml
实时性和流式处理
```

1. 特点
   不对外提供随机读写

2. kafka组成
   topic    消息主题，topic可以分成多个partion
   message  消息
   broker   kafka有多个实例组成，每个实例成为broker
   consumer 消费者   如果订阅了某一主题，就会被广播给这些consumer；多个consumer可以组成一个group，这个message只能传输给某个group中的某一个consumer
   producer 生产者   
   partitions   底层是append log文件，任何发送到此partion的消息都会追加到log文件的尾部
   segment  partion物理上有多个segment组成，segment有index file和data file两部分组成，表示索引文件和数据文件
   offset   每条消息在文件中的位置称为offset
   replicas  partion的备份数
   leader    基于备份就以为着对多个备份进行调度，每一个partion都有一个leader，负责所有的读写操作
   follower  只是单调的和leader进行跟进，同步消息

3. kafka基于文件存储
---
### 生产者：

* 消息分到partition的机制：
	1.指定partition
	2.没有partition，根据key的hash值散落到partition上
	3.没有partition,也没有key，采用rand-robin方法，轮询，第一个随机

* 保证消息可靠性：
    kafka发送ack给消息发送者

* ack的发送有两种方式
	1.半数以上的集群同leader同步
	2.全部follwer同leader同步完，再发送ack
	kafka采用全部同步完后发送ack

* 采用全部同步会有一个问题：
```xml
	如果网络不好，会有follower一直同步，那么leader会一直不发送ack，延迟会很大
	所以kafka用一个isr来优化同步策略

	isr是用所有follow中的一部分作为备用leader，isr跟loader同步完之后就可以发送ack

```

* 发送者可以配置ack的返回机制：
	0：消息发送之后，立即返回ack，不管leader是否同步,由于leader可能未落盘，所以leader挂掉会有数据丢失
	1：消息发送之后，leader落盘之后，发送ack,只有leader落盘，在未同步之前,leader挂掉会有数据丢失
	-1:同步完之后，发送ack,同步之后，发送ack之前,leader挂掉，ack未发出，所以生产者会重试，造成数据重复
	
>> 保证数据一致性
	leader挂掉之后，各副本之间可能数据不一致，如何保证数据一致性。
	有一个HW(highwater，所有副本的最小offset) 和LOE（该副本的最后一个offset位置)
	超过HW的数据不会对消费者可见
	数据如何同步：
	选举leader之后，各副本先截取HW之前的数据，然后再同步leader HW到LOE之间的数据，保证数据一致性

### 消费者：
>> kafka怎么保证幂等性
    生产者在生产的时候会有一个pid(produce id)，分区和序列号，kafka会缓存<pid,partion,sequence>，根据这个进行存储，保证幂等性

>> 消费者模式：
    pull模式：
    push模式：

>> 消息消费哪个partion:
    roundrobin:以组为划分依据，一个topic下的partion以轮询的方式分到消费者组里，如果是多个topic，会将多个topic下的partion进行hash然后排序
，然后再以轮询的方式进行分发。
    range:以topic为依据，topic的partion对消费者组进行取余，然后再进行划分，前多后少
例： 1 2 3 4 5 6 7 分到 c1 c2 c3，则c1分到 1 2 3，c2分到 4 5，c3分到6 7



>> 消费模式
    1.队列模式--每一条消息只能被一个消费者读到
    2.广播模式--消息向所有的消费者广播
    3.消费者组--队列模式和广播模式合并，消费者组成一个组，消息由组里面的一个消费者消费


问题：
1.如果可以存比较长的时间，是不是还能被消费？
2.一个消息以什么方式发送到某一个partion？  
答：同5，跟5重复
3.如果leader down掉，怎么保证数据不丢失的？
4.因为有多个partion，消费者消费哪个partion呢？
答：从follower选一个主partion，数据会丢失
5.发送消息，发送到哪个partion？
答：可以指定partion，则定位到partion，如果有key，hash算出定位到partion，如果都没有默认是round-robin策略，就是轮询
6.Consumer 每次  poll 时是获得的消息列表是否只包含一个 Partition 源还是可以多个 Partiton 源？
跟partion有关
7.怎样保证消费的可靠性
只有所有follower都同步之后才确认消息能被消费者访问
8.怎样保证生产者生产数据的可靠性
答：生产者发送数据给leader，leader收到数据后发送成功信息，生产者收到后认为发送数据成功 ，如果一直收不到成功消息，则生产者认为发送数据失败会自动重发数据.
			当leader宕机时，可能丢失数据
