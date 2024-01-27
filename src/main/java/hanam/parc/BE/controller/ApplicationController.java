package hanam.parc.BE.controller;

import hanam.parc.BE.service.ApplicationService;
import hanam.parc.BE.type.dto.ApplicationDto;
import hanam.parc.BE.type.dto.ResponseModel;
import hanam.parc.BE.type.etc.ApplicationStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/application")
@Tag(name = "Application", description = "application 관리")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("")
    @Operation(summary = "[U] Application 등록", description = "Application 등록")
    public ResponseModel<?> createApplication(
            @RequestParam Long programId,
            @RequestBody ApplicationDto applicationDto
    ) {
        applicationService.createApplication(programId, applicationDto);
        return ResponseModel.success(true);
    }

    @GetMapping("")
    @Operation(summary = "[U] Application 조회", description = "Application 조회")
    public ResponseModel<?> getApplication(
            @RequestParam Long id
    ) {
        ApplicationDto applicationDto = applicationService.getApplication(id);
        return ResponseModel.success(applicationDto);
    }

    @GetMapping("/my")
    @Operation(summary = "[U] 내가 신청한 Application 조회", description = "내가 신청한 Application 조회")
    public ResponseModel<?> getMyApplication() {
        List<ApplicationDto> applicationDtoList = applicationService.getMyApplication();
        return ResponseModel.success(applicationDtoList);
    }

    @GetMapping("/list")
    @Operation(summary = "[A] Application 리스트 조회", description = "Application 리스트 조회")
    public ResponseModel<?> getApplicationList() {
        List<ApplicationDto> applicationDtoList = applicationService.getApplicationList();
        return ResponseModel.success(applicationDtoList);
    }

    @GetMapping("/program")
    @Operation(summary = "[A] Program에 대한 Application 리스트 조회", description = "Program에 대한 Application 리스트 조회")
    public ResponseModel<?> getApplicationListByProgram(
            @RequestParam Long programId
    ) {
        List<ApplicationDto> applicationDtoList = applicationService.getApplicationListByProgram(programId);
        return ResponseModel.success(applicationDtoList);
    }

    @PatchMapping("")
    @Operation(summary = "[U] Application 수정", description = "Application 수정")
    public ResponseModel<?> updateApplication(
            @RequestParam Long id,
            @RequestBody ApplicationDto applicationDto
    ) {
        applicationService.updateApplication(id, applicationDto);
        return ResponseModel.success(true);
    }

    @PatchMapping("/status")
    @Operation(summary = "[A] Application 상태 변경", description = "Application 상태 변경")
    public ResponseModel<?> updateApplicationStatus(
            @RequestParam Long id,
            @RequestParam ApplicationStatus status
    ) {
        applicationService.updateApplicationStatus(id, status);
        return ResponseModel.success(true);
    }

    @DeleteMapping("")
    @Operation(summary = "[U] Application 취소", description = "Application 취소")
    public ResponseModel<?> deleteApplication(
            @RequestParam Long id
    ) {
        applicationService.deleteApplication(id);
        return ResponseModel.success(true);
    }

}
