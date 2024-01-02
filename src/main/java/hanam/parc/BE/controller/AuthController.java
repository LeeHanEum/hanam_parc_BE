package hanam.parc.BE.controller;

import hanam.parc.BE.auth.jwt.filter.JwtFilter;
import hanam.parc.BE.auth.jwt.provider.JwtTokenProvider;
import hanam.parc.BE.repository.MemberRepository;
import hanam.parc.BE.service.AuthenticatorService;
import hanam.parc.BE.service.MemberService;
import hanam.parc.BE.type.dto.LoginDto;
import hanam.parc.BE.type.dto.MemberRequestDto;
import hanam.parc.BE.type.dto.ResponseModel;
import hanam.parc.BE.type.dto.TokenDto;
import hanam.parc.BE.type.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.Token;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth", description = "인증 관리")
public class AuthController {

    private final MemberRepository memberRepository;

    private final AuthenticatorService authenticatorService;

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입")
    public ResponseModel<?> signup(
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        memberService.createMember(memberRequestDto);
        authenticatorService.generateSecretKey(memberRequestDto.getId());
        return ResponseModel.success(true);
    }

    @PostMapping("/login")
    public ResponseModel<TokenDto> authorize(
            @RequestBody LoginDto loginDto
    ) throws ChangeSetPersister.NotFoundException {
        String id = loginDto.getId();
        String password = loginDto.getPassword();
        Member member = memberRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
        if (!Objects.equals(member.getPassword(), password)) {
            return ResponseModel.fail("Password is not correct", "비밀번호가 일치하지 않습니다.");
        }
        TokenDto tokenDto = new TokenDto();
        if(authenticatorService.isAuthenticatorExist(id)) {
            String jwt = authenticatorService.getSecretKey(id);
            tokenDto.setToken(jwt);
        }else{
            authenticatorService.generateSecretKey(id);
            String jwt = authenticatorService.getSecretKey(id);
            tokenDto.setToken(jwt);
        }
        return ResponseModel.success(tokenDto);
    }
}
