package hanam.parc.BE.service;

import hanam.parc.BE.mapper.ApplicationMapper;
import hanam.parc.BE.repository.ApplicationRepository;
import hanam.parc.BE.type.dto.ApplicationRequestDto;
import hanam.parc.BE.type.dto.ApplicationResponseDto;
import hanam.parc.BE.type.entity.Application;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.Program;
import hanam.parc.BE.type.etc.ApplicationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final MemberService memberService;

    private final ProgramService programService;

    private final ApplicationRepository applicationRepository;

    public void createApplication(Long programId, ApplicationRequestDto applicationRequestDto) {
        Program program = programService.getProgramById(programId);
        if (!programService.checkProgramAvailable(program)) {
            throw new IllegalArgumentException("접수 중이 아닙니다.");
        }
        if (LocalDate.now().isAfter(program.getEndDate())) {
            throw new IllegalArgumentException("접수 기간이 지났습니다.");
        }else if (LocalDate.now().isBefore(program.getStartDate())) {
            throw new IllegalArgumentException("아직 접수 기간이 아닙니다.");
        }
        Member member = memberService.getCurrentMember();
        Application application = ApplicationMapper.INSTANCE.ApplicationRequestDtoToApplication(applicationRequestDto);
        application.setProgram(program);
        application.setMember(member);
        application.setStatus(ApplicationStatus.WAITING);
        applicationRepository.save(application);
    }

    public ApplicationResponseDto getApplication(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        return ApplicationMapper.INSTANCE.ApplicationToApplicationResponseDto(application);
    }

    public List<ApplicationResponseDto> getMyApplication() {
        Member member = memberService.getCurrentMember();
        List<Application> applicationList = applicationRepository.findAllByMember(member);
        return applicationList.stream()
                .map(ApplicationMapper.INSTANCE::ApplicationToApplicationResponseDto)
                .collect(Collectors.toList());
    }

    public List<ApplicationResponseDto> getApplicationList() {
        List<Application> applicationList = applicationRepository.findAll();
        return applicationList.stream()
                .map(ApplicationMapper.INSTANCE::ApplicationToApplicationResponseDto)
                .collect(Collectors.toList());
    }

    public Page<ApplicationResponseDto> getApplicationListByPage(Pageable pageable) {
        Page<Application> applicationList = applicationRepository.findAllByOrderByCreatedAtDesc(pageable);
        return applicationList.map(ApplicationMapper.INSTANCE::ApplicationToApplicationResponseDto);
    }

    public List<ApplicationResponseDto> getApplicationListByProgram(Long programId) {
        Program program = programService.getProgramById(programId);
        List<Application> applicationList = applicationRepository.findAllByProgram(program);
        return applicationList.stream()
                .map(ApplicationMapper.INSTANCE::ApplicationToApplicationResponseDto)
                .collect(Collectors.toList());
    }

    public void updateApplication(Long id, ApplicationRequestDto applicationRequestDto) {
        Member member = memberService.getCurrentMember();
        Application application = applicationRepository.findById(id).orElseThrow();
        if (!application.getMember().equals(member)) {
            throw new IllegalArgumentException("본인의 신청만 수정할 수 있습니다.");
        }
        if (application.getStatus().equals(ApplicationStatus.ACCEPTED)) {
            throw new IllegalArgumentException("신청이 완료되어 수정할 수 없습니다.");
        }else if (application.getStatus().equals(ApplicationStatus.CANCELED)) {
            throw new IllegalArgumentException("신청이 거절되어 수정할 수 없습니다.");
        }
        application.setAddress(applicationRequestDto.getAddress());
        application.setGuardianName(applicationRequestDto.getGuardianName());
        application.setGuardianPhone(applicationRequestDto.getGuardianPhone());
        application.setRemarks(applicationRequestDto.getRemarks());
        applicationRepository.save(application);
    }

    public void updateApplicationStatus(Long id, ApplicationStatus status) {
        Member member = memberService.getCurrentMember();
        Application application = applicationRepository.findById(id).orElseThrow();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 상태를 변경할 수 있습니다.");
        }
        application.setStatus(status);
        applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        Member member = memberService.getCurrentMember();
        Application application = applicationRepository.findById(id).orElseThrow();
        if (!application.getMember().equals(member)) {
            throw new IllegalArgumentException("본인의 신청만 삭제할 수 있습니다.");
        }
        if (application.getStatus().equals(ApplicationStatus.ACCEPTED)) {
            throw new IllegalArgumentException("신청이 완료되어 삭제할 수 없습니다.");
        }else if (application.getStatus().equals(ApplicationStatus.CANCELED)) {
            throw new IllegalArgumentException("신청이 거절되어 삭제할 수 없습니다.");
        }
        applicationRepository.deleteById(id);
    }

}
