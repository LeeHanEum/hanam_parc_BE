package hanam.parc.BE.controller;

import hanam.parc.BE.service.MemberService;
import hanam.parc.BE.type.dto.MemberRequestDto;
import hanam.parc.BE.type.dto.MemberResponseDto;
import hanam.parc.BE.type.dto.ResponseModel;
import hanam.parc.BE.type.etc.MemberRole;
import hanam.parc.BE.type.etc.MemberStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "Member", description = "회원 관리")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/page")
    @Operation(summary = "[A] 회원 페이징", description = "회원 리스팅")
    public ResponseModel<?> getMemberList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MemberResponseDto> memberList = memberService.getMemberList(pageable);
        return ResponseModel.success(memberList);
    }

    @GetMapping("/last_login")
    @Operation(summary = "[A] 최근 로그인한 회원 조회", description = "최근 로그인한 회원 조회")
    public ResponseModel<?> getLastLoginMemberList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MemberResponseDto> memberList = memberService.getLastLoginMemberList(pageable);
        return ResponseModel.success(memberList);
    }

    @GetMapping("")
    @Operation(summary = "[U] 회원 조회", description = "회원 조회")
    public ResponseModel<?> getMember(
            @RequestParam String id
    ) {
        MemberResponseDto member = memberService.getMember(id);
        return ResponseModel.success(member);
    }

    @GetMapping("/admin")
    @Operation(summary = "[A] 회원 어드민 조회", description = "회원 어드민 조회")
    public ResponseModel<?> getMemberListByRole(
    ) {
        List<MemberResponseDto> memberList = memberService.getAdminMemberList();
        return ResponseModel.success(memberList);
    }

    @PatchMapping("")
    @Operation(summary = "[U] 회원 정보 수정", description = "회원 정보 수정")
    public ResponseModel<?> updateMember(
            @RequestParam String id,
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        memberService.updateMember(id, memberRequestDto);
        return ResponseModel.success(true);
    }

    @PatchMapping("/role")
    @Operation(summary = "[A] 회원 권한 및 상태 수정", description = "회원 권한 및 상태 수정")
    public ResponseModel<?> updateMemberRoleAndStatus(
            @RequestParam String id,
            @RequestParam(required = false) String memberRole,
            @RequestParam(required = false) String memberStatus
    ) {
        memberService.updateMemberRoleAndStatus(id, memberRole, memberStatus);
        return ResponseModel.success(true);
    }

    @DeleteMapping("")
    @Operation(summary = "[U] 회원 탈퇴", description = "회원 탈퇴")
    public ResponseModel<?> deleteMember(
            @RequestParam String id
    ) {
        memberService.deleteMember(id);
        return ResponseModel.success(true);
    }

    @GetMapping("/current")
    @Operation(summary = "[A] 현재 로그인한 회원 조회", description = "현재 로그인한 회원 조회")
    public ResponseModel<?> getCurrentMember() {
        MemberResponseDto member = memberService.getAuthenticatedUser().getMemberDto();
        return ResponseModel.success(member);
    }

    @GetMapping("/token")
    @Operation(summary = "[A] 현재 로그인한 회원의 토큰 조회", description = "현재 로그인한 회원의 토큰 조회")
    public ResponseModel<?> getCurrentMemberToken(
            HttpServletRequest request
    ) {
        String token = memberService.getCurrentMemberToken(request);
        return ResponseModel.success(token);
    }

    @GetMapping("/duplication")
    @Operation(summary = "[U] 회원 아이디 중복 확인", description = "회원 아이디 중복 확인")
    public ResponseModel<?> checkMemberIdDuplication(
            @RequestParam String id
    ) {
        boolean isDuplicated = memberService.checkMemberIdDuplication(id);
        return ResponseModel.success(isDuplicated);
    }
}
