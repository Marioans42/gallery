package com.example.demo.controller;

import com.example.demo.repository.dto.ResponseMessage;
import com.example.demo.repository.model.Photo;
import com.example.demo.repository.model.Rubric;
import com.example.demo.service.PhotoService;
import com.example.demo.service.RubricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PhotoController {
    private final PhotoService photoService;
    private final RubricService rubricService;

    public PhotoController(PhotoService photoService, RubricService rubricService) {
        this.photoService = photoService;
        this.rubricService = rubricService;
    }

    @GetMapping(value = {"/", "/home"})
    public String index() {
        return "index";
    }

    @GetMapping(value = {"/admin"})
    public String admin(Model modelAttribute) {
        modelAttribute.addAttribute("photos", getAllPhoto());
        modelAttribute.addAttribute("rubrics", getAllRubric());
        return "admin";
    }

    @GetMapping(value = { "/contact"})
    public String contact() {
        return "contact";
    }

    @GetMapping(value = "/gallery")
    public String gallery(Model modelAttribute) {
        modelAttribute.addAttribute("rubrics", getAllRubric());
        modelAttribute.addAttribute("photos", getAllPhoto());
        modelAttribute.addAttribute("carousel", "carouselGal");
        modelAttribute.addAttribute("slide", "slider");
        return "gallery";
    }

    @PostMapping("/save")
    public @ResponseBody ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("idRubrique") Integer id, HttpServletRequest request) {
        String message = "";
        try {
            Rubric rubric = rubricService.findById(id);
            photoService.savePhoto(file, rubric, request);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<Photo>> getListFiles() {
        List<Photo> files = photoService.getAllFiles().map(dbFile -> new Photo(
                dbFile.getId(),
                dbFile.getName(),
                dbFile.getData(),
                dbFile.getRubric())).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<Photo> getFile(@PathVariable Integer id) {
        Photo photo = photoService.getById(id);
        return ResponseEntity.ok()
                .body(photo);
    }

    @DeleteMapping("/file/{id}")
    public ResponseEntity<Photo> deleteFile(@PathVariable Integer id) {
        photoService.deletePhoto(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Integer id, HttpServletResponse response)
            throws IOException {
        Photo photo = photoService.getById(id);
        response.getOutputStream().write(photo.getData());
        response.getOutputStream().close();
    }

    public List<Rubric> getAllRubric() {
        return rubricService.findAll();
    }

    public List<Photo> getAllPhoto() {
        return photoService.getAllPhoto();
    }
}
