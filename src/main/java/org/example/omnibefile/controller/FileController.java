package org.example.omnibefile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.omnibefile.dto.file.FileUploadResponseDto;
import org.example.omnibefile.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileUploadService fileUploadService;

    @PostMapping(value ="/upload", consumes = "multipart/form-data")
    @Operation(summary = "이미지 등 파일 업로드 API",description = "이미지 등 파일 업로드를 위한 Api입니다.",tags = "File")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "COMMON200-성공",content = @Content(schema = @Schema(implementation = FileUploadResponseDto.class))),
    })

    public ResponseEntity<FileUploadResponseDto> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        return ResponseEntity.ok(fileUploadService.upload(file));
    }
}
