package hanam.parc.BE.service;

import hanam.parc.BE.mapper.CommentMapper;
import hanam.parc.BE.repository.CommentRepository;
import hanam.parc.BE.type.dto.CommentRequestDto;
import hanam.parc.BE.type.dto.CommentResponseDto;
import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.Comment;
import hanam.parc.BE.type.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberService memberService;

    private final BoardService boardService;

    private final CommentRepository commentRepository;

    public void createComment(Long boardId, Long commentId, CommentRequestDto commentRequestDto) {
        Member member = memberService.getMemberById("leehaneum");
        Board board = boardService.getBoardById(boardId);
        Comment comment = CommentMapper.INSTANCE.CommentRequestDtoToComment(commentRequestDto);
        comment.setMember(member);
        comment.setBoard(board);
        if (commentId != null){
            Comment parent = getCommentById(commentId);
            comment.setParent(parent);
        }
        commentRepository.save(comment);
    }

    public CommentResponseDto getComment(Long id) {
        Comment comment = getCommentById(id);
        return CommentMapper.INSTANCE.CommentToCommentResponseDto(comment);
    }

    public List<CommentResponseDto> getAllCommentByParent(Long id) {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(getCommentById(id));
        commentList.addAll(getChildComment(id));
        return commentList.stream()
                .map(CommentMapper.INSTANCE::CommentToCommentResponseDto)
                .toList();
    }

    public List<CommentResponseDto> getMyComment() {
        Member member = memberService.getMemberById("leehaneum");
        List<Comment> commentList = commentRepository.findAllByMember(member);
        return commentList.stream()
                .map(CommentMapper.INSTANCE::CommentToCommentResponseDto)
                .toList();
    }

    public void updateComment(Long id, CommentRequestDto commentRequestDto) {
        Member member = memberService.getMemberById("leehaneum");
        Comment comment = getCommentById(id);
        if (!comment.getMember().equals(member)) {
            throw new RuntimeException("권한이 없습니다.");
        }
        comment.setContent(commentRequestDto.getContent());
        commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        Member member = memberService.getMemberById("superuser");
        Comment comment = getCommentById(id);
        if (comment.getMember().equals(member) || memberService.checkMemberAdminRole(member)) {
            commentRepository.deleteById(id);
        } else {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
    }

    private List<Comment> getChildComment(Long id) {
        Comment comment = getCommentById(id);
        List<Comment> commentList = new ArrayList<>();
        for(Comment child : comment.getChildren()) {
            commentList.add(child);
            if (child.getChildren() != null) {
                commentList.addAll(getChildComment(child.getId()));
            }
        }
//        commentList.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
        return commentList;
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }
}
