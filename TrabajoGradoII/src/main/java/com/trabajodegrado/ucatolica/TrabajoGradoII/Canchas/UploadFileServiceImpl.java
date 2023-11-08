package com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileServiceImpl implements IUploadFileService{
    @Override
    public void uploadFile(InputStream inputStream, String nameImg) {
        try {
            byte[] bytes = inputStream.readAllBytes();
            Path path = Paths.get("src", "main","resources","upload");
            path = Paths.get(path + "/" + nameImg);
            Files.write(path, bytes);
            System.out.println(path);
        }catch (IOException e){
            System.out.println("perro " + e);
        }

    }

}

