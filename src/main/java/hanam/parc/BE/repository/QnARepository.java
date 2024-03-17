package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.QnA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnARepository extends JpaRepository<QnA, Long> {

    List<QnA> findAllByWriter(Member member);

    Page<QnA> findAllByWriterOrderByCreatedAtDesc(Member member, Pageable pageable);

    Page<QnA> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
