package hanam.parc.BE.service;

import hanam.parc.BE.mapper.MemberMapper;
import hanam.parc.BE.repository.MemberRepository;
import hanam.parc.BE.type.dto.MemberRequestDto;
import hanam.parc.BE.type.dto.MemberResponseDto;
import hanam.parc.BE.type.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(MemberRequestDto memberRequestDto) {
        Member member = MemberMapper.INSTANCE.MemberRequestDtoToMember(memberRequestDto);
        memberRepository.save(member);
    }


    public MemberResponseDto getMember(String id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return MemberMapper.INSTANCE.MemberToMemberResponseDto(member);
    }

    public List<MemberResponseDto> getMemberList() {
        List<Member> memberList = memberRepository.findAll();
        return memberList.stream()
                .map(MemberMapper.INSTANCE::MemberToMemberResponseDto)
                .collect(Collectors.toList());
    }

    public void updateMember(String id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.setPassword(memberRequestDto.getPassword());
        member.setName(memberRequestDto.getName());
        member.setPhone(memberRequestDto.getPhone());
        member.setEmail(memberRequestDto.getEmail());
        member.setRole(memberRequestDto.getRole());
        member.setStatus(memberRequestDto.getStatus());
        member.setBirth(memberRequestDto.getBirth());
        memberRepository.save(member);
    }

    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }

}
