package org.example.omnibefile.service;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.example.omnibefile.common.exception.CustomNotFoundException;
import org.example.omnibefile.common.property.GcpProperty;
import org.example.omnibefile.dto.file.FileUploadResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    private final Storage storage = StorageOptions.getDefaultInstance().getService();
    private final GcpProperty gcpProperty;

    public FileUploadResponseDto upload(MultipartFile file) {
        try {
            String fileName = generateFileName(file.getOriginalFilename());
            BlobInfo blobInfo = BlobInfo.newBuilder(gcpProperty.getBucket(), fileName).build();
            storage.create(blobInfo, file.getBytes());
            String filePath = getFilePath(fileName);
            String fileType = file.getContentType();

            return FileUploadResponseDto.builder()
                    .fileName(fileName)
                    .filePath(filePath)
                    .fileType(fileType)
                    .build();
        } catch (IOException e) {
            throw new CustomNotFoundException();
        }
    }

    private String getFilePath(String fileName) {
        return String.format("https://storage.googleapis.com/%s/%s", gcpProperty.getBucket(), fileName);
    }

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "-" + originalFileName;
    }
}
