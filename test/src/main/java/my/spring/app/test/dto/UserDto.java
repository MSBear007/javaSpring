package my.spring.app.test.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class UserDto {
    @Getter @Setter @NotNull @NotBlank
    private String username;
    
    @Getter @Setter @NotNull @NotBlank
    private String password;

    @Getter @Setter @NotNull @NotBlank
    private String passwordConfirm;

    @Getter @Setter 
    private String email;
}
