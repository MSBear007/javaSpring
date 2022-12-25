package my.spring.app.test.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ActorDto {
    @NotBlank @NotNull
    private String name;

    @NotBlank @NotNull
    private String birthDate;

    @NotBlank @NotNull
    private String country;

    @NotBlank @NotNull
    private String sex;

    private MultipartFile thumbnail;

}
