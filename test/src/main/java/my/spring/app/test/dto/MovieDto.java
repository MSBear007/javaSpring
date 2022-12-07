package my.spring.app.test.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import my.spring.app.test.validation.MovieYear;

@Data
public class MovieDto {
    @NotBlank @NotNull
    private String title;

    @MovieYear(oldestPossible = 1888, message = "year must be >= 1888")
    private Integer year;
    
    @NotNull
    private MultipartFile poster;
}
