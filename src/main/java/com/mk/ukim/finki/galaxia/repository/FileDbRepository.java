package com.mk.ukim.finki.galaxia.repository;

import com.mk.ukim.finki.galaxia.model.FileDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDbRepository extends JpaRepository<FileDb, Long> {

    List<FileDb> findAllByLessonId(Long id);
}
