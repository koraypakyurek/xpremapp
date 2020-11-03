package com.xprem.payment.api;

import com.xprem.payment.business.PaymentBusinessService;
import com.xprem.payment.model.PaymentModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xprem.payment.constants.ApiConstants.PAYMENT_API_PAYMENT_METHOD_PATH;
import static com.xprem.payment.constants.ApiConstants.PAYMENT_API_URL;

@RestController
@RequestMapping(PAYMENT_API_URL)
public class PaymentRestService {

    private final PaymentBusinessService paymentBusinessService;

    public PaymentRestService(PaymentBusinessService paymentBusinessService) {
        this.paymentBusinessService = paymentBusinessService;
    }

    @PostMapping(PAYMENT_API_PAYMENT_METHOD_PATH)
    public boolean pay(PaymentModel paymentModel) {
        return this.paymentBusinessService.pay(paymentModel);
    }
}
