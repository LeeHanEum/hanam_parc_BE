package hanam.parc.BE.controller;

import hanam.parc.BE.service.ScheduleService;
import hanam.parc.BE.type.dto.ScheduleRequestDto;
import hanam.parc.BE.type.dto.ScheduleResponseDto;
import hanam.parc.BE.type.dto.ResponseModel;
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
@RequestMapping("/schedule")
@Tag(name = "Schedule", description = "schedule 관리")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("")
    @Operation(summary = "[A] schedule 등록", description = "schedule 등록")
    public ResponseModel<?> createSchedule(
            @RequestParam(required = false) Long boardId,
            @RequestBody ScheduleRequestDto scheduleRequestDto
    ) {
        try {
            scheduleService.createSchedule(boardId, scheduleRequestDto);
            return ResponseModel.success(true);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @GetMapping("")
    @Operation(summary = "[U] Schedule 조회", description = "Schedule 조회")
    public ResponseModel<?> getSchedule(
            @RequestParam Long id
    ) {
        try {
            ScheduleResponseDto scheduleResponseDto = scheduleService.getSchedule(id);
            return ResponseModel.success(scheduleResponseDto);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @GetMapping("/list")
    @Operation(summary = "[U] Schedule 리스트 조회", description = "Schedule 리스트 조회")
    public ResponseModel<?> getScheduleList(
    ) {
        try {
            List<ScheduleResponseDto> scheduleList = scheduleService.getScheduleList();
            return ResponseModel.success(scheduleList);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @GetMapping("/page")
    @Operation(summary = "[U] Schedule 페이징 조회", description = "Schedule 페이징 조회")
    public ResponseModel<?> getScheduleListByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ScheduleResponseDto> scheduleList = scheduleService.getScheduleListByPage(pageable);
            return ResponseModel.success(scheduleList);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @PatchMapping("")
    @Operation(summary = "[U] Schedule 수정", description = "Schedule 수정")
    public ResponseModel<?> updateSchedule(
            @RequestParam Long id,
            @RequestParam(required = false) Long boardId,
            @RequestBody ScheduleRequestDto scheduleRequestDto
    ) {
        try {
            scheduleService.updateSchedule(id, boardId, scheduleRequestDto);
            return ResponseModel.success(true);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @DeleteMapping("")
    @Operation(summary = "[D] Schedule 삭제", description = "Schedule 삭제")
    public ResponseModel<?> deleteSchedule(
            @RequestParam Long id
    ) {
        try {
            scheduleService.deleteSchedule(id);
            return ResponseModel.success(true);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

}
