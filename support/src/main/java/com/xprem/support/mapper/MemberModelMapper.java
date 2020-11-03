package com.xprem.support.mapper;

import com.xprem.support.entity.MemberEntity;
import com.xprem.support.model.MemberModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberModelMapper {

    MemberModelMapper INSTANCE = Mappers.getMapper(MemberModelMapper.class);

    MemberModel entityToModel(MemberEntity memberEntity);
    MemberEntity modelToEntity(MemberModel memberModel);
}
