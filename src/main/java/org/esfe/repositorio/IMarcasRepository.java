package org.esfe.repositorio;

import org.esfe.modelos.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMarcasRepository extends JpaRepository<Marca, Integer> {
}
