package org.esfe.services.interfaces;

import org.esfe.dtos.categoria.CategoriaGuardar;
import org.esfe.dtos.categoria.CategoriaModificar;
import org.esfe.dtos.categoria.CategoriaSalida;
import org.esfe.dtos.movimiento.MovimientoGuardar;
import org.esfe.dtos.movimiento.MovimientoModificar;
import org.esfe.dtos.movimiento.MovimientoSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ImovimientoService {

    List<MovimientoSalida> obtenerTodos();

    Page<MovimientoSalida> obtenerTodosPaginados(Pageable pageable);

    MovimientoSalida obtenerPorId(Integer id);

    MovimientoSalida crear(MovimientoGuardar movimientoGuardar);

    MovimientoSalida editar(MovimientoModificar movimientoModificar);

    void eliminarPorId(Integer id);
}
