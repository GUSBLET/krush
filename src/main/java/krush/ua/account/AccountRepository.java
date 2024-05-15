package krush.ua.account;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByEmail(String email);

    @Modifying
    @Query("UPDATE Account a SET a.accountEnabling = true WHERE a.email = :email")
    @Transactional
    void confirmAccountByEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE Account a SET a.password = :newPassword WHERE a.id = :id")
    @Transactional
    void updatePasswordById(@Param("id") UUID id,@Param("newPassword") String newPassword);
}
