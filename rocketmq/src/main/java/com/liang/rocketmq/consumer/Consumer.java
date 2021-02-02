package com.liang.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
/*
* 消费者
* */
public class Consumer {
    public static void main(String[] args) throws Exception {
        // 1.创建消息消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_consumer_group");
        // 2.设置nameServer地址
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        // 3.订阅主题 tag="*"代表订阅TopicTest主题下所有子主题消息
        consumer.subscribe("TopicTest", "*");
        // 4.注册消息监听回调函数
        consumer.registerMessageListener((MessageListenerConcurrently)(msgs, context) -> {
            for(MessageExt messageExt : msgs){
                String strMsg = new String(messageExt.getBody());
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), strMsg);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // 5.启动消费者
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
