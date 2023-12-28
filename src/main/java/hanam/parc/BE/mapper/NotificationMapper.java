package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.NotificationRequestDto;
import hanam.parc.BE.type.dto.NotificationResponseDto;
import hanam.parc.BE.type.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mappings({
            @Mapping(target = "content", source = "notificationRequestDto.content")
    })
    Notification NotificationRequestDtoToNotification(NotificationRequestDto notificationRequestDto);

    @Mappings({
            @Mapping(target = "id", source = "notification.id"),
            @Mapping(target = "content", source = "notification.content"),
            @Mapping(target = "createdAt", source = "notification.createdAt")
    })
    NotificationResponseDto NotificationToNotificationResponseDto(Notification notification);
}
