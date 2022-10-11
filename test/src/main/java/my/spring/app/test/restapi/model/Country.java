package my.spring.app.test.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "countries", schema = "public")
public class Country {

    @Getter
    @Setter
    @Column(name = "name_")
    private String name;

    @Getter
    @Setter
    @Id 
    private String code;

}
