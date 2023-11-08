package com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
public interface IUploadFileService {
   void uploadFile(InputStream inputStream, String nameImg);
}
