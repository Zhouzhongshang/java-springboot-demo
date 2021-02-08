package com.aliyun.openservices.springboot.example.consumer;

import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.springboot.example.config.MqConfig;
import com.aliyun.openservices.springboot.example.normal.ConsumerClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerTest {

    @Autowired
    private ConsumerBean consumerBean;

    @Autowired
    private MqConfig mqConfig;

    @Test
    public void testConsumer(){
        //方便测试，运行这个方法时启动
       new ImportSelector(){
           @Override
           public String[] selectImports(AnnotationMetadata annotationMetadata) {
                return new String[]{"com.aliyun.openservices.springboot.example.normal.ConsumerClient"};
           }
       };


    }

}
