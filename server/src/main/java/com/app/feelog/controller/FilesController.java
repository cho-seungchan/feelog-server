package com.app.feelog.controller;

import com.app.feelog.domain.dto.FileDTO;
import com.app.feelog.domain.vo.FileVO;
import com.app.feelog.service.FilesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/file")
public class FilesController {

//    private final FilesService filesService;
//
//    /**
//     * 단일 파일 업로드 (썸네일 생성 포함)
//     */
//    @PostMapping("/upload")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {
//        FileDTO fileDTO = filesService.upload(file);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("thumbnail", fileDTO);
//
//        return ResponseEntity.ok(response);
//    }
//
//    /**
//     * 다중 파일 업로드
//     */
//    @PostMapping("/upload/multi")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
//        List<FileDTO> fileDTOList = new ArrayList<>();
//
//        for (MultipartFile file : files) {
//            FileDTO fileDTO = filesService.upload(file);
//            if (fileDTO != null) {
//                fileDTOList.add(fileDTO);
//            }
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("thumbnails", fileDTOList);
//
//        return ResponseEntity.ok(response);
//    }
//
//    /**
//     * 이미지 출력 (썸네일 포함)
//     */
//    @GetMapping("/display")
//    @ResponseBody
//    public byte[] display(String path) throws IOException {
//        try {
//            return FileCopyUtils.copyToByteArray(new File("/upload/" + path));
//        } catch (NoSuchFileException e) {
//            throw new RuntimeException("파일을 찾을 수 없습니다: " + path);
//        }
//    }
//
//    /**
//     * 파일 다운로드
//     */
//    @GetMapping("/download")
//    public ResponseEntity<Resource> download(String path) throws IOException {
//        Resource resource = new FileSystemResource("/upload/" + path);
//        HttpHeaders headers = new HttpHeaders();
//
//        String encodedName = URLEncoder.encode(path.split("_", 2)[1], "UTF-8");
//        headers.add("Content-Disposition", "attachment; filename=" + new String(encodedName.getBytes("UTF-8"), "ISO-8859-1"));
//
//        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
//    }
//}


    private final FilesService fileService;

    @PostMapping("upload")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {

        FileVO thumbnail = fileService.upload(file);

        Map<String, Object> response = new HashMap<>();
        response.put("thumbnail", thumbnail);

        return ResponseEntity.ok(response);
    }

    @PostMapping("upload/multi")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        log.info("들어옴");

        List<FileVO> thumbnails = new ArrayList<>();
        files.forEach( file -> {
            thumbnails.add(fileService.upload(file));
        });

        Map<String, Object> response = new HashMap<>();
        response.put("thumbnails", thumbnails);

        return ResponseEntity.ok(response);
    }

    @GetMapping("display")
    @ResponseBody
    public byte[] display(String path) throws IOException{
        byte[] file = null;
        try {
            file = FileCopyUtils.copyToByteArray(new File("C:/upload/" + path));
        }catch (NoSuchFileException e){
            throw new RuntimeException();
        }
        return file;
    }

    @GetMapping("download")
    public ResponseEntity<Resource> download(String path) throws IOException{
        Resource resource = new FileSystemResource("C:/upload/" + path);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + new String(("한동석짱_" + path.split("_")[1]).getBytes("UTF-8"), "ISO-8859-1"));
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
}
