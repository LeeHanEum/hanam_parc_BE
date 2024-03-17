package hanam.parc.BE.service;

import hanam.parc.BE.mapper.QnAMapper;
import hanam.parc.BE.repository.QnARepository;
import hanam.parc.BE.type.dto.QnARequestDto;
import hanam.parc.BE.type.dto.QnAResponseDto;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.QnA;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QnAService {

    private final MemberService memberService;

    private final QnARepository qnaRepository;

    public void createQnA(QnARequestDto qnaRequestDto) {
        Member member = memberService.getCurrentMember();
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

    public Page<QnAResponseDto> getQnAPage(Pageable pageable){
        Page<QnA> qnaPage = qnaRepository.findAllByOrderByCreatedAtDesc(pageable);
        return qnaPage.map(QnAMapper.INSTANCE::QnAToQnAResponseDto);
    }

    public Page<QnAResponseDto> getMyQnAPage(Pageable pageable){
        Member member = memberService.getCurrentMember();
        Page<QnA> qnaPage = qnaRepository.findAllByWriterOrderByCreatedAtDesc(member, pageable);
        return qnaPage.map(QnAMapper.INSTANCE::QnAToQnAResponseDto);
    }

    public void updateQnA(Long id, QnARequestDto qnaRequestDto) {
        Member member = memberService.getCurrentMember();
        QnA qna = qnaRepository.findById(id).orElseThrow();
        if (!qna.getWriter().equals(member) || memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        qna.setTitle(qnaRequestDto.getTitle());
        qna.setContent(qnaRequestDto.getContent());
        qnaRepository.save(qna);
    }

    public void deleteQnA(Long id) {
        Member member = memberService.getCurrentMember();
        QnA qna = qnaRepository.findById(id).orElseThrow();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        qnaRepository.deleteById(id);
    }

    public void answerQnA(Long id, String answer) {
        Member member = memberService.getCurrentMember();
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
