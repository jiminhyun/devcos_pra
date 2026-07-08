package com.example.assignment._0706.service;

import com.example.assignment._0706.domain.entity.Board;
import com.example.assignment._0706.domain.repository.BoardRepository;
import com.example.assignment._0706.exception.BoardFileNotFoundException;
import com.example.assignment._0706.exception.BoardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.UIResource;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;

    public List<Board> getBoardList(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        return boardRepository.findAll(pageable).getContent();
    }

    public int getTotalBoards() {
        return (int) boardRepository.count();
    }

    public Board getBoardDetail(Long id) {
        return boardRepository.findById(id).orElseThrow(()-> new BoardNotFoundException("해당 "+ id +"님의 게시글을 찾을 수 없습니다!"));
    }

    @Transactional
    public void saveArticle(String userId, String title, String content, MultipartFile file) {
        String path = storeFile(file);
        Board board = Board.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .filePath(path)
                .created(LocalDateTime.now())
                .build();

        boardRepository.save(board);
    }

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
}
