package com.metaway.repository;

import com.metaway.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, UUID> {
}
