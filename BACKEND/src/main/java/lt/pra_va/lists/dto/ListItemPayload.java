package lt.pra_va.lists.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.pra_va.lists.model.ImportanceStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListItemPayload {
    private String payload;
    private ImportanceStatus importanceStatus;
}
