package com.aliyun.openservices.springboot.example.enums;

import com.aliyun.openservices.springboot.example.service.ServiceEnumService;

/**
 * @program: java-springboot-demo
 * @description: 库存操作业务枚举
 * @author: zzs
 * @create: 2021-03-23 21:08
 **/
public enum InventoryEnum {
    //保存策略
    SAVE{
        @Override
        public void serviceInventory(ServiceEnumService serviceEnumService) {
            serviceEnumService.callBack("回调保存");
        }
    },
    //提交策略
    SUBMIT{
        @Override
        public void serviceInventory(ServiceEnumService serviceEnumService) {
            serviceEnumService.callBack("回调提交");
        }
    };
    public abstract void serviceInventory(ServiceEnumService serviceEnumService);
}
