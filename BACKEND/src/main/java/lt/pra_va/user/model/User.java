package lt.pra_va.user.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreated", updatable = false, nullable = false)
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateEdited", nullable = false)
    private Date dateEdited;

    @Column(name = "username", unique = true, updatable = false, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "authorities", nullable = false)
    @ElementCollection(targetClass = AuthorityType.class)
    @JoinTable(name = "user_to_authorities", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<AuthorityType> authorities;

    @Column(name = "isAccountNonExpired", nullable = false)
    private boolean isAccountNonExpired;

    @Column(name = "isAccountNonLocked", nullable = false)
    private boolean isAccountNonLocked;

    @Column(name = "isCredentialsNonExpired", nullable = false)
    private boolean  isCredentialsNonExpired;

    @Column(name = "isEnabled", nullable = false)
    private boolean isEnabled;

    public User(Date dateCreated, Date dateEdited, String username, String password, String email,
                List<AuthorityType> authorities, boolean isAccountNonExpired, boolean isAccountNonLocked,
                boolean isCredentialsNonExpired, boolean isEnabled) {
        this.dateCreated = dateCreated;
        this.dateEdited = dateEdited;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

}
