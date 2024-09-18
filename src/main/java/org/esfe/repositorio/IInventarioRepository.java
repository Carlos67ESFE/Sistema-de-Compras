package org.esfe.repositorio;

import org.esfe.modelos.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventarioRepository extends JpaRepository<Inventario, Integer> {
}
