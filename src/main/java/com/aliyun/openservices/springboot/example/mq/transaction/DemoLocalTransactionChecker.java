package com.aliyun.openservices.springboot.example.mq.transaction;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;
import org.springframework.stereotype.Component;

@Component
public class DemoLocalTransactionChecker implements LocalTransactionChecker {
    @Override
    public TransactionStatus check(Message msg) {
        //TODO 回查执行成功或者失败
        System.out.println("开始回查本地事务状态：模拟成功");
        return TransactionStatus.CommitTransaction; //根据本地事务状态检查结果返回不同的TransactionStatus
    }
}
