package com.trabajodegrado.ucatolica.TrabajoGradoII.foto;

import com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas.IUploadFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class UploadFilesServiceImple implements IUploadoFilesService {


    @Override
    public String handleFileUpload(MultipartFile file) throws Exception {
        try {
            String fileName = UUID.randomUUID().toString();
            byte[] bytes = file.getBytes();
            String fileOriginalName = file.getOriginalFilename();

            long fileSize = file.getSize();
            long maxFileSize = 5*1024*1024;

            if (fileSize>maxFileSize){
                return "File size must be less then or equal 5MB";
            }

            if (!fileOriginalName.endsWith(".jpg") && !fileOriginalName.endsWith(".jpeg") && !fileOriginalName.endsWith(".png")){
                return "Only JPG, JPEG, PNG file are allower";
            }

            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String newFileName = fileName + fileExtension;

            File folder = new File("src/main/resources/pictures");
            if (!folder.exists()){
                folder.mkdir();
            }

            Path path = Paths.get("src/main/resources/pictures/" + newFileName);
            Files.write(path, bytes);
            return "File Upload succesfully";


        } catch (Exception e) {
            throw  new Exception(e.getMessage());

        }

    }
}

