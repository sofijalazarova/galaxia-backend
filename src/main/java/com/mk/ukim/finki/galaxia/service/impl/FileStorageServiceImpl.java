package com.mk.ukim.finki.galaxia.service.impl;

import com.mk.ukim.finki.galaxia.model.FileDb;
import com.mk.ukim.finki.galaxia.repository.FileDbRepository;
import com.mk.ukim.finki.galaxia.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private FileDbRepository fileDbRepository;


    @Override
    public FileDb store(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDb fileDb = new FileDb(fileName, file.getContentType(), file.getBytes());

        return this.fileDbRepository.save(fileDb);
    }

    @Override
    public FileDb getFile(Long id) {
        return this.fileDbRepository.findById(id).get();
    }

    @Override
    public Stream<FileDb> getAllFiles() {
        return this.fileDbRepository.findAll().stream();
    }
}
