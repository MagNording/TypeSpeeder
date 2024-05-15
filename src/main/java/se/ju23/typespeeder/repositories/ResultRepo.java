package se.ju23.typespeeder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ju23.typespeeder.entity.Result;

public interface ResultRepo extends JpaRepository<Result, Integer> {

}
