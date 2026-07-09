package com.example.assignment._0706.service;

import com.example.assignment._0706.domain.entity.Board;
import com.example.assignment._0706.domain.repository.BoardRepository;
import com.example.assignment._0706.dto.BoardDeleteRequestDto;
import com.example.assignment._0706.dto.BoardUpdateRequestDto;
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
    private final FileService fileService;

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
        String path = fileService.storeFile(file);
        Board board = Board.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .filePath(path)
                .created(LocalDateTime.now())
                .build();

        boardRepository.save(board);
    }



    @Transactional
    public void updateArticle(Long id, BoardUpdateRequestDto dto) {
        Board board = boardRepository.findById(id).orElseThrow(()->new BoardNotFoundException("게시글 찾기 오류. id="+id));

        String curFilePath = board.getFilePath();
        if(dto.isFileFlag()) {//수정 행동있을 시
            fileService.deleteFile(curFilePath);
            curFilePath = fileService.storeFile(dto.getFile());
        }
        board.update(dto.getTitle(), dto.getContent(), curFilePath, LocalDateTime.now());
    }

    @Transactional
    public void deleteArticle(Long id, BoardDeleteRequestDto dto) {
        if(!boardRepository.existsById(id)) throw new BoardNotFoundException("해당 게시판 찾지 못함! id: " + id);
        boardRepository.deleteById(id);
        fileService.deleteFile(dto.getFilePath());
    }

}
