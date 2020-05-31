package lt.pra_va.lists.dao;

import lt.pra_va.lists.model.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for lists.
 */
public interface ListRepository extends JpaRepository<List, Long> {

    List findListByName(String name);

}
