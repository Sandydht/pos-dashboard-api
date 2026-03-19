package com.flexpos.pos_dashboard_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.flexpos.pos_dashboard_api.models.common.WebResponse;
import com.flexpos.pos_dashboard_api.services.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

  private final FileService fileService;

  @PostMapping("/uploads")
  public ResponseEntity<WebResponse<String>> uploadFile(@RequestParam("file") MultipartFile file) {
    WebResponse<String> response = fileService.uploadFile(file);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(response);
  }

}
