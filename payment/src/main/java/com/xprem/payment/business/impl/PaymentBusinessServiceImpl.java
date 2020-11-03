package com.xprem.payment.business.impl;

import com.xprem.payment.business.PaymentBusinessService;
import com.xprem.payment.model.PaymentModel;
import org.springframework.stereotype.Service;

@Service
public class PaymentBusinessServiceImpl implements PaymentBusinessService {

    private boolean validateCreditCard(String cardNumber) {
        // number must be validated as 0..9 numeric first!!
        int digits = cardNumber.length();
        int oddOrEven = digits & 1;
        long sum = 0;
        for (int count = 0; count < digits; count++) {
            int digit = 0;
            try {
                digit = Integer.parseInt(cardNumber.charAt(count) + "");
            } catch (NumberFormatException e) {
                return false;
            }

            if (((count & 1) ^ oddOrEven) == 0) { // not
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }

        return (sum == 0) ? false : (sum % 10 == 0);
    }

    @Override
    public boolean pay(PaymentModel paymentModel) {
        return this.validateCreditCard(paymentModel.getCreditCardNo());
    }
}
