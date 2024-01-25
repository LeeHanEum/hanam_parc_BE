package hanam.parc.BE.auth.jwt.service;

import hanam.parc.BE.mapper.MemberMapper;
import hanam.parc.BE.repository.MemberRepository;
import hanam.parc.BE.type.dto.MemberResponseDto;
import hanam.parc.BE.type.dto.SecurityUserDetailsDto;
import hanam.parc.BE.type.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // 1. memberRepository로부터 loginId로 유저정보를 받아온다.
        Member byLoginId = memberRepository.findById(id)
                .orElseThrow(
                        () -> new UsernameNotFoundException(id)
                );

        // 2.user를 dto로 변환시켜준다.
        MemberResponseDto memberDto = MemberMapper.INSTANCE.MemberToMemberResponseDto(byLoginId);

        // 3. 사용자 정보를 기반으로 SecurityUserDetailsDto 객체를 생성한다.
        return new SecurityUserDetailsDto(
                memberDto,
                Collections.singleton(new SimpleGrantedAuthority(
                        memberDto.getMemberRole().toString()
                ))
        );
    }

}
