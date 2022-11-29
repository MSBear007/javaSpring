package my.spring.app.test.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Data
public class GetMovieDto {
    
    @NotBlank @NotNull
    private String title;

    private double rating;

    private int year;

    private byte[] poster;
}
