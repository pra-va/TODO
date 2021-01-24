package lt.pra_va.user.dao;

import lt.pra_va.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Checks if username is taken.
     * @param username to check.
     * @return true if username is taken.
     */
    boolean existsByUsername(String username);

    /**
     * Checks if email is taken.
     * @param email to check.
     * @return true if email is taken.
     */
    boolean existsByEmail(String email);
}
