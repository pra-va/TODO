package lt.pra_va.lists.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * List entity that will contain list items.
 */
@Entity
@Table(name = "lists")
@Getter @Setter @NoArgsConstructor
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private ArrayList<String> items = new ArrayList<>();

    public void addMultipleItems(String[] items) {
        for (String item : items) {
            this.items.add(item);
        }
    }
}
