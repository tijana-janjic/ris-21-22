package com.example.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Data
public class NewArticleDto {
    private String title;
    private String text;
    private Long cityId;
    private FileDto coverImage;
}
