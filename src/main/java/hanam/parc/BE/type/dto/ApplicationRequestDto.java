package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.etc.DisabilityType;
import hanam.parc.BE.type.etc.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequestDto {

    private String memberId;

    private Long programId;

    private String address;

    private String phone;

    private Gender gender;

    private DisabilityType disabilityType;

    private String guardianName;

    private String guardianPhone;

    private String remarks;

}
