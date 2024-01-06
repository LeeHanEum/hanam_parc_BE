package hanam.parc.BE.auth.jwt.service;

import hanam.parc.BE.mapper.MemberMapper;
import hanam.parc.BE.repository.MemberRepository;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String memberId) {

        return memberRepository.findById(memberId)
                .map(member -> createUser(memberId, member))
                .orElseThrow(() -> new UsernameNotFoundException(memberId + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String memberId, Member member) {
        if (!member.getStatus().equals(Status.ACTIVE)) {
            throw new RuntimeException(memberId + " -> 활성화되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                member.getId(),
                member.getPassword(),
                grantedAuthorities);
    }
}
