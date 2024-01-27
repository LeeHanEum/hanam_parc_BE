package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Application;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByMember(Member member);

    List<Application> findAllByProgram(Program program);
}
