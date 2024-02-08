package hanam.parc.BE.service;

import hanam.parc.BE.mapper.EventMapper;
import hanam.parc.BE.repository.EventRepository;
import hanam.parc.BE.type.dto.EventRequestDto;
import hanam.parc.BE.type.dto.EventResponseDto;
import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.Event;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.BoardCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final MemberService memberService;

    private final BoardService boardService;

    private final EventRepository eventRepository;

    public void createEvent(Long boardId, EventRequestDto eventRequestDto) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 등록할 수 있습니다.");
        }
        Event event = EventMapper.INSTANCE.EventRequestDtoToEvent(eventRequestDto);
        event.setMember(member);
        if (boardId != null) {
            Board board = boardService.getBoardById(boardId);
            if (board.getBoardCategory() != BoardCategory.EVENT) {
                throw new IllegalArgumentException("이벤트 게시판이 아닙니다.");
            }
            event.setBoard(board);
        }

        eventRepository.save(event);
    }

    public EventResponseDto getEvent(Long id) {
        Event event = getEventById(id);
        return EventMapper.INSTANCE.EventToEventResponseDto(event);
    }

    public List<EventResponseDto> getEventList() {
        List<Event> eventList = eventRepository.findAll();
        return eventList.stream()
                .map(EventMapper.INSTANCE::EventToEventResponseDto)
                .collect(Collectors.toList());
    }

    public Page<EventResponseDto> getEventListByPage(Pageable pageable) {
        Page<Event> eventList = eventRepository.findAllByOrderByCreatedAtDesc(pageable);
        return eventList.map(EventMapper.INSTANCE::EventToEventResponseDto);
    }

    public void updateEvent(Long id, Long boardId, EventRequestDto eventRequestDto) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 수정할 수 있습니다.");
        }
        Event event = getEventById(id);
        event.setTitle(eventRequestDto.getTitle());
        event.setStart(eventRequestDto.getStart());
        event.setEnd(eventRequestDto.getEnd());
        event.setColor(eventRequestDto.getColor());
        event.setDescription(eventRequestDto.getDescription());
        if (boardId != null) {
            Board board = boardService.getBoardById(boardId);
            if (board.getBoardCategory() != BoardCategory.EVENT) {
                throw new IllegalArgumentException("이벤트 게시판이 아닙니다.");
            }
            event.setBoard(board);
        }
        eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 삭제할 수 있습니다.");
        }
        Event event = getEventById(id);
        eventRepository.delete(event);
    }

    private Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow();
    }


}
