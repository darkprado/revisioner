package my.project.revisioner.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


/**
 * @author s.melekhin
 * @since 24 май 2023 г.
 */
@Data
public class LoginRequest {

    @Schema(description = "Никнейм пользователя")
    @NotEmpty(message = "Username can't be empty")
    private String username;
    @Schema(description = "Пароль пользователя")
    @NotEmpty(message = "Password can't be empty")
    private String password;

}
