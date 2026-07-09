package com.example.assignment._0706.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import static lombok.AccessLevel.PROTECTED;
import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED) //기본생성자는 필수라서 외부접근 막기용으로 protected
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 50)
    private String userId;

    @Column(length = 255)
    private String filePath;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    public void update(String title, String content, String filePath, LocalDateTime created) {
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.created = created;
    }

}
