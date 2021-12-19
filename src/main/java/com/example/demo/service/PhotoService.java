package com.example.demo.service;

import com.example.demo.repository.model.Photo;
import com.example.demo.repository.model.Rubric;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface PhotoService {
    void savePhoto(MultipartFile file, Rubric rubric, HttpServletRequest request) throws IOException;
    void deletePhoto(Integer id);
    Photo getById(Integer id);
    Stream<Photo> getAllFiles();
    List<Photo> getAllPhoto();
}
