package org.example.omnibefile.controller;

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
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponseDto> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(fileUploadService.upload(file));
    }
}
