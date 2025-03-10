package com.example.spring.basicboardv2.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private final String UPLOADED_FOLDER = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "java_2" + File.separator + "springboot" + File.separator + "uploads" + File.separator;

    public String fileUpLoad(MultipartFile file) {
        // 파일 저장 로직
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            Files.write(path, bytes);

            return UPLOADED_FOLDER + file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Resource downloadFile(String fileName) {
        try {
            Path path = Paths.get(UPLOADED_FOLDER + fileName).normalize();
            UrlResource resource = new UrlResource(path.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("파일을 찾을 수 없거나 읽을 수 없습니다.");
            }

            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    // 파일 삭제 메서드
    public void deleteFile(String filePath) {
        try {
            if(!filePath.trim().isEmpty()) {
                Path path = Paths.get(filePath);
                Files.deleteIfExists(path); // 파일이 존재하면 삭제
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
