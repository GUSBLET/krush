package krush.ua.token;

import krush.ua.account.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {
    private final TokenRepository tokenRepository;
    @Value("${host.link}")
    private String HOST_LINK;

    public String generateHostUrl(Token token, String url){
        return HOST_LINK + url + token.getToken();
    }

    public Token generateAndSaveToken(Account account, LocalDateTime lifeTime) {
        return tokenRepository.save(Token.builder()
                .token(UUID.randomUUID().toString())
                .timeOfCreation(LocalDateTime.now())
                .lifetime(lifeTime)
                .account(account)
                .operationType(OperationType.CONFIRMING_ACCOUNT.name())
                .build());
    }

    public boolean verifyToken(Token token) {
        if (token == null) {
            log.error("token confirmation", new IllegalArgumentException());
            return false;
        }
        if (token.getLifetime().isBefore(LocalDateTime.now())) {
            log.error("the life of the link is over", new IllegalArgumentException());
            return false;
        }
        return true;
    }

    public Token findToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException(token + " not exist"));
    }

    public void delete(Token token) {
        tokenRepository.delete(token);
    }
}
