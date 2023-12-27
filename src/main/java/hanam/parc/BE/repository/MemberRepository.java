package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Member findOneWithAuthoritiesById(String id);

}
