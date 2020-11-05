package com.xprem.support.business.impl;

import com.xprem.support.business.MailService;
import com.xprem.support.business.MemberService;
import com.xprem.support.client.PaymentClient;
import com.xprem.support.entity.MemberEntity;
import com.xprem.support.enums.MemberStatus;
import com.xprem.support.enums.MemberType;
import com.xprem.support.exception.AlreadyMemberException;
import com.xprem.support.exception.AlreadyPremiumMemberException;
import com.xprem.support.exception.UserNotFoundException;
import com.xprem.support.mapper.MemberModelMapper;
import com.xprem.support.model.MemberModel;
import com.xprem.support.model.PaymentModel;
import com.xprem.support.model.SignupModel;
import com.xprem.support.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import static com.xprem.support.constants.SecurityConstants.PREMIUM_MEMBER_EXPIRE_DAYS;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PaymentClient paymentClient;
    private final MailService mailService;
    private MemberModelMapper memberModelMapper;


    public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder, PaymentClient paymentClient, MailService mailService) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.paymentClient = paymentClient;
        this.mailService = mailService;
        this.memberModelMapper = MemberModelMapper.INSTANCE;
    }

    private MemberModel getMemberModel(SignupModel signupModel) {
        MemberModel memberModel = new MemberModel();
        memberModel.setPassword(this.bCryptPasswordEncoder.encode(signupModel.getPassword()));
        memberModel.setMail(signupModel.getMail());
        memberModel.setName(signupModel.getName());
        memberModel.setLastName(signupModel.getLastName());
        memberModel.setMemberDate(new Date().toInstant());
        memberModel.setMemberStatus(MemberStatus.ACTIVE);
        memberModel.setMemberType(MemberType.STANDART);
        return memberModel;
    }


    @Override
    public String signUp(SignupModel signupModel) {
        Optional<MemberEntity> memberEntity = this.memberRepository.getByMail(signupModel.getMail());
        if (memberEntity.isPresent()) {
            throw new AlreadyMemberException();
        }
        this.memberRepository.save(this.memberModelMapper.modelToEntity(getMemberModel(signupModel)));
        return signupModel.getMail();
    }

    private boolean doPayment(PaymentModel paymentModel) {
        return this.paymentClient.pay(paymentModel);
    }

    @Override
    public boolean payment(PaymentModel paymentModel) {
        Optional<MemberEntity> memberEntity = this.memberRepository.getByMail(paymentModel.getMail());
        if (memberEntity.isPresent()) {
            if (memberEntity.get().getMemberType().equals(MemberType.PREMIUM)) {
                throw new AlreadyPremiumMemberException();
            }

            if (this.doPayment(paymentModel)) {
                Instant startDate = new Date().toInstant();
                memberEntity.get().setMemberStatus(MemberStatus.ACTIVE);
                memberEntity.get().setMemberType(MemberType.PREMIUM);
                memberEntity.get().setPremiumMemberStartDate(startDate);
                memberEntity.get().setPremiumMemberEndDate(startDate.plus(PREMIUM_MEMBER_EXPIRE_DAYS, ChronoUnit.DAYS));
                this.memberRepository.save(memberEntity.get());
                this.mailService.sendMail(memberEntity.get().getMail());
            }

        } else {
            throw new UserNotFoundException("User not found.");
        }
        return false;
    }

    /***
     * Uyelık durumunu kontrol eder Premium uyelik suresı gecmisse veritabanınını guncellerç
     * @param mail
     * @return
     */

    @Override
    public MemberType checkMemberStatus(String mail) {
        Optional<MemberEntity> member = this.memberRepository.getByMail(mail);
        if (member.isPresent()) {
            if (member.get().getMemberType().equals(MemberType.PREMIUM)) {
                if (member.get().getPremiumMemberEndDate() != null && member.get().getPremiumMemberEndDate().isBefore(new Date().toInstant())) {
                    return MemberType.PREMIUM;
                } else {
                    member.get().setMemberType(MemberType.STANDART);
                    member.get().setMemberStatus(MemberStatus.WAIT_FOR_PAYMENT);
                    this.memberRepository.save(member.get());
                    return MemberType.STANDART;
                }
            } else return MemberType.STANDART;

        }
        return MemberType.STANDART;
    }

    @Override
    public MemberModel getMemberByEmail(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        Optional<MemberEntity> memberEntity = this.memberRepository.getByMail(email);
        if (memberEntity.isPresent()) {
            return this.memberModelMapper.entityToModel(memberEntity.get());
        }
        return null;
    }
}
