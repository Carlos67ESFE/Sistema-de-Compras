package org.esfe.repositorio;

import org.esfe.modelos.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IInventarioRepository extends JpaRepository<Inventario, Integer> {

    List<Inventario> findByProductoId(Integer Id);
}
