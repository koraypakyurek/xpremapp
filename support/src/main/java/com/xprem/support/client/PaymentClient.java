package com.xprem.support.client;

import com.xprem.support.model.PaymentModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "Payment-Service",url = "http://localhost:9090")
public interface PaymentClient {
    @RequestMapping(method = RequestMethod.POST,value = "/payment/pay")
    boolean pay(PaymentModel paymentModel);
}
