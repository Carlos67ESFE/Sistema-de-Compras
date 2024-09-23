package org.esfe.Controladores;

import org.esfe.dtos.inventario.InventarioSalida;
import org.esfe.dtos.movimiento.MovimientoGuardar;
import org.esfe.dtos.movimiento.MovimientoModificar;
import org.esfe.dtos.movimiento.MovimientoSalida;
import org.esfe.dtos.proveedor.ProveedorGuardar;
import org.esfe.dtos.proveedor.ProveedorModificar;
import org.esfe.dtos.proveedor.ProveedorSalida;
import org.esfe.services.interfaces.ImovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private ImovimientoService imovimientoService;

    @PreAuthorize("hasRole('Administrador')")
    @GetMapping
    public ResponseEntity<Page<MovimientoSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<MovimientoSalida> movimientos = imovimientoService.obtenerTodosPaginados(pageable);
        if(movimientos.hasContent()){
            return ResponseEntity.ok(movimientos);
        }

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @GetMapping("/lista")
    public ResponseEntity<List<MovimientoSalida>> mostrarTodos(){
        List<MovimientoSalida> movimientos = imovimientoService.obtenerTodos();
        if(!movimientos.isEmpty()){
            return ResponseEntity.ok(movimientos);
        }

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoSalida> buscarPorId(@PathVariable Integer id){
        MovimientoSalida movimientos = imovimientoService.obtenerPorId(id);

        if(movimientos != null){
            return ResponseEntity.ok(movimientos);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @PostMapping
    public ResponseEntity<MovimientoSalida> crear(@RequestBody MovimientoGuardar movimientoGuardar){
        MovimientoSalida movimientos = imovimientoService.crear(movimientoGuardar);

        if(movimientos != null){
            return ResponseEntity.ok(movimientos);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @PutMapping("/{id}")
    public ResponseEntity<MovimientoSalida> editar(@PathVariable Integer id,@RequestBody MovimientoModificar movimientoModificar ){
        MovimientoSalida movimientos = imovimientoService.editar(movimientoModificar);

        if(movimientos != null){
            return ResponseEntity.ok(movimientos);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        imovimientoService.eliminarPorId(id);
        return ResponseEntity.ok("El movimiento de inventario fue eliminado correctamente");
    }
}
