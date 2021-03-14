package com.aliyun.openservices.springboot.example.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付宝付款代码
 */
@RestController
public class AlipayControllerDemo {

    /**
     * 支付金额
     * @param amount
     */
    @GetMapping("aliPay")
    public void pay(@RequestParam Integer amount){
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(getOptions());
        try {
            // 2. 发起API调用（以创建当面付收款二维码为例）
            AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace()
                    .preCreate("Apple iPhone11 128G", "2234567890", String.valueOf(amount));
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println("调用成功");
            } else {
                System.err.println("调用失败，原因：" + response.msg + "，" + response.subMsg);
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 异步通知
     * @return
     */
    @GetMapping("notifyUrl")
    public String notifyUrl(){
        return "异步通知成功";
    }

    private Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipaydev.com";
        config.signType = "RSA2";

        //<-- 请填写您的AppId，例如：2019091767145019 -->
        config.appId = "2016092000558137";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCWkgs4t9pB5zYm6BbDnE71eAdQN24bPU+3Y6UswQlJ4v/Xs9hQj0iGJEO/4rxRfD5m8JDJKGPEvIBxsH5DvIzr447Lh1DM8RjArNAgaiXT1UG+V+azE8T4upKQ+jRNiOLkYQ4xIejboUshGxIKsQS+oy7E1rXyUV4TAl4s5cr8FaQlV+8t/phde4X52AVOmvclVd4PW39g4SURcRf03R98Uf6DKiNPwB6eXty1YIMoiwVDGiqNEkZu7H33E6mSj8ZFiAVHavnX11Hnc6hycY8NCws4d3keJWiqI6CaaceKF95fC2+xLu/6TXvaCRjIxIaRAObmNOENNff7DjdY13rnAgMBAAECggEBAI5QNSkDpNf7aBR38uQp/ftG2mq4KWq3lXv5Q6o8PcJHOpeu1Xq3Is4ewqY/8Mx3ySdUoxRuB3Zw0FokfgMZ2HwwVcwK9pTuH/4kmIa6rUw92WjF/jguLFMHTPaYdgz+n61KCrbnnQbRuYft5SyTya9B3zeO1OPWgjsnVsnK3ANMIXE6hIA04WoeaMXnIQh9NEX4n4f9rpaqXhVAARqO/mzgR2jbIBLsSxORYn7ElSj4wBVDCezxxZNzmvow+W7HSM9AVaP69uo7pDXha6iXg1UVgEe8BPTCYxvpELZjJt+YTT3k5kYoOfnE6RElcppxDcOjpyITrAfiQVD+6bBADjkCgYEAzySxY9u5RjdCRkvs8EdF8Vx40Xe8y09GyRF5BhTIKYq3X3PdPAo61f+6Vgu7MkdXvLpjuKQOSw9XzLfA8FNngSFUFWsf1wJ/qm1IX+d7VVdqRcU08x7v9iYBxLRngLYtL3lSODDgLv+Gg7ZA+j50zTN7sl4xrAUV8406/Uz4ZbUCgYEAuhV7/VSv2a9/Ebqwa8Yzq++miPm+sGn1flU4gNsmqFaGW1v6j6fDyeMRbQat+5eft1oLUwRyR+qnbLrf1RtuHdxbbTZO5slhMv/dE1hPIqZjBniaNPUGKPaA5zyyEuh39Npdq3SxOvsWcLCq9pSUK9NKuw9q1EaVV+tgHcTUP6sCgYBnCGNLvkD70gSfHqjuIyWBFnPqDNrVbctRu+Usxy2ArETlr6X9dUEqrJassg0cxyu27icn8kJBx5Hue//2CiZTrpY6SRx/P/jsdosnWOaZ3m834Q2jWGIXV3RNuUY6WWNEV94zoOwBtIvd+8t6LZK/droyUHP4klcG29rZn212eQKBgHtEvCwnK46S2SRPfYsHczdUvAdFGfMXXg7gOVvy0Pr2xm5C0grT4bkdeG+22w4tdRoqxhGqgmHtqhc2/AvZ7epODSqdrUro4EC+ih7Egze67SJeRhkKr5f3I5EK/hPUd5J2DVMFLFqb9lOUVMyfxD/76idcoTIZx9jEDZnqx4edAoGBAMiaHCWO7TbWQw3AmBvZClWi32VBD8eEClSe796dTpOgO0ZL3dpHWmcD6PLGnxo8IAEQ5sFaTqtTY/FZtuGkDs05MmJhCspvZ7nkypClHdehl+Dg1NwNFixBskLQnC0KdkBHWa5uBT833LCFUOu3u+zE7TPCAjNWnxrHQriLhgnb";

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
       // config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
       // config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
       // config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可:
        //<-- 请填写您的支付宝公钥，例如：MIIBIjANBg... -->
         config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvVjPu2xYUQv1oCYb0XyrPJqXvXwWrMqYLMQyci4p0erjXyKsUeFLPC5XSsfXHcE7jToNS3aTUwk6730X0JVbGu1tBynMl2LURGcyBgDitxK6vSG8o8769h/gtcZ+hIjX5kKN+MOuOFaiL71lhb2hUqBe8bgZnv13FAdY2kZYLO9EpUR/h1rASKF6v9FRookE/Rz3RjCqN+akQFfCXX7jbBPmOyxU4YUFy6nsJDya0hcw/PRehWy4JGZTyCtFCN0sS97wK+wxChcpoLeGId30+0BpdrrxYotmM9ph36NRVffUHxc77zVkGyy/uHqbwzvC5dZfznP/8bahFVY7jtrPaQIDAQAB";

        //可设置异步通知接收服务地址（可选）
        //<-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->
        config.notifyUrl = "http://localhost:8080/notifyUrl";

        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
       // config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";

        return config;
    }

}
