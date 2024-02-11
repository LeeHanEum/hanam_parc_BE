package hanam.parc.BE.service;

import hanam.parc.BE.mapper.BoardMapper;
import hanam.parc.BE.repository.BoardRepository;
import hanam.parc.BE.type.dto.BoardRequestDto;
import hanam.parc.BE.type.dto.BoardResponseDto;
import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.BoardCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberService memberService;

    private final BoardRepository boardRepository;

    public void createBoard(BoardRequestDto boardRequestDto) {
        Member member = memberService.getCurrentMember();
        Board board = BoardMapper.INSTANCE.BoardRequestDtoToBoard(boardRequestDto);
        board.setMember(member);
        boardRepository.save(board);
    }

    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        return BoardMapper.INSTANCE.BoardToBoardResponseDto(board);
    }

    public List<BoardResponseDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream()
                .map(BoardMapper.INSTANCE::BoardToBoardResponseDto)
                .collect(Collectors.toList());
    }

    public List<BoardResponseDto> getBoardListByCategory(BoardCategory boardCategory) {
        List<Board> boardList = boardRepository.findAllByBoardCategory(boardCategory);
        return boardList.stream()
                .map(BoardMapper.INSTANCE::BoardToBoardResponseDto)
                .collect(Collectors.toList());
    }

    public Page<BoardResponseDto> getBoardPageByCategory(BoardCategory boardCategory, Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAllByBoardCategoryOrderByCreatedAtDesc(boardCategory, pageable);
        return boardPage.map(BoardMapper.INSTANCE::BoardToBoardResponseDto);
    }

    public List<BoardResponseDto> getMyBoardList() {
        Member member = memberService.getCurrentMember();
        List<Board> boardList = boardRepository.findAllByMember(member);
        return boardList.stream()
                .map(BoardMapper.INSTANCE::BoardToBoardResponseDto)
                .collect(Collectors.toList());
    }

    public void updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Member member = memberService.getCurrentMember();
        Board board = boardRepository.findById(id).orElseThrow();
        if (!board.getMember().equals(member) || memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());
        board.setBoardCategory(BoardCategory.valueOf(boardRequestDto.getBoardCategory()));
        boardRepository.save(board);
    }

    public void deleteBoard(Long id) {
        Member member = memberService.getCurrentMember();
        Board board = boardRepository.findById(id).orElseThrow();
        if (!board.getMember().equals(member) || memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        boardRepository.deleteById(id);
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow();
    }

}
