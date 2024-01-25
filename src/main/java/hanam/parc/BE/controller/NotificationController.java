package hanam.parc.BE.controller;

import hanam.parc.BE.service.NotificationService;
import hanam.parc.BE.type.dto.NotificationRequestDto;
import hanam.parc.BE.type.dto.NotificationResponseDto;
import hanam.parc.BE.type.dto.ResponseModel;
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
@RequestMapping("/notification")
@Tag(name = "Notification", description = "알림 관리")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("")
    @Operation(summary = "[A] 알림 생성", description = "알림 생성")
    public ResponseModel<?> createNotification(
            @RequestParam String memberId,
            @RequestBody NotificationRequestDto notificationRequestDto
    ) {
        notificationService.createNotification(memberId, notificationRequestDto);
        return ResponseModel.success(true);
    }

    @GetMapping("")
    @Operation(summary = "[A] 사용자 별 알림 조회", description = "알림 조회")
    public ResponseModel<?> getNotification(
            @RequestParam String memberId
    ) {
        List<NotificationResponseDto> notificationList = notificationService.getNotification(memberId);
        return ResponseModel.success(notificationList);
    }

    // 내 알림 조회
    @GetMapping("/my")
    @Operation(summary = "[U] 내 알림 조회", description = "내 알림 조회")
    public ResponseModel<?> getMyNotification() {
        List<NotificationResponseDto> notificationList = notificationService.getMyNotification();
        return ResponseModel.success(notificationList);
    }

    @PatchMapping("")
    @Operation(summary = "[U] 알림 읽음 처리", description = "알림 읽음 처리")
    public ResponseModel<?> readNotification(
            @RequestParam Long notificationId
    ) {
        notificationService.readNotification(notificationId);
        return ResponseModel.success(true);
    }

    @DeleteMapping("")
    @Operation(summary = "[U] 알림 삭제", description = "알림 삭제")
    public ResponseModel<?> deleteNotification(
            @RequestParam Long notificationId
    ) {
        notificationService.deleteNotification(notificationId);
        return ResponseModel.success(true);
    }

}
