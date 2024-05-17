package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class DtoUserCreate {
    private String email;
    private String password;
    private String name;
}
