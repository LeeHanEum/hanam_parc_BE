package hanam.parc.BE.controller;

import hanam.parc.BE.service.MemberService;
import hanam.parc.BE.type.dto.MemberRequestDto;
import hanam.parc.BE.type.dto.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @Operation(summary = "[C] 회원가입", description = "회원가입")
    public ResponseModel<?> join(
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        memberService.createMember(memberRequestDto);
        return ResponseModel.success(true);
    }

    @PostMapping("/login")
    @Operation(summary = "[R] 로그인", description = "로그인")
    public ResponseModel<?> login(
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        return ResponseModel.success(true);
    }


}
