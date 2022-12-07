package my.spring.app.test.events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

public class UpdateRatingEvent extends ApplicationEvent {

    @Getter @Setter
    private long movieId;

    public UpdateRatingEvent(Object source, long movieId) {
        super(source);
        this.movieId = movieId;
    }
}
