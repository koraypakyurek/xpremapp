package com.xprem.support.api;

import com.xprem.support.model.TicketModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xprem.support.constants.ApiConstants.TICKET_CONTROLLER_PATH;
import static com.xprem.support.constants.ApiConstants.TICKET_POST_PATH;

@RestController
@RequestMapping(TICKET_CONTROLLER_PATH)
public class TicketRestService {

    @PreAuthorize("hasRole('ROLE_PREMIUM')")
    @PostMapping(TICKET_POST_PATH)
    public ResponseEntity<?> post(TicketModel ticketModel) {
        return ResponseEntity.ok().body("ok");
    }
}
