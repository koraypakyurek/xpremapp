package com.xprem.support.mapper;

import com.xprem.support.entity.TicketEntity;
import com.xprem.support.model.TicketModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketModelMapper {
    TicketModelMapper INSTANCE = Mappers.getMapper(TicketModelMapper.class);

    TicketModel entityToModel(TicketEntity ticketEntity);

    TicketEntity modelToEntity(TicketModel ticketModel);

}
