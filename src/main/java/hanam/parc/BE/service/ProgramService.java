package hanam.parc.BE.service;

import hanam.parc.BE.mapper.ProgramMapper;
import hanam.parc.BE.repository.ProgramRepository;
import hanam.parc.BE.type.dto.ProgramRequestDto;
import hanam.parc.BE.type.dto.ProgramResponseDto;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.Program;
import hanam.parc.BE.type.etc.ProgramStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final MemberService memberService;

    private final FileUploadService fileUploadService;

    private final ProgramRepository programRepository;

    @Transactional
    public void createProgram(ProgramRequestDto programRequestDto, MultipartFile multipartFile) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 등록할 수 있습니다.");
        }
        Program program = ProgramMapper.INSTANCE.ProgramRequestDtoToProgram(programRequestDto);
        if (programRequestDto.getManagerId() != null) {
            Member manager = memberService.getMemberById(programRequestDto.getManagerId());
            program.setManager(manager);
        }
        String url = null;
        if (multipartFile != null) {
            url = fileUploadService.saveFile(multipartFile);
        }
        program.setThumbnail(url);
        program.setProgramStatus(ProgramStatus.WAITING);
        programRepository.save(program);
    }

    public ProgramResponseDto getProgram(Long id) {
        Program program = programRepository.findById(id).orElseThrow();
        return ProgramMapper.INSTANCE.ProgramToProgramResponseDto(program);
    }

    public List<ProgramResponseDto> getProgramList() {
        List<Program> programList = programRepository.findAll();
        return programList.stream()
                .map(ProgramMapper.INSTANCE::ProgramToProgramResponseDto)
                .collect(Collectors.toList());
    }

    public List<ProgramResponseDto> getMyProgramList() {
        Member member = memberService.getCurrentMember();
        List<Program> programList = programRepository.findAllByManager(member);
        return programList.stream()
                .map(ProgramMapper.INSTANCE::ProgramToProgramResponseDto)
                .collect(Collectors.toList());
    }

    public Page<ProgramResponseDto> getProgramListByPage(Pageable pageable) {
        Page<Program> programList = programRepository.findAllByOrderByApplyEndDesc(pageable);
        return programList.map(ProgramMapper.INSTANCE::ProgramToProgramResponseDto);
    }

    public List<ProgramResponseDto> getProgramListByStatus(ProgramStatus programStatus) {
        List<Program> programList = programRepository.findAllByProgramStatus(programStatus);
        return programList.stream()
                .map(ProgramMapper.INSTANCE::ProgramToProgramResponseDto)
                .collect(Collectors.toList());
    }

    public void updateProgram(Long id, ProgramRequestDto programRequestDto) {
        Member member = memberService.getCurrentMember();
        Program program = programRepository.findById(id).orElseThrow();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("프로그램 수정 권한이 없습니다.");
        }
        program.setName(programRequestDto.getName());
        program.setAvailable(programRequestDto.getAvailable());
        program.setProgramStatus(programRequestDto.getProgramStatus());
        program.setApplyEnd(programRequestDto.getApplyEnd());
        program.setStartDate(programRequestDto.getStartDate());
        program.setEndDate(programRequestDto.getEndDate());
        program.setTime(programRequestDto.getTime());
        program.setLocation(programRequestDto.getLocation());
        program.setCost(programRequestDto.getCost());
        program.setMaterial(programRequestDto.getMaterial());
        program.setManager(memberService.getMemberById(programRequestDto.getManagerId()));
        programRepository.save(program);
    }

    public void updateProgramStatus(Long id, ProgramStatus programStatus) {
        Member member = memberService.getCurrentMember();
        Program program = programRepository.findById(id).orElseThrow();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("프로그램 상태 변경 권한이 없습니다.");
        }
        program.setProgramStatus(programStatus);
        programRepository.save(program);
    }

    public void deleteProgram(Long id) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("프로그램 삭제 권한이 없습니다.");
        }
        programRepository.deleteById(id);
    }

    public Program getProgramById(Long id) {
        return programRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 프로그램입니다."));
    }

    public Boolean checkProgramAvailable(Program program) {
        return program.getProgramStatus().equals(ProgramStatus.ACCEPTING);
    }

}
