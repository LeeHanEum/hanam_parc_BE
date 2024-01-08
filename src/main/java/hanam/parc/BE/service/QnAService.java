package hanam.parc.BE.service;

import hanam.parc.BE.mapper.QnAMapper;
import hanam.parc.BE.repository.QnARepository;
import hanam.parc.BE.type.dto.QnARequestDto;
import hanam.parc.BE.type.dto.QnAResponseDto;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.QnA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QnAService {

    private final MemberService memberService;

    private final QnARepository qnaRepository;

    public void createQnA(QnARequestDto qnaRequestDto) {
        Member member = memberService.getMemberById("leehaneum");
        QnA qna = QnAMapper.INSTANCE.QnARequestDtoToQnA(qnaRequestDto);
        qna.setWriter(member);
        qna.setIsAnswered(false);
        qnaRepository.save(qna);
    }

    public QnAResponseDto getQnA(Long id) {
        QnA qna = qnaRepository.findById(id).orElseThrow();
        return QnAMapper.INSTANCE.QnAToQnAResponseDto(qna);
    }

    public List<QnAResponseDto> getQnAList() {
        List<QnA> qnaList = qnaRepository.findAll();
        return qnaList.stream()
                .map(QnAMapper.INSTANCE::QnAToQnAResponseDto)
                .collect(Collectors.toList());
    }

    public List<QnAResponseDto> getMyQnA() {
        Member member = memberService.getMemberById("leehaneum");
        List<QnA> qnaList = qnaRepository.findAllByWriter(member);
        return qnaList.stream()
                .map(QnAMapper.INSTANCE::QnAToQnAResponseDto)
                .collect(Collectors.toList());
    }

    public void updateQnA(Long id, QnARequestDto qnaRequestDto) {
        String uid = "leehaneum";
        QnA qna = qnaRepository.findById(id).orElseThrow();
        if(!qna.getWriter().getId().equals(uid)) {
            throw new RuntimeException("작성자가 아닙니다.");
        }
        qna.setTitle(qnaRequestDto.getTitle());
        qna.setContent(qnaRequestDto.getContent());
        qnaRepository.save(qna);
    }

    public void deleteQnA(Long id) {
        Member member = memberService.getMemberById("leehaneum");
        QnA qna = qnaRepository.findById(id).orElseThrow();
        if (memberService.checkMemberAdminRole(member) || qna.getWriter().getId().equals(member.getId())) {
            qnaRepository.deleteById(id);
        } else {
            throw new RuntimeException("작성자가 아닙니다.");
        }
    }

    public void answerQnA(Long id, String answer) {
        Member member = memberService.getMemberById("superuser");
        if (!memberService.checkMemberAdminRole(member)){
           throw new RuntimeException("관리자만 답변을 달 수 있습니다.");
        }
        QnA qna = qnaRepository.findById(id).orElseThrow();
        qna.setAnswer(answer);
        qna.setAnswerer(member);
        qna.setIsAnswered(true);
        qnaRepository.save(qna);
    }
}
