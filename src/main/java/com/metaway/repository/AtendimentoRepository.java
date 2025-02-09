package com.metaway.repository;

import com.metaway.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, UUID> {
}
