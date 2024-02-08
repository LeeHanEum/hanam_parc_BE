package hanam.parc.BE.controller;

import hanam.parc.BE.service.EventService;
import hanam.parc.BE.service.MemberService;
import hanam.parc.BE.type.dto.EventRequestDto;
import hanam.parc.BE.type.dto.EventResponseDto;
import hanam.parc.BE.type.dto.ResponseModel;
import hanam.parc.BE.type.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
@Tag(name = "Event", description = "event 관리")
public class EventController {

    private final EventService eventService;

    @PostMapping("")
    @Operation(summary = "[A] Event 등록", description = "Event 등록")
    public ResponseModel<?> createEvent(
            @RequestParam(required = false) Long boardId,
            @RequestBody EventRequestDto eventRequestDto
    ) {
        try {
            eventService.createEvent(boardId, eventRequestDto);
            return ResponseModel.success(true);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @GetMapping("")
    @Operation(summary = "[U] Event 조회", description = "Event 조회")
    public ResponseModel<?> getEvent(
            @RequestParam Long id
    ) {
        try {
            EventResponseDto eventResponseDto = eventService.getEvent(id);
            return ResponseModel.success(eventResponseDto);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @GetMapping("/list")
    @Operation(summary = "[U] Event 리스트 조회", description = "Event 리스트 조회")
    public ResponseModel<?> getEventList(
    ) {
        try {
            List<EventResponseDto> eventList = eventService.getEventList();
            return ResponseModel.success(eventList);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @GetMapping("/page")
    @Operation(summary = "[U] Event 페이징 조회", description = "Event 페이징 조회")
    public ResponseModel<?> getEventListByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EventResponseDto> eventList = eventService.getEventListByPage(pageable);
            return ResponseModel.success(eventList);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @PatchMapping("")
    @Operation(summary = "[U] Event 수정", description = "Event 수정")
    public ResponseModel<?> updateEvent(
            @RequestParam Long id,
            @RequestParam(required = false) Long boardId,
            @RequestBody EventRequestDto eventRequestDto
    ) {
        try {
            eventService.updateEvent(id, boardId, eventRequestDto);
            return ResponseModel.success(true);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @DeleteMapping("")
    @Operation(summary = "[D] Event 삭제", description = "Event 삭제")
    public ResponseModel<?> deleteEvent(
            @RequestParam Long id
    ) {
        try {
            eventService.deleteEvent(id);
            return ResponseModel.success(true);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

}
