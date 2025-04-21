package com.app.feelog.service;

import com.app.feelog.domain.dto.FileDTO;
import com.app.feelog.domain.vo.FileVO;
import com.app.feelog.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilesService {

    private final FileDAO fileDAO;

    public FileDTO upload(MultipartFile file) {
        if (file.isEmpty() || file.getOriginalFilename().isBlank()) {
            return null;
        }

        String todayPath = getPath(); // yyyy/MM/dd
        String rootPath = "C:/upload/" + todayPath;
        UUID uuid = UUID.randomUUID();
        String storedFileName = uuid + "_" + file.getOriginalFilename();
        String thumbnailName = "t_" + storedFileName;

        try {
            // 디렉토리 생성
            File directory = new File(rootPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 원본 파일 저장
            File originalFile = new File(rootPath, storedFileName);
            file.transferTo(originalFile);

            // 썸네일 생성 (이미지일 경우)
            if (file.getContentType() != null && file.getContentType().startsWith("image")) {
                File thumbnailFile = new File(rootPath, thumbnailName);
                try (FileOutputStream out = new FileOutputStream(thumbnailFile)) {
                    Thumbnailator.createThumbnail(file.getInputStream(), out, 1300, 350);
                }
            }

            // VO 생성
            FileVO fileVO = FileVO.builder()
                    .filePath(todayPath)
                    .fileName("t_" + storedFileName) // 썸네일 이름 저장
                    .fileSize(String.valueOf(file.getSize()))
                    .build();

            // 🔥 DB 저장
            fileDAO.save(fileVO);

            // VO → DTO로 변환
            FileDTO fileDTO = new FileDTO();
            fileDTO.setId(fileVO.getId()); // DB insert 후 PK 사용 시점
            fileDTO.setFilePath(fileVO.getFilePath());
            fileDTO.setFileName(fileVO.getFileName());
            fileDTO.setFileSize(fileVO.getFileSize());
            fileDTO.setFileStatus(fileVO.getFileStatus());
            fileDTO.setCreatedDate(fileVO.getCreatedDate());
            fileDTO.setUpdatedDate(fileVO.getUpdatedDate());

            return fileDTO;

        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }

    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
