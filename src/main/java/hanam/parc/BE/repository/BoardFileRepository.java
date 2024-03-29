package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {

    List<BoardFile> findAllByBoard(Board board);

    BoardFile findByUrl(String url);
}
