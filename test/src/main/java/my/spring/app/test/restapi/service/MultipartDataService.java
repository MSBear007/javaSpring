package my.spring.app.test.restapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MultipartDataService {
    
    public void uploadFile(MultipartFile file, Path path) throws IOException {
        if (file != null && !file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);
        }
    }

    public byte[] downloadFile(Path path) throws IOException{
        return Files.readAllBytes(path);
    }
}
