package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.PopUpRequestDto;
import hanam.parc.BE.type.dto.PopUpResponseDto;
import hanam.parc.BE.type.entity.PopUp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PopUpMapper {

    PopUpMapper INSTANCE = Mappers.getMapper(PopUpMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "popUp.id"),
            @Mapping(target = "name", source = "popUp.name"),
            @Mapping(target = "url", source = "popUp.url"),
            @Mapping(target = "isShow", source = "popUp.isShow"),
            @Mapping(target = "createdAt", source = "popUp.createdAt")
    })
    PopUpResponseDto PopUpToPopUpResponseDto(PopUp popUp);

    @Mappings({
            @Mapping(target = "name", source = "popUpRequestDto.name"),
            @Mapping(target = "isShow", source = "popUpRequestDto.isShow")
    })
    PopUp PopUpRequestDtoToPopUp(PopUpRequestDto popUpRequestDto);

}
