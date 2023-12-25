package com.mk.ukim.finki.galaxia.service;

import com.mk.ukim.finki.galaxia.model.FileDb;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileStorageService {

    public FileDb store(MultipartFile file) throws IOException;
    public FileDb getFile(Long id);
    public Stream<FileDb> getAllFiles();

}
