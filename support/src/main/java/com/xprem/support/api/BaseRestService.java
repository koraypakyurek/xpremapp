package com.xprem.support.api;

import com.xprem.support.business.MemberService;
import com.xprem.support.model.MemberModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseRestService {

    @Autowired
    private MemberService memberService;

    public MemberModel getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            if (user != null) {
                return this.memberService.getMemberByEmail(user);
            }
        }
        return null;
    }
}
