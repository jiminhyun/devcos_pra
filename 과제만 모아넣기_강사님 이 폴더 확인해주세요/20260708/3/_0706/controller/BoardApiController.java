package com.example.assignment._0706.controller;

import com.example.assignment._0706.domain.entity.Board;
import com.example.assignment._0706.dto.BoardDetailResponseDto;
import com.example.assignment._0706.dto.BoardListResponseDto;
import com.example.assignment._0706.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}
