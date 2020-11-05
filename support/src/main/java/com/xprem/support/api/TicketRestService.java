package com.xprem.support.api;

import com.xprem.support.business.MemberService;
import com.xprem.support.business.TicketService;
import com.xprem.support.enums.MemberType;
import com.xprem.support.model.MemberModel;
import com.xprem.support.model.TicketModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xprem.support.constants.ApiConstants.TICKET_CONTROLLER_PATH;
import static com.xprem.support.constants.ApiConstants.TICKET_POST_PATH;
import static com.xprem.support.constants.SecurityConstants.PAYMENT_REQUIRED_MESSAGE;

@RestController
@RequestMapping(TICKET_CONTROLLER_PATH)
public class TicketRestService extends BaseRestService {

    private final MemberService memberService;
    private final TicketService ticketService;

    public TicketRestService(MemberService memberService, TicketService ticketService) {
        this.memberService = memberService;
        this.ticketService = ticketService;
    }

    @PostMapping(TICKET_POST_PATH)
    public ResponseEntity<?> post(TicketModel ticketModel) {
        MemberModel currentUser = this.getCurrentUser();
        if (currentUser != null) {
            MemberType memberType = this.memberService.checkMemberStatus(currentUser.getMail());
            if (memberType != null) {
                if (memberType.equals(MemberType.STANDART)) {
                    return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(PAYMENT_REQUIRED_MESSAGE);
                } else {
                    return ResponseEntity.ok(this.ticketService.post(ticketModel));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Hatali kullanici");
    }
}
