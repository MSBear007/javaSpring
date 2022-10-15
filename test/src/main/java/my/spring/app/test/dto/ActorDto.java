package my.spring.app.test.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ActorDto {
    @NotBlank @NotNull
    private String name;

    private String birthDate;

    private String country;

    private String sex;



}
