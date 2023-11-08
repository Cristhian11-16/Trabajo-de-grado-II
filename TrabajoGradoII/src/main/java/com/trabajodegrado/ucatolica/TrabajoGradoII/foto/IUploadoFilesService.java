package com.trabajodegrado.ucatolica.TrabajoGradoII.foto;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadoFilesService {
    String handleFileUpload(MultipartFile file) throws Exception;


}
