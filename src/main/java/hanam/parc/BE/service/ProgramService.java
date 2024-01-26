package hanam.parc.BE.service;

import hanam.parc.BE.mapper.ProgramMapper;
import hanam.parc.BE.repository.ProgramRepository;
import hanam.parc.BE.type.dto.ProgramDto;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.Program;
import hanam.parc.BE.type.etc.ProgramStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final MemberService memberService;

    private final ProgramRepository programRepository;

    public void createProgram(ProgramDto programDto) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 등록할 수 있습니다.");
        }
        Program program = ProgramMapper.INSTANCE.ProgramDtoToProgram(programDto);
        program.setManager(member);
        programRepository.save(program);
    }

    public ProgramDto getProgram(Long id) {
        Program program = programRepository.findById(id).orElseThrow();
        return ProgramMapper.INSTANCE.ProgramToProgramDto(program);
    }

    public List<ProgramDto> getProgramList() {
        List<Program> programList = programRepository.findAll();
        return programList.stream()
                .map(ProgramMapper.INSTANCE::ProgramToProgramDto)
                .collect(Collectors.toList());
    }

    public List<ProgramDto> getMyProgramList() {
        Member member = memberService.getCurrentMember();
        List<Program> programList = programRepository.findAllByManager(member);
        return programList.stream()
                .map(ProgramMapper.INSTANCE::ProgramToProgramDto)
                .collect(Collectors.toList());
    }

    public List<ProgramDto> getProgramListByStatus(ProgramStatus programStatus) {
        List<Program> programList = programRepository.findAllByProgramStatus(programStatus);
        return programList.stream()
                .map(ProgramMapper.INSTANCE::ProgramToProgramDto)
                .collect(Collectors.toList());
    }

    public void updateProgram(Long id, ProgramDto programDto) {
        Member member = memberService.getCurrentMember();
        Program program = programRepository.findById(id).orElseThrow();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("프로그램 수정 권한이 없습니다.");
        }
        program.setName(programDto.getName());
        program.setThumbnail(programDto.getThumbnail());
        program.setCategory(programDto.getCategory());
        program.setAvailable(programDto.getAvailable());
        program.setProgramStatus(programDto.getProgramStatus());
        program.setDisabilityType(programDto.getDisabilityType());
        program.setApplyPeriod(programDto.getApplyPeriod());
        program.setApplyMethod(programDto.getApplyMethod());
        program.setStartDate(programDto.getStartDate());
        program.setEndDate(programDto.getEndDate());
        program.setEducationTime(programDto.getEducationTime());
        program.setLocation(programDto.getLocation());
        program.setCost(programDto.getCost());
        program.setMaterial(programDto.getMaterial());
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

}
