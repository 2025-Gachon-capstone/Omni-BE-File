package org.example.omnibefile.dto.file;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class FileUploadResponseDto {
    private String fileName;
    private String filePath;
    private String fileType;
}
