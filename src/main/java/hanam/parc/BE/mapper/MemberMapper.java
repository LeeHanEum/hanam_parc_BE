package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.JoinDto;
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
            @Mapping(target = "name", source = "memberRequestDto.name"),
            @Mapping(target = "gender", source = "memberRequestDto.gender"),
            @Mapping(target = "phone", source = "memberRequestDto.phone"),
            @Mapping(target = "guardianPhone", source = "memberRequestDto.guardianPhone"),
            @Mapping(target = "guardianName", source = "memberRequestDto.guardianName"),
            @Mapping(target = "disabilityType", source = "memberRequestDto.disabilityType"),
            @Mapping(target = "address", source = "memberRequestDto.address"),
            @Mapping(target = "email", source = "memberRequestDto.email"),
            @Mapping(target = "birth", source = "memberRequestDto.birth"),
            @Mapping(target = "memberRole", source = "memberRequestDto.memberRole"),
            @Mapping(target = "memberStatus", source = "memberRequestDto.memberStatus"),
    })
    Member MemberRequestDtoToMember(MemberRequestDto memberRequestDto);

    @Mappings({
            @Mapping(target = "id", source = "member.id"),
            @Mapping(target = "password", source = "member.password"),
            @Mapping(target = "name", source = "member.name"),
            @Mapping(target = "gender", source = "member.gender"),
            @Mapping(target = "phone", source = "member.phone"),
            @Mapping(target = "guardianPhone", source = "member.guardianPhone"),
            @Mapping(target = "guardianName", source = "member.guardianName"),
            @Mapping(target = "address", source = "member.address"),
            @Mapping(target = "email", source = "member.email"),
            @Mapping(target = "memberRole", source = "member.memberRole"),
            @Mapping(target = "memberStatus", source = "member.memberStatus"),
            @Mapping(target = "disabilityType", source = "member.disabilityType"),
            @Mapping(target = "birth", source = "member.birth"),
            @Mapping(target = "lastLoginTime", source = "member.lastLoginTime")
    })
    MemberResponseDto MemberToMemberResponseDto(Member member);

    @Mappings({
            @Mapping(target = "id", source = "joinDto.id"),
            @Mapping(target = "password", source = "joinDto.password"),
            @Mapping(target = "name", source = "joinDto.name"),
            @Mapping(target = "phone", source = "joinDto.phone"),
            @Mapping(target = "email", source = "joinDto.email"),
    })
    Member JoinDtoToMember(JoinDto joinDto);

}
