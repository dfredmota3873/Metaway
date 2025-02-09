package com.metaway.repository;

import com.metaway.model.Raca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RacaRepository extends JpaRepository<Raca, UUID> {
}
