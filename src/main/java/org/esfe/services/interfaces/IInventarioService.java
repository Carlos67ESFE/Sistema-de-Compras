package org.esfe.services.interfaces;

import org.esfe.dtos.inventario.InventarioGuardar;
import org.esfe.dtos.inventario.InventarioModificar;
import org.esfe.dtos.inventario.InventarioSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInventarioService {

    List<InventarioSalida> obtenerTodos();

    Page<InventarioSalida> obtenerTodosPaginados(Pageable pageable);

    InventarioSalida obtenerPorId(Integer id);

    InventarioSalida crear(InventarioGuardar inventarioGuardar);

    InventarioSalida editar(InventarioModificar inventarioModificar);

    void eliminarPorId(Integer id);
}
