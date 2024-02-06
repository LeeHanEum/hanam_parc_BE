package hanam.parc.BE.controller;

import hanam.parc.BE.service.MemberService;
import hanam.parc.BE.type.dto.JoinDto;
import hanam.parc.BE.type.dto.LoginDto;
import hanam.parc.BE.type.dto.MemberRequestDto;
import hanam.parc.BE.type.dto.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Login", description = "로그인 관리")
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/join")
    @Operation(summary = "[U] 회원가입", description = "회원가입")
    public ResponseModel<?> join(
            @RequestBody JoinDto joinDto
    ) {
        try {
            memberService.createMember(joinDto);
            return ResponseModel.success(true);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "[U] 로그인", description = "로그인")
    public ResponseModel<?> login(
            @RequestBody LoginDto loginDto
    ) {
        return ResponseModel.success(true);
    }

    @GetMapping("/logout")
    @Operation(summary = "[U] 로그아웃", description = "로그아웃")
    public ResponseModel<?> logout(
            HttpServletResponse response
    ) {
//        Cookie jwtCookie = new Cookie("jwt", null);
//        jwtCookie.setMaxAge(0);  // 쿠키의 유효기간을 0으로 설정하여 즉시 삭제
//        jwtCookie.setPath("/");
//        response.addCookie(jwtCookie);
        return ResponseModel.success(true);
    }

}
