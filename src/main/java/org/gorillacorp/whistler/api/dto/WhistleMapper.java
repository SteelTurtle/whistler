package org.gorillacorp.whistler.api.dto;

import org.gorillacorp.whistler.domain.model.Whistle;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface WhistleMapper {
    WhistleMapper instance = Mappers.getMapper(WhistleMapper.class);

    WhistleDto whistleToWhistleDto(Whistle whistle);

    Whistle whistleDtoToWhistle(WhistleDto whistleDto);
}
