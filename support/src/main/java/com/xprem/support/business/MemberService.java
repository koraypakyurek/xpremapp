package com.xprem.support.business;

import com.xprem.support.enums.MemberType;
import com.xprem.support.model.MemberModel;
import com.xprem.support.model.PaymentModel;
import com.xprem.support.model.SignupModel;

public interface MemberService{
    String signUp(SignupModel signupModel);
    boolean payment(PaymentModel paymentModel);
    MemberType checkMemberStatus(String mail);
    MemberModel getMemberByEmail(String email);
}
