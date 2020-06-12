package lt.pra_va.lists.dto;

import lombok.*;

import java.util.List;

/**
 * DTO to create new list
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateListCommand {

    @NonNull
    private String listName;
    private List<ListItemPayload> listItemPayloads;
}
