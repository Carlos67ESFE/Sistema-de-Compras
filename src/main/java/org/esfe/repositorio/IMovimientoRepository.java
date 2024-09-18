package org.esfe.repositorio;
import org.esfe.modelos.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovimientoRepository extends JpaRepository<Movimiento, Integer> {
}
