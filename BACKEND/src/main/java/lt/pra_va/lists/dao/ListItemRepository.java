package lt.pra_va.lists.dao;

import lt.pra_va.lists.model.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
}
