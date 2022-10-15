package my.spring.app.test.restapi.model;

import javax.persistence.Column;
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
@Table(name = "actors", schema = "public")
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter @Setter
    @Column(name = "name_", nullable = false)
    private String name;

    @Getter @Setter
    private java.sql.Date birthDate;

    @Getter @Setter
    private String sex; // inclusive but most importantly - lazy.

    @ManyToOne
    @JoinColumn(name = "country_code")
    @Getter @Setter
    private Country country;
}
