package com.liang.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/*
 *
 * 发送异步消息
 * */
public class AsyncProducer {
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
                        ("Hello RocketMQ TopicTest AsyncProducer" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 5.异步发送消息
                producer.send(msg, new SendCallback() {
                    /*
                    * 成功回调函数
                    * */
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("发送成功:"+sendResult);
                    }

                    /*
                     * 失败回调函数
                     * */
                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println("发送异常:"+throwable);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        // 6.关闭生产者
        producer.shutdown();
    }
}
