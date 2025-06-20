package server.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpRequestDto {
    @NotNull
    private String name;

    @NotNull
    private String nickname;

    @NotNull
    private String password;
}
