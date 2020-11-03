package com.xprem.support.repository;

import com.xprem.support.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> getByMail(String mail);
}
