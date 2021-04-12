package com.aliyun.openservices.springboot.example.mq.normal;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.aliyun.openservices.springboot.example.data.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class DemoMessageListener implements MessageListener {

    @Override
    public Action consume(Message message, ConsumeContext context) {

        System.out.println("Receive: " + message);
        try {
            byte[] body = message.getBody();
            String s = new String(body);
            UserInfo mqConfig = JSON.parseObject(s, UserInfo.class);
            System.out.println(JSON.toJSONString(mqConfig));
            //do something..
            return Action.CommitMessage;
        } catch (Exception e) {
            //消费失败
            return Action.ReconsumeLater;
        }
    }
}
