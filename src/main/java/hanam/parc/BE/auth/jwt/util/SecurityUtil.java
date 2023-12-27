package hanam.parc.BE.auth.jwt.util;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@AllArgsConstructor
public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    public static Optional<String> getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            logger.debug("Security Context 에 인증 정보가 없습니다.");
            return Optional.empty();
        }
        String memberId = null;
        if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            memberId = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            memberId = (String) authentication.getPrincipal();
        }
        return Optional.ofNullable(memberId);
    }
}
