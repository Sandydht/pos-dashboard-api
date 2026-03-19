package com.flexpos.pos_dashboard_api.services.implementations;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.flexpos.pos_dashboard_api.exceptions.InvariantException;
import com.flexpos.pos_dashboard_api.models.common.WebResponse;
import com.flexpos.pos_dashboard_api.services.FileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImplementation implements FileService {

  @Value("${file.upload-dir}")
  private String uploadDir;

  @Override
  public WebResponse<String> uploadFile(MultipartFile file) {
    try {
      if (file.isEmpty()) {
        throw new InvariantException("File is empty");
      }

      if (!file.getContentType().startsWith("image/")) {
        throw new InvariantException("File must be an image");
      }

      String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

      Path path = Paths.get(uploadDir);

      if (!Files.exists(path)) {
        Files.createDirectories(path);
      }

      Files.copy(file.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

      return WebResponse
        .<String>builder()
        .status("OK")
        .data(fileName)
        .build();
    } catch (IOException e) {
      throw new InvariantException("Failed to upload file");
    }
  }

}
