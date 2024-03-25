package hanam.parc.BE.type.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class BoardFileDto {

    private List<String> boardFileUrl;

    private List<String> boardFileName;

    public BoardFileDto(List<String> boardFileUrl, List<String> boardFileName) {
        this.boardFileUrl = boardFileUrl;
        this.boardFileName = boardFileName;
    }

    public BoardFileDto(Map<String, String> boardFileList) {
        this.boardFileName = new ArrayList<>(boardFileList.keySet());
        this.boardFileUrl = new ArrayList<>(boardFileList.values());
    }

}
