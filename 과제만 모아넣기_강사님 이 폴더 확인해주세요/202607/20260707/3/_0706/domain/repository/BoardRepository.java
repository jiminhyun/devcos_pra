package com.example.assignment._0706.domain.repository;

import com.example.assignment._0706.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
