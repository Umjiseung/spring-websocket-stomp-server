package server.domain.auth.repository;

import org.springframework.data.repository.CrudRepository;
import server.global.util.count.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
