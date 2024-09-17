package org.esfe.repositorio;

import org.esfe.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriasRepository extends JpaRepository<Categoria, Integer> {
}
