package se.ju23.typespeeder.repositories;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import se.ju23.typespeeder.entity.Result;

import java.util.List;

public interface ResultRepo extends JpaRepository<Result, Integer> {

}
