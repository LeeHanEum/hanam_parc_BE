package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.Comment;
import hanam.parc.BE.type.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByMember(Member member);

    List<Comment> findAllByBoard(Board board);
}
