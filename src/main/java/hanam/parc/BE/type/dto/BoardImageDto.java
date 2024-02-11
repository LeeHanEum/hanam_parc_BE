package hanam.parc.BE.type.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardImageDto {

    private List<String> boardImageList;

    @Builder
    public BoardImageDto(List<String> boardImageList) {
        this.boardImageList = boardImageList;
    }
}
