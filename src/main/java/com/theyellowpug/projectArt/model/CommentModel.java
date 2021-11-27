package com.theyellowpug.projectArt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommentModel {

    private Long id;

    private String text;

    private String ownerName;

    private Long ownerId;

}
