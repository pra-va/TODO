package lt.pra_va.lists.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.pra_va.lists.model.ImportanceStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListItem4Client {
    private int orderNumber;
    private String payload;
    private Date created;
    private Date updated;
    private ImportanceStatus status;
}
