package hanam.parc.BE.service;

import hanam.parc.BE.mapper.BoardMapper;
import hanam.parc.BE.repository.BoardRepository;
import hanam.parc.BE.type.dto.BoardDto;
import hanam.parc.BE.type.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void createBoard(BoardDto boardDto) {
        Board board = BoardMapper.INSTANCE.BoardDtoToBoard(boardDto);
        boardRepository.save(board);
    }

    public BoardDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        return BoardMapper.INSTANCE.BoardToBoardDto(board);
    }

    public List<BoardDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream()
                .map(BoardMapper.INSTANCE::BoardToBoardDto)
                .collect(Collectors.toList());
    }

    public void updateBoard(Long id, BoardDto boardDto) {
        Board board = boardRepository.findById(id).orElseThrow();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setCategory(boardDto.getCategory());
        boardRepository.save(board);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

}
