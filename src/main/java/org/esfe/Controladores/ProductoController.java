package org.esfe.Controladores;

import org.esfe.dtos.Producto.ProductoGuardar;
import org.esfe.dtos.Producto.ProductoModificar;
import org.esfe.dtos.Producto.ProductoSalida;
import org.esfe.services.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @PreAuthorize("hasAnyRole('Administrador', 'Empleado', 'Cliente')")
    @GetMapping
    public ResponseEntity<Page<ProductoSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<ProductoSalida> productos = productoService.obtenerTodosPaginados(pageable);
        if(productos.hasContent()){
            return ResponseEntity.ok(productos);
        }

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('Administrador', 'Empleado', 'Cliente')")
    @GetMapping("/lista")
    public ResponseEntity<List<ProductoSalida>> mostrarTodos(){
        List<ProductoSalida> productos = productoService.obtenerTodos();
        if(!productos.isEmpty()){
            return ResponseEntity.ok(productos);
        }

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('Administrador', 'Empleado', 'Cliente')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoSalida> buscarPorId(@PathVariable Integer id) {
        ProductoSalida productos = productoService.obtenerPorId(id);

        if(productos != null){
            return ResponseEntity.ok(productos);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @PostMapping
    public ResponseEntity<ProductoSalida> crear(@RequestBody ProductoGuardar productoGuardar){
        ProductoSalida productos = productoService.crear(productoGuardar);

        if(productos != null){
            return ResponseEntity.ok(productos);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PreAuthorize("hasRole('Administrador')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoSalida> editar(@PathVariable Integer id,@RequestBody ProductoModificar productoModificar ){
        ProductoSalida productos = productoService.editar(productoModificar);

        if(productos != null){
            return ResponseEntity.ok(productos);
        }

        return ResponseEntity.internalServerError().build();
    }


    @PreAuthorize("hasRole('Administrador')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        productoService.eliminarPorId(id);
        return ResponseEntity.ok("El producto fue eliminado correctamente");
    }

}
