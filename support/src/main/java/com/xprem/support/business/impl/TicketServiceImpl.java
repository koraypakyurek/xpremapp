package com.xprem.support.business.impl;

import com.xprem.support.business.TicketService;
import com.xprem.support.entity.MemberEntity;
import com.xprem.support.enums.MemberType;
import com.xprem.support.exception.PaymentRequiredException;
import com.xprem.support.exception.UserNotFoundException;
import com.xprem.support.mapper.TicketModelMapper;
import com.xprem.support.model.TicketModel;
import com.xprem.support.repository.MemberRepository;
import com.xprem.support.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private final MemberRepository memberRepository;
    private final TicketRepository ticketRepository;
    private TicketModelMapper ticketModelMapper;

    public TicketServiceImpl(MemberRepository memberRepository, TicketRepository ticketRepository) {
        this.memberRepository = memberRepository;
        this.ticketRepository = ticketRepository;
        this.ticketModelMapper = TicketModelMapper.INSTANCE;
    }

    @Override
    public TicketModel post(TicketModel ticketModel) {
        MemberEntity memberEntity = this.memberRepository.getOne(ticketModel.getMemberId());
        if (memberEntity == null) {
            throw new UserNotFoundException("User not found");
        }

        if (!memberEntity.getMemberType().equals(MemberType.PREMIUM)) {
            throw new PaymentRequiredException();
        }
        return this.ticketModelMapper.entityToModel(this.ticketRepository.save(this.ticketModelMapper.modelToEntity(ticketModel)));
    }
}
