package com.example.demo.service.impl;

import com.example.demo.repository.dao.PhotoRepository;
import com.example.demo.repository.model.Photo;
import com.example.demo.repository.model.Rubric;
import com.example.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;

    @Value("${uploadDir}")
    private String uploadFolder;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public void savePhoto(MultipartFile file, Rubric rubric, HttpServletRequest request) throws IOException {
        String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePath = Paths.get(uploadDirectory, fileName).toString();
        try {
            File dir = new File(uploadDirectory);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // Save the file locally
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Photo photo = new Photo(fileName, file.getBytes(), rubric);
        photoRepository.save(photo);
    }

    @Override
    public void deletePhoto(Integer id) {
        Photo photo = photoRepository.getById(id);
        photoRepository.delete(photo);
    }

    @Override
    public Photo getById(Integer id) {
        return photoRepository.findById(id).get();
    }

    @Override
    public Stream<Photo> getAllFiles() {
        return photoRepository.findAll().stream();
    }

    @Override
    public List<Photo> getAllPhoto() {
        return photoRepository.findAll();
    }
}
