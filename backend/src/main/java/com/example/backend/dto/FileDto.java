package com.example.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@Data
public class FileDto implements Serializable {
    private String altText;
    private byte[] data;
}
