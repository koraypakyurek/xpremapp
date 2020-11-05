package com.xprem.support.api;

import com.xprem.support.business.MemberService;
import com.xprem.support.mapper.MemberModelMapper;
import com.xprem.support.model.PaymentModel;
import com.xprem.support.model.SignupModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xprem.support.constants.ApiConstants.*;

@RestController
@RequestMapping(MEMBER_CONTROLLER_PATH)
public class MemberRestService {

    private final MemberService memberService;
    private MemberModelMapper memberModelMapper;

    public MemberRestService(MemberService memberService) {
        this.memberService = memberService;
        this.memberModelMapper = MemberModelMapper.INSTANCE;
    }

    @PostMapping(MEMBER_SIGNUP_PATH)
    public ResponseEntity<?> signUp(@RequestBody SignupModel signupModel) {
        this.memberService.signUp(signupModel);
        return null;
    }

//    @PreAuthorize("hasRole('ROLE_STANDART')")
    @PostMapping(MEMBER_PAYMENT_PATH)
    public ResponseEntity<?> doPayment(@RequestBody PaymentModel paymentModel) {
        this.memberService.payment(paymentModel);
        return ResponseEntity.ok("");
    }

}
