package hanam.parc.BE.service;

import hanam.parc.BE.mapper.MemberMapper;
import hanam.parc.BE.repository.MemberRepository;
import hanam.parc.BE.type.dto.MemberRequestDto;
import hanam.parc.BE.type.dto.MemberResponseDto;
import hanam.parc.BE.type.dto.SecurityUserDetailsDto;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.MemberStatus;
import hanam.parc.BE.type.etc.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void createMember(MemberRequestDto memberRequestDto) {
        if(memberRepository.findById(memberRequestDto.getId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 ID 입니다.");
        }
        Member member = MemberMapper.INSTANCE.MemberRequestDtoToMember(memberRequestDto);
        member.setPassword(passwordEncoder.encode(memberRequestDto.getPassword()));
        member.setMemberRole(MemberRole.GUEST);
        member.setMemberStatus(MemberStatus.ACTIVE);
        memberRepository.save(member);
    }

    public MemberResponseDto getMember(String id) {
        Member member = getMemberById(id);
        return MemberMapper.INSTANCE.MemberToMemberResponseDto(member);
    }

    public List<MemberResponseDto> getMemberList() {
        List<Member> memberList = memberRepository.findAll();
        return memberList.stream()
                .map(MemberMapper.INSTANCE::MemberToMemberResponseDto)
                .collect(Collectors.toList());
    }

    public void updateMember(String id, MemberRequestDto memberRequestDto) {
        Member member = getMemberById(id);
        member.setPassword(passwordEncoder.encode(memberRequestDto.getPassword()));
        member.setName(memberRequestDto.getName());
        member.setGender(memberRequestDto.getGender());
        member.setPhone(memberRequestDto.getPhone());
        member.setEmail(memberRequestDto.getEmail());
        member.setBirth(memberRequestDto.getBirth());
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

}
