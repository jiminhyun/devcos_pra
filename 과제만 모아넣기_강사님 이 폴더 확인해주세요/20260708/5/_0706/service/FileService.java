package com.example.assignment._0706.service;

import com.example.assignment._0706.exception.BoardFileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) {
        if(file == null || file.isEmpty()) return null;
        try {
            File dir =  new File(uploadDir).getAbsoluteFile();
            if(!dir.exists()) dir.mkdirs();
            String fileName =  UUID.randomUUID() +"_"+file.getOriginalFilename();
            File newFile = new File(dir, fileName);
            file.transferTo(newFile);
            return newFile.getAbsolutePath(); // 테스트
        } catch (IOException e) {
            throw new IllegalStateException("파일 저장 실패",e);
        }
    }

    public Resource downloadFile(String fileName) {
        try {
            File file = new File(new File(uploadDir).getAbsoluteFile(), fileName);
            Resource resource = new UrlResource(file.toURI());
            if (!resource.exists() || !resource.isReadable()) {
                throw new BoardFileNotFoundException("파일 못 찾음! fileName: " + fileName );
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new IllegalStateException("url에 대한 잘못된 접근",e);
        }

    }

    public void deleteFile(String filePath) {
        if(filePath == null || filePath.isBlank()) return;
        File file = new File(filePath);
        if(file.exists()) file.delete();
    }
}
