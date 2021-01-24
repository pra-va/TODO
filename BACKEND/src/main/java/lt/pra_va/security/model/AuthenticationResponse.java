package lt.pra_va.security.model;

import java.io.Serializable;

// TODO rewrite with lombok
public class AuthenticationResponse implements Serializable {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
