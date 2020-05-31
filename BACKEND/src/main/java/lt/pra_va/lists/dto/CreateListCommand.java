package lt.pra_va.lists.dto;

import lombok.*;

/**
 * DTO to create new list
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreateListCommand {
    @NonNull
    private String name;
    private String[] listItems;
}
