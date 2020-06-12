package lt.pra_va.lists.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * List entity that will contain list items.
 */
@Entity
@Table(name = "lists")
@Getter
@Setter
@NoArgsConstructor
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name="list_to_items")
    @MapKeyColumn(name="order_number")
    private Map<Integer, ListItem> items = new HashMap<>();

    public void addItem(ListItem item, Integer key) {
        this.items.put(key, item);
    }

    public Integer getNextKey() {
        Map.Entry<Integer, ListItem> maxEntry = null;
        for (Map.Entry<Integer, ListItem> entry : items.entrySet()) {
            if (maxEntry == null || entry.getKey().intValue() > maxEntry.getKey().intValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey() + 1;
    }
}
