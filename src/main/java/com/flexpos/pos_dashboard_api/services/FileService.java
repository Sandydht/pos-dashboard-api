package com.flexpos.pos_dashboard_api.services;

import org.springframework.web.multipart.MultipartFile;

import com.flexpos.pos_dashboard_api.models.common.WebResponse;

public interface FileService {
  
  public WebResponse<String> uploadFile(MultipartFile file);

}
