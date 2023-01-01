package my.spring.app.test.restapi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User model
 * used both for account details and registration details (currently)
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    @Column(nullable = false, unique = true)
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String email;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="users_roles",
        joinColumns = @JoinColumn(
            name="user_id", referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
            name="role_id", referencedColumnName = "id"
        ))
    private List<UserRole> roles;

    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<MoviesRatings> ratings;
    
}
