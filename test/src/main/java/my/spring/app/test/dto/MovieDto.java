package my.spring.app.test.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MovieDto {
    @NotBlank @NotNull
    private String title;

    private int year;
    
    private MultipartFile poster;
}
