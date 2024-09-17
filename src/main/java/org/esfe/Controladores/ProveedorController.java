package org.esfe.Controladores;

import org.esfe.dtos.proveedor.ProveedorGuardar;
import org.esfe.dtos.proveedor.ProveedorModificar;
import org.esfe.dtos.proveedor.ProveedorSalida;
import org.esfe.services.interfaces.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<Page<ProveedorSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<ProveedorSalida> proveedores = proveedorService.obtenerTodosPaginados(pageable);
        if(proveedores.hasContent()){
            return ResponseEntity.ok(proveedores);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ProveedorSalida>> mostrarTodos(){
        List<ProveedorSalida> proveedores = proveedorService.obtenerTodos();
        if(!proveedores.isEmpty()){
            return ResponseEntity.ok(proveedores);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorSalida> buscarPorId(@PathVariable Integer id){
        ProveedorSalida proveedores = proveedorService.obtenerPorId(id);

        if(proveedores != null){
            return ResponseEntity.ok(proveedores);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProveedorSalida> crear(@RequestBody ProveedorGuardar proveedorGuardar){
        ProveedorSalida proveedores = proveedorService.crear(proveedorGuardar);

        if(proveedores != null){
            return ResponseEntity.ok(proveedores);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorSalida> editar(@PathVariable Integer id,@RequestBody ProveedorModificar proveedorModificar ){
        ProveedorSalida proveedores = proveedorService.editar(proveedorModificar);

        if(proveedores != null){
            return ResponseEntity.ok(proveedores);
        }

        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        proveedorService.eliminarPorId(id);
        return ResponseEntity.ok("El proveedor fue eliminado correctamente");
    }
}
