package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.MemberRequestDto;
import hanam.parc.BE.type.dto.MemberResponseDto;
import hanam.parc.BE.type.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "memberRequestDto.id"),
            @Mapping(target = "password", source = "memberRequestDto.password"),
            @Mapping(target = "name", source = "memberRequestDto.name"),
            @Mapping(target = "phone", source = "memberRequestDto.phone"),
            @Mapping(target = "email", source = "memberRequestDto.email"),
            @Mapping(target = "role", source = "memberRequestDto.role"),
            @Mapping(target = "status", source = "memberRequestDto.status"),
            @Mapping(target = "birth", source = "memberRequestDto.birth"),
            @Mapping(target = "lastLoginTime", source = "memberRequestDto.lastLoginTime")
    })
    Member MemberRequestDtoToMember(MemberRequestDto memberRequestDto);

    @Mappings({
            @Mapping(target = "name", source = "member.name"),
            @Mapping(target = "phone", source = "member.phone"),
            @Mapping(target = "email", source = "member.email"),
            @Mapping(target = "role", source = "member.role"),
            @Mapping(target = "status", source = "member.status"),
            @Mapping(target = "birth", source = "member.birth"),
            @Mapping(target = "lastLoginTime", source = "member.lastLoginTime")
    })
    MemberRequestDto MemberToMemberRequestDto(Member member);

    @Mappings({
            @Mapping(target = "name", source = "member.name"),
            @Mapping(target = "phone", source = "member.phone"),
            @Mapping(target = "email", source = "member.email"),
            @Mapping(target = "role", source = "member.role"),
            @Mapping(target = "status", source = "member.status"),
            @Mapping(target = "birth", source = "member.birth"),
            @Mapping(target = "lastLoginTime", source = "member.lastLoginTime")
    })
    MemberResponseDto MemberToMemberResponseDto(Member member);
}