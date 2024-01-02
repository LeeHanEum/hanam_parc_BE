package hanam.parc.BE.service;

import hanam.parc.BE.auth.jwt.provider.JwtTokenProvider;
import hanam.parc.BE.repository.AuthenticatorRepository;
import hanam.parc.BE.type.entity.Authenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatorService {

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticatorRepository authenticatorRepository;

    public void generateSecretKey(String memberId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwt = jwtTokenProvider.createToken(authentication);
        Authenticator authenticator = Authenticator.builder()
                .memberId(memberId)
                .secretKey(jwt)
                .build();
        authenticatorRepository.save(authenticator);
    }

    public boolean isAuthenticatorExist(String memberId) {
        return authenticatorRepository.existsById(memberId);
    }

    public String getSecretKey(String memberId) throws ChangeSetPersister.NotFoundException {
        return authenticatorRepository.findById(memberId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new)
                .getSecretKey();
    }
}
