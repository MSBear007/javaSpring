package my.spring.app.test.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import my.spring.app.test.exceptions.ResourceNotFoundException;
import my.spring.app.test.restapi.service.MovieService;

@Component
@Slf4j
public class UpdateRatingEventListener implements ApplicationListener<UpdateRatingEvent> {

    @Autowired
    private MovieService service;
    @Override
    public void onApplicationEvent(UpdateRatingEvent event) {
        try {
            log.info("rating updated LOL");
            service.updateRating(event.getMovieId());
        }
        catch (ResourceNotFoundException exc) {
            // TODO something
            return;
        }
        
    }
}
