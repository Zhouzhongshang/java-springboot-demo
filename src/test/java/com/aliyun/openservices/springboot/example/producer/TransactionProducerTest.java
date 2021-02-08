package com.aliyun.openservices.springboot.example.producer;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.TransactionProducerBean;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionExecuter;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;
import com.aliyun.openservices.shade.com.google.common.collect.Maps;
import com.aliyun.openservices.springboot.example.config.MqConfig;
import com.aliyun.openservices.springboot.example.data.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionProducerTest {

    public static final Map<String,String> map = new HashMap<>();

    //事务消息的Producer 已经注册到了spring容器中，后面需要使用时可以直接注入到其它类中
    @Autowired
    private TransactionProducerBean transactionProducerBean;

    @Autowired
    private MqConfig mqConfig;

    @Test
    public void testSend() throws InterruptedException {


        for (int i=0;i<10;i++){
            UserInfo userInfo = new UserInfo(i, String.valueOf(i));
            Message msg = new Message(mqConfig.getTopic(), "", JSON.toJSONString(userInfo).getBytes());
            SendResult sendResult = transactionProducerBean.send(msg, new LocalTransactionExecuter() {
                @Override
                public TransactionStatus execute(Message msg, Object arg) {
                    String msgID = msg.getMsgID();
                    map.put(msgID,new String(msg.getBody()));
                    System.out.println("执行本地事务");
                    //1.成功执行。2，失败会抛出异常回滚。3，未知则回查
                    return TransactionStatus.Unknow; //根据本地事务执行结果来返回不同的TransactionStatus
                }
            }, null);
            System.out.println(sendResult);
            Thread.sleep(10000);
        }
    }
}
