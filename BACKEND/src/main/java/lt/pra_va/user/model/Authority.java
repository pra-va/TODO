package lt.pra_va.user.model;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "authority_type", nullable = false)
    private AuthorityType authorityType;

    public Authority(AuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
