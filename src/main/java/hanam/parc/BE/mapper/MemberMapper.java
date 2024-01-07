package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.MemberRequestDto;
import hanam.parc.BE.type.dto.MemberResponseDto;
import hanam.parc.BE.type.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "memberRequestDto.id"),
            @Mapping(target = "password", source = "memberRequestDto.password"),
            @Mapping(target = "name", source = "memberRequestDto.name"),
            @Mapping(target = "phone", source = "memberRequestDto.phone"),
            @Mapping(target = "email", source = "memberRequestDto.email"),
            @Mapping(target = "birth", source = "memberRequestDto.birth"),
    })
    Member MemberRequestDtoToMember(MemberRequestDto memberRequestDto);

    @Mappings({
            @Mapping(target = "id", source = "member.id"),
            @Mapping(target = "password", source = "member.password"),
            @Mapping(target = "name", source = "member.name"),
            @Mapping(target = "phone", source = "member.phone"),
            @Mapping(target = "email", source = "member.email"),
            @Mapping(target = "birth", source = "member.birth"),
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
