package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {

    List<BoardImage> findAllByBoard(Board board);

    void deleteAllByBoardId(Long boardId);

    BoardImage findByUrl(String url);

}
