package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.Program;
import hanam.parc.BE.type.etc.ProgramStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    List<Program> findAllByManager(Member member);

    List<Program> findAllByProgramStatus(ProgramStatus programStatus);

    Page<Program> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Program> findAllByProgramStatusOrderByCreatedAtDesc(ProgramStatus programStatus, Pageable pageable);

}
