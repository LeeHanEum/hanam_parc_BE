package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.BoardCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByBoardCategory(BoardCategory boardCategory);

    List<Board> findAllByMember(Member member);

    Page<Board> findAllByBoardCategoryOrderByCreatedAt(BoardCategory boardCategory, Pageable pageable);


}
