package com.xprem.support.security;

import com.xprem.support.entity.MemberEntity;
import com.xprem.support.enums.MemberType;
import com.xprem.support.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.xprem.support.constants.SecurityConstants.NORMAL_MEMBER_ROLE;
import static com.xprem.support.constants.SecurityConstants.PREMIUM_MEMBER_ROLE;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberEntity> member = this.memberRepository.getByMail(username);
        if (!member.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        if (member.get().getMemberType().equals(MemberType.PREMIUM)) {
            grantedAuthorities.add(new SimpleGrantedAuthority(PREMIUM_MEMBER_ROLE));
        } else {
            grantedAuthorities.add(new SimpleGrantedAuthority(NORMAL_MEMBER_ROLE));
        }

        return new User(member.get().getMail(), member.get().getPassword(), grantedAuthorities);
    }
}
