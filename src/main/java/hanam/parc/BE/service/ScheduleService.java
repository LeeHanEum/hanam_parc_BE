package hanam.parc.BE.service;

import hanam.parc.BE.mapper.ScheduleMapper;
import hanam.parc.BE.repository.ScheduleRepository;
import hanam.parc.BE.type.dto.ScheduleRequestDto;
import hanam.parc.BE.type.dto.ScheduleResponseDto;
import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.Schedule;
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
public class ScheduleService {

    private final MemberService memberService;

    private final BoardService boardService;

    private final ScheduleRepository scheduleRepository;

    public void createSchedule(Long boardId, ScheduleRequestDto scheduleRequestDto) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 등록할 수 있습니다.");
        }
        Schedule schedule = ScheduleMapper.INSTANCE.ScheduleRequestDtoToSchedule(scheduleRequestDto);
        schedule.setManager(member);
        if (boardId != null) {
            Board board = boardService.getBoardById(boardId);
            if (board.getBoardCategory() != BoardCategory.EVENT) {
                throw new IllegalArgumentException("이벤트 게시판이 아닙니다.");
            }
            schedule.setBoard(board);
        }

        scheduleRepository.save(schedule);
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = getScheduleById(id);
        return ScheduleMapper.INSTANCE.ScheduleToScheduleResponseDto(schedule);
    }

    public List<ScheduleResponseDto> getScheduleList() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        return scheduleList.stream()
                .map(ScheduleMapper.INSTANCE::ScheduleToScheduleResponseDto)
                .collect(Collectors.toList());
    }

    public Page<ScheduleResponseDto> getScheduleListByPage(Pageable pageable) {
        Page<Schedule> scheduleList = scheduleRepository.findAllByOrderByCreatedAtDesc(pageable);
        return scheduleList.map(ScheduleMapper.INSTANCE::ScheduleToScheduleResponseDto);
    }

    public void updateSchedule(Long id, Long boardId, ScheduleRequestDto scheduleRequestDto) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 수정할 수 있습니다.");
        }
        Schedule schedule = getScheduleById(id);
        schedule.setTitle(scheduleRequestDto.getTitle());
        schedule.setStartDate(scheduleRequestDto.getStartDate());
        schedule.setEndDate(scheduleRequestDto.getEndDate());
        schedule.setColor(scheduleRequestDto.getColor());
        schedule.setDescription(scheduleRequestDto.getDescription());
        if (boardId != null) {
            Board board = boardService.getBoardById(boardId);
            if (board.getBoardCategory() != BoardCategory.EVENT) {
                throw new IllegalArgumentException("이벤트 게시판이 아닙니다.");
            }
            schedule.setBoard(board);
        }
        scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 삭제할 수 있습니다.");
        }
        Schedule schedule = getScheduleById(id);
        scheduleRepository.delete(schedule);
    }

    private Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow();
    }


}
