package com.theyellowpug.projectArt.dTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProfileCardDTO {
    private Long id;

    private Long clientId;

    private String nickname;

    private String title;

    private String shortDescription;

}
