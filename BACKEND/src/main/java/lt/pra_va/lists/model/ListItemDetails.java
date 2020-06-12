package lt.pra_va.lists.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "list_details")
@Getter
@Setter
@NoArgsConstructor
public class ListItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @OneToOne(mappedBy = "listItemDetails")
    private ListItem listItem;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", updatable = false, nullable = false)
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edited")
    private Date dateEdited;

}
