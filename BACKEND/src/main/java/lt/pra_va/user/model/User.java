package lt.pra_va.user.model;

import lombok.*;
import lt.pra_va.user.model.enums.Role;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, updatable = false)
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

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "isActivated", nullable = false)
    private boolean isActivated;

    @Column(name = "isBlocked", nullable = false)
    private boolean isBlocked;

}
