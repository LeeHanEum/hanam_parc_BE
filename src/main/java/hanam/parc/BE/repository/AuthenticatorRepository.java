package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Authenticator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticatorRepository extends JpaRepository<Authenticator, String> {

}