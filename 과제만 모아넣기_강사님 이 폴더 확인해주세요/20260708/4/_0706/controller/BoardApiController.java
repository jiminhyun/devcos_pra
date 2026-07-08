package com.example.assignment._0706.controller;

import com.example.assignment._0706.domain.entity.Board;
import com.example.assignment._0706.dto.BoardDetailResponseDto;
import com.example.assignment._0706.dto.BoardListResponseDto;
import com.example.assignment._0706.dto.BoardWriteRequestDto;
import com.example.assignment._0706.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping
    public BoardListResponseDto getBoardList(@RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "10") int size, HttpSession session) {
        List<Board> boards = boardService.getBoardList(page, size);
        int totalBoards = boardService.getTotalBoards();

        int totalPages = (int)Math.ceil((double)totalBoards / size);
        boolean last = page >= totalPages;

        return BoardListResponseDto.builder().boards(boards).
                last(last).totalPages(totalPages).build();
    }

    @GetMapping("/{id}")
    public BoardDetailResponseDto getBoardDetail(@PathVariable long id) {
        Board board = boardService.getBoardDetail(id);
        return BoardDetailResponseDto.builder().title(board.getTitle()).content(board.getContent())
                .userId(board.getUserId()).filePath(board.getFilePath()).created(board.getCreated()).build();
    }

    @PostMapping
    public void saveArticle(@ModelAttribute BoardWriteRequestDto dto) {
        boardService.saveArticle(dto.getUserId(), dto.getTitle(), dto.getContent(), dto.getFile());
    }

    @GetMapping("/file/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpSession session) {
        Resource resource = boardService.downloadFile(filename);

        // 한글/공백은 헤더에 그대로 못 담으니 URL 인코딩 ( '+' → %20 )
        String encoded = URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)                     // "순수 바이너리" 힌트(유도)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename*=UTF-8''" + encoded)                   // "저장하라" 지시(결정타) //; 뒤 filename은 옵션
                .body(resource); //body안의 내용을 다운
    }
}
