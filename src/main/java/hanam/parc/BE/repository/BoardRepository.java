package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByCategory(Category category);

    List<Board> findAllByMember(Member member);
}
