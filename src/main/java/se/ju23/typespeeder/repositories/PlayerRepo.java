package se.ju23.typespeeder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.entity.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
    boolean existsByUsernameAndPassword(String username, String password);
    Player findByUsernameAndPassword(String username, String password);
    boolean existsUserById(int id);
}
