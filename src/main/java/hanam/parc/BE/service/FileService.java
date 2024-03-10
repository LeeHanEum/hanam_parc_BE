package hanam.parc.BE.service;

import hanam.parc.BE.repository.PopUpRepository;
import hanam.parc.BE.type.entity.PopUp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

    private final PopUpRepository popUpRepository;

    public void popupUpload(Long popupId,  String url) {
        PopUp popUp = popUpRepository.findById(popupId).orElseThrow();
        popUp.setUrl(url);
        popUpRepository.save(popUp);
    }
}
