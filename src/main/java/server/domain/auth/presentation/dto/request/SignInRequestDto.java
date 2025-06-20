package server.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequestDto {
    @NotNull
    private String nickname;
    @NotNull
    private String password;
}
