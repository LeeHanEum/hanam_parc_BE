package hanam.parc.BE.service;

import hanam.parc.BE.mapper.MemberMapper;
import hanam.parc.BE.repository.MemberRepository;
import hanam.parc.BE.type.dto.JoinDto;
import hanam.parc.BE.type.dto.MemberRequestDto;
import hanam.parc.BE.type.dto.MemberResponseDto;
import hanam.parc.BE.type.dto.SecurityUserDetailsDto;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.MemberStatus;
import hanam.parc.BE.type.etc.MemberRole;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void createMember(JoinDto joinDto) {
        if(memberRepository.findById(joinDto.getId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 ID 입니다.");
        }
        Member member = MemberMapper.INSTANCE.JoinDtoToMember(joinDto);
        member.setPassword(passwordEncoder.encode(joinDto.getPassword()));
        member.setMemberRole(MemberRole.GUEST);
        member.setMemberStatus(MemberStatus.ACTIVE);
        memberRepository.save(member);
    }

    public MemberResponseDto getMember(String id) {
        Member member = getMemberById(id);
        return MemberMapper.INSTANCE.MemberToMemberResponseDto(member);
    }

    public Page<MemberResponseDto> getMemberList(Pageable pageable) {
        Page<Member> memberList = memberRepository.findAllByOrderByCreatedAtDesc(pageable);
        return memberList.map(MemberMapper.INSTANCE::MemberToMemberResponseDto);
    }

    public List<MemberResponseDto> getAdminMemberList() {
        List<Member> memberList = new ArrayList<>();
        memberList.addAll(memberRepository.findAllByMemberRole(MemberRole.SUPER));
        memberList.addAll(memberRepository.findAllByMemberRole(MemberRole.ADMIN));
        return memberList.stream()
                .map(MemberMapper.INSTANCE::MemberToMemberResponseDto)
                .collect(Collectors.toList());
    }

    public void updateMember(String id, MemberRequestDto memberRequestDto) {
        Member member = getMemberById(id);
        member.setName(memberRequestDto.getName());
        member.setGender(memberRequestDto.getGender());
        member.setPhone(memberRequestDto.getPhone());
        member.setGuardianPhone(memberRequestDto.getGuardianPhone());
        member.setGuardianName(memberRequestDto.getGuardianName());
        member.setAddress(memberRequestDto.getAddress());
        member.setEmail(memberRequestDto.getEmail());
        member.setBirth(memberRequestDto.getBirth());
        member.setDisabilityType(memberRequestDto.getDisabilityType());
        memberRepository.save(member);
    }

    public void updateMemberRoleAndStatus(String id, MemberRole memberRole, MemberStatus memberStatus) {
        Member member = getMemberById(id);
        member.setMemberRole(memberRole);
        member.setMemberStatus(memberStatus);
        memberRepository.save(member);
    }

    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }

    public void setLastLoginTime(String id) {
        Member member = getMemberById(id);
        member.setLastLoginTime(LocalDateTime.now());
        memberRepository.save(member);
    }

    public Member getMemberById(String memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    public boolean checkMemberAdminRole(Member member){
        return member.getMemberRole().equals(MemberRole.ADMIN) || member.getMemberRole().equals(MemberRole.SUPER);
    }

    public SecurityUserDetailsDto getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new SecurityException("No authentication data provided");
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof SecurityUserDetailsDto)) {
            throw new SecurityException("Principal is not an instance of SecurityUserDetailsDto");
        }

        // Cast principal to SecurityUserDetailsDto
        return (SecurityUserDetailsDto) principal;
    }

    public Member getCurrentMember() {
        String id = getAuthenticatedUser().getMemberDto().getId();
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    public String getCurrentMemberToken(HttpServletRequest request) {
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new IllegalArgumentException("No cookies provided");
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("jwt")) {
                return cookie.getValue();
            }
        }
        throw new IllegalArgumentException("No JWT cookie provided");
    }

    public boolean checkMemberIdDuplication(String id) {
        return memberRepository.existsById(id);
    }

}
