package my.spring.app.test.restapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies_ratings", schema = "public")
@Entity
public class MoviesRatings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    
    @ManyToOne
    @JoinColumn(name="movie_id")
    @Getter @Setter
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter
    private User user;


    // TODO: validation 1 -- 10
    @Getter @Setter
    private int stars;

}
