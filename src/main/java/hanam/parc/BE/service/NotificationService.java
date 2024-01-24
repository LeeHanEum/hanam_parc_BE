package hanam.parc.BE.service;

import hanam.parc.BE.mapper.NotificationMapper;
import hanam.parc.BE.repository.NotificationRepository;
import hanam.parc.BE.type.dto.NotificationRequestDto;
import hanam.parc.BE.type.dto.NotificationResponseDto;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.Notification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final MemberService memberService;

    @Transactional
    public void createNotification(String memberId, NotificationRequestDto notificationRequestDto) {
        Member member = memberService.getMemberById(memberId);
        Notification notification = NotificationMapper.INSTANCE.NotificationRequestDtoToNotification(notificationRequestDto);
        notification.setMember(member);
        notification.setIsRead(false);
        notificationRepository.save(notification);
    }

    public List<NotificationResponseDto> getNotification(String memberId) {
        Member member = memberService.getMemberById(memberId);
        List<Notification> notificationList = notificationRepository.findAllByMember(member);
        return notificationList.stream()
                .map(NotificationMapper.INSTANCE::NotificationToNotificationResponseDto)
                .collect(Collectors.toList());
    }

    public List<NotificationResponseDto> getMyNotification(){
        Member member = memberService.getCurrentMember();
        List<Notification> notificationList = notificationRepository.findAllByMember(member);
        return notificationList.stream()
                .map(NotificationMapper.INSTANCE::NotificationToNotificationResponseDto)
                .collect(Collectors.toList());
    }

    public void readNotification(Long notificationId) {
        Member member = memberService.getCurrentMember();
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }

    public void deleteNotification(Long notificationId) {
        Member member = memberService.getCurrentMember();
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        if (!notification.getMember().equals(member) || memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        notificationRepository.deleteById(notificationId);
    }
}
