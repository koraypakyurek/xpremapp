package com.xprem.payment.business;

import com.xprem.payment.model.PaymentModel;

public interface PaymentBusinessService {
    boolean pay(PaymentModel paymentModel);
}
