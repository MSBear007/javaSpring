package my.spring.app.test.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import my.spring.app.test.validation.Rating1To10;

@Data
public class RatingDto {

    @Rating1To10
    private int rating;

    @NotNull
    private long movieId;
}
