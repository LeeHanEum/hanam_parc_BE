package hanam.parc.BE.service;

import hanam.parc.BE.auth.jwt.util.SecurityUtil;
import hanam.parc.BE.mapper.MemberMapper;
import hanam.parc.BE.repository.MemberRepository;
import hanam.parc.BE.type.dto.MemberRequestDto;
import hanam.parc.BE.type.dto.MemberResponseDto;
import hanam.parc.BE.type.entity.Authenticator;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.Role;
import hanam.parc.BE.type.etc.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public void createMember(MemberRequestDto memberRequestDto) {
        if(memberRepository.findById(memberRequestDto.getId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 ID 입니다.");
        }
        Member member = MemberMapper.INSTANCE.MemberRequestDtoToMember(memberRequestDto);
        member.setPassword(passwordEncoder.encode(memberRequestDto.getPassword()));
        member.setRole(Role.GUEST);
        member.setStatus(Status.ACTIVE);
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
        member.setPhone(memberRequestDto.getPhone());
        member.setEmail(memberRequestDto.getEmail());
        member.setBirth(memberRequestDto.getBirth());
        memberRepository.save(member);
    }

    public void updateMemberRoleAndStatus(String id, Role role, Status status) {
        Member member = getMemberById(id);
        member.setRole(role);
        member.setStatus(status);
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

    public String getCurrentMemberId() {
        return SecurityUtil.getCurrentUsername().orElseThrow();
    }

    public Optional<Member> getCurrentMember() {
        return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findById);
    }

    public Member getMemberById(String memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

}
