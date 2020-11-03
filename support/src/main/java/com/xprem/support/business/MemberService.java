package com.xprem.support.business;

import com.xprem.support.enums.MemberStatus;
import com.xprem.support.model.MemberModel;
import com.xprem.support.model.PaymentModel;
import com.xprem.support.model.SignupModel;

import java.util.UUID;

public interface MemberService{
    String signUp(SignupModel signupModel);
    boolean payment(PaymentModel paymentModel);
    MemberStatus checkMemberStatus(UUID memberId);
    MemberModel getMemberByEmail(String email);
}
