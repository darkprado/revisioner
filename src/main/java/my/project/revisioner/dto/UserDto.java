package my.project.revisioner.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


/**
 * @author s.melekhin
 * @since 24 май 2023 г.
 */
@Data
public class UserDto {

    @Schema(description = "Индентификатор пользователя")
    private Long id;
    @Schema(description = "Имя пользователя")
    @NotEmpty
    private String firstname;
    @Schema(description = "Фамилия пользователя")
    @NotEmpty
    private String lastname;
    @Schema(description = "Никнейм пользователя")
    private String username;

}
