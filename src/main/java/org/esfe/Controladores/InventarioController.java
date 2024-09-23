package org.esfe.Controladores;

import org.esfe.dtos.inventario.InventarioCambiarEstado;
import org.esfe.dtos.inventario.InventarioGuardar;
import org.esfe.dtos.inventario.InventarioModificar;
import org.esfe.dtos.inventario.InventarioSalida;
import org.esfe.services.interfaces.IInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private IInventarioService iInventarioService;

    @PreAuthorize("hasRole('Administrador')")
    @GetMapping
    public ResponseEntity<Page<InventarioSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<InventarioSalida> inventarios = iInventarioService.obtenerTodosPaginados(pageable);
        if(inventarios.hasContent()){
            return ResponseEntity.ok(inventarios);
        }

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @GetMapping("/lista")
    public ResponseEntity<List<InventarioSalida>> mostrarTodos(){
        List<InventarioSalida> inventarios = iInventarioService.obtenerTodos();
        if(!inventarios.isEmpty()){
            return ResponseEntity.ok(inventarios);
        }

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @GetMapping("/{id}")
    public ResponseEntity<InventarioSalida> buscarPorId(@PathVariable Integer id){
        InventarioSalida inventarios = iInventarioService.obtenerPorId(id);

        if(inventarios != null){
            return ResponseEntity.ok(inventarios);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @GetMapping("/producto/{id}")
    public ResponseEntity<List<InventarioSalida>> mostrarPorProducto(@PathVariable Integer id){
        List<InventarioSalida> inventarios = iInventarioService.obtenerPorProductoId(id);

        if(!inventarios.isEmpty()){
            return ResponseEntity.ok(inventarios);
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @PostMapping
    public ResponseEntity<InventarioSalida> crear(@RequestBody InventarioGuardar inventarioGuardar){
        InventarioSalida inventarios = iInventarioService.crear(inventarioGuardar);

        if(inventarios != null){
            return ResponseEntity.ok(inventarios);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @PutMapping("/{id}")
    public ResponseEntity<InventarioSalida> editar(@PathVariable Integer id, @RequestBody InventarioModificar inventarioModificar ){
        InventarioSalida inventarios = iInventarioService.editar(inventarioModificar);

        if(inventarios != null){
            return ResponseEntity.ok(inventarios);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @PatchMapping
    public ResponseEntity<InventarioSalida> cambiarEstado(@RequestBody InventarioCambiarEstado inventarioCambiarEstado){
        InventarioSalida inventarios = iInventarioService.cambiarEstado(inventarioCambiarEstado);

        if(inventarios != null){
            return ResponseEntity.ok(inventarios);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        iInventarioService.eliminarPorId(id);
        return ResponseEntity.ok("El inventario fue eliminado correctamente");
    }
}
