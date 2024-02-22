package hanam.parc.BE.service;

import hanam.parc.BE.mapper.PopUpMapper;
import hanam.parc.BE.repository.PopUpRepository;
import hanam.parc.BE.type.dto.PopUpRequestDto;
import hanam.parc.BE.type.dto.PopUpResponseDto;
import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.PopUp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PopUpService {

    private final MemberService memberService;

    private final FileUploadService fileUploadService;

    private final PopUpRepository popUpRepository;

    @Transactional
    public void createPopUp(PopUpRequestDto popUpRequestDto, MultipartFile multipartFile) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 팝업 등록이 가능합니다.");
        }

        PopUp popUp = PopUpMapper.INSTANCE.PopUpRequestDtoToPopUp(popUpRequestDto);
        popUpRepository.save(popUp);
    }

    public PopUpResponseDto getPopUp(Long id) {
        PopUp popUp = getPopUpById(id);
        return PopUpMapper.INSTANCE.PopUpToPopUpResponseDto(popUp);
    }

    public Page<PopUpResponseDto> getPopUpByPageIsShow(Pageable pageable, boolean isShow) {
        Page<PopUp> popUpList = popUpRepository.findAllByIsShowOrderByCreatedAtDesc(isShow, pageable);
        return popUpList.map(PopUpMapper.INSTANCE::PopUpToPopUpResponseDto);
    }

    public Page<PopUpResponseDto> getPopUpByPage(Pageable pageable) {
        Page<PopUp> popUpList = popUpRepository.findAllByOrderByCreatedAtDesc(pageable);
        return popUpList.map(PopUpMapper.INSTANCE::PopUpToPopUpResponseDto);
    }

    public void updatePopUp(Long id){
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 팝업 수정이 가능합니다.");
        }
        PopUp popUp = getPopUpById(id);
        popUp.setIsShow(!popUp.getIsShow());
        popUpRepository.save(popUp);
    }

    public void deletePopUp(Long id) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 팝업 삭제가 가능합니다.");
        }
        PopUp popUp = getPopUpById(id);
        popUpRepository.delete(popUp);
    }

    private PopUp getPopUpById(Long id) {
        return popUpRepository.findById(id).orElseThrow();
    }



}
