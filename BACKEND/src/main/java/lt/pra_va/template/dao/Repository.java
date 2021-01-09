package lt.pra_va.template.dao;

import lt.pra_va.template.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for lists.
 */
public interface Repository extends JpaRepository<Model, Long> {
}
