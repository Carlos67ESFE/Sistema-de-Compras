package org.esfe.repositorio;

import org.esfe.dtos.Producto.ProductoSalida;
import org.esfe.modelos.Inventario;
import org.esfe.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}
