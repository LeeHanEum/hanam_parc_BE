package hanam.parc.BE.controller;

import hanam.parc.BE.service.MemberService;
import hanam.parc.BE.type.dto.MemberRequestDto;
import hanam.parc.BE.type.dto.MemberResponseDto;
import hanam.parc.BE.type.dto.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "Member", description = "회원 관리")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    @Operation(summary = "[U] 회원 생성", description = "회원 생성")
    public ResponseModel<?> createMember(
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        memberService.createMember(memberRequestDto);
        return ResponseModel.success(true);
    }

//    @GetMapping("/list")
//    @Operation(summary = "[A] 회원 리스팅", description = "회원 리스팅")
//    public ResponseModel<?> getMemberList(
//    ) {
//        List<MemberResponseDto> memberList = memberService.getMemberList();
//        return ResponseModel.success(memberList);
//    }

    @GetMapping("")
    @Operation(summary = "[A] 회원 조회", description = "회원 조회")
    public ResponseModel<?> getMember(
            @RequestParam String id
    ) {
        MemberResponseDto member = memberService.getMember(id);
        return ResponseModel.success(member);
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

    @DeleteMapping("")
    @Operation(summary = "[U] 회원 삭제", description = "회원 삭제")
    public ResponseModel<?> deleteMember(
            @RequestParam String id
    ) {
        memberService.deleteMember(id);
        return ResponseModel.success(true);
    }

}
