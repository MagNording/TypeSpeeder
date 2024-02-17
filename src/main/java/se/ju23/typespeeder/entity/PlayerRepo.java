package se.ju23.typespeeder.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
    boolean existsByUsernameAndPassword(String username, String password);
    Player findByUsernameAndPassword(String username, String password);
}
