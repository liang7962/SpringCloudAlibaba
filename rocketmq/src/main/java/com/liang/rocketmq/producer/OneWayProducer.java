package com.liang.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/*
* 发送单向消息
* */
public class OneWayProducer {
    public static void main(String[] args) throws Exception {
        // 1.实例化消息生产者producer
        DefaultMQProducer producer = new DefaultMQProducer("test_producer_group");
        // 2.设置nameServer地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 3.启动producer
        producer.start();
        for (int i = 0; i < 1000; i++) {
            try {
                // 4.构造消息对象，topic=TopicTest，tag=TagA
                Message msg = new Message("TopicTest", "TagA" ,
                        ("Hello RocketMQ TopicTest OneWayProducer" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 5.发送消息
                producer.sendOneway(msg);

            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        // 6.关闭生产者
        producer.shutdown();
    }
}
