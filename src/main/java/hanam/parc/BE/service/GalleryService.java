package hanam.parc.BE.service;

import hanam.parc.BE.mapper.GalleryMapper;
import hanam.parc.BE.repository.GalleryRepository;
import hanam.parc.BE.type.dto.GalleryRequestDto;
import hanam.parc.BE.type.dto.GalleryResponseDto;
import hanam.parc.BE.type.entity.Gallery;
import hanam.parc.BE.type.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final MemberService memberService;

    private final GalleryRepository galleryRepository;

    public Long createGallery(GalleryRequestDto galleryRequestDto) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 앨범을 생성할 수 있습니다.");
        }
        Gallery gallery = GalleryMapper.INSTANCE.GalleryRequestDtoToGallery(galleryRequestDto);
        gallery.setMember(member);
        return galleryRepository.save(gallery).getId();
    }

    public GalleryResponseDto getGallery(Long id) {
        Gallery gallery = findGalleryById(id);
        return GalleryMapper.INSTANCE.GalleryToGalleryResponseDto(gallery);
    }

    public Page<GalleryResponseDto> getGalleryPage(Pageable pageable) {
        Page<Gallery> galleryList = galleryRepository.findAllByOrderByCreatedAtDesc(pageable);
        return galleryList.map(GalleryMapper.INSTANCE::GalleryToGalleryResponseDto);
    }

    public void updateGallery(Long id, GalleryRequestDto galleryRequestDto) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 앨범을 수정할 수 있습니다.");
        }
        Gallery gallery = findGalleryById(id);
        gallery.setTitle(galleryRequestDto.getTitle());
        galleryRepository.save(gallery);
    }

    public void deleteGallery(Long id) {
        Member member = memberService.getCurrentMember();
        if (!memberService.checkMemberAdminRole(member)) {
            throw new IllegalArgumentException("관리자만 앨범을 삭제할 수 있습니다.");
        }
        galleryRepository.deleteById(id);
    }

    public Gallery findGalleryById(Long id) {
        return galleryRepository.findById(id).orElseThrow();
    }
}
