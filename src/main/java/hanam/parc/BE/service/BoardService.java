package hanam.parc.BE.service;

import hanam.parc.BE.mapper.BoardMapper;
import hanam.parc.BE.repository.BoardImageRepository;
import hanam.parc.BE.repository.BoardRepository;
import hanam.parc.BE.type.dto.BoardImageDto;
import hanam.parc.BE.type.dto.BoardRequestDto;
import hanam.parc.BE.type.dto.BoardResponseDto;
import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.BoardImage;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.BoardCategory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberService memberService;


    private final BoardRepository boardRepository;

    private final BoardImageRepository boardImageRepository;

    public Long createBoard(BoardRequestDto boardRequestDto) {
        Member member = memberService.getCurrentMember();
        Board board = BoardMapper.INSTANCE.BoardRequestDtoToBoard(boardRequestDto);
        board.setMember(member);
        return boardRepository.save(board).getId();
    }

    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        return BoardMapper.INSTANCE.BoardToBoardResponseDto(board);
    }

    public BoardImageDto getBoardImage(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        List<String> boardImageList = getBoardImageList(board);
        return new BoardImageDto(boardImageList);
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
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());
        boardRepository.save(board);
    }

    public void deleteBoard(Long id) {
        Member member = memberService.getCurrentMember();
        Board board = boardRepository.findById(id).orElseThrow();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        boardRepository.deleteById(id);
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow();
    }

    private List<String> getBoardImageList(Board board) {
        List<BoardImage> boardImageList = boardImageRepository.findAllByBoard(board);
        return boardImageList.stream()
                .map(BoardImage::getUrl)
                .collect(Collectors.toList());
    }

}
