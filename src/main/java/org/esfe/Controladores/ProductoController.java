package org.esfe.Controladores;

import org.esfe.dtos.Producto.ProductoGuardar;
import org.esfe.dtos.Producto.ProductoModificar;
import org.esfe.dtos.Producto.ProductoSalida;
import org.esfe.dtos.proveedor.ProveedorSalida;
import org.esfe.services.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<Page<ProductoSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<ProductoSalida> productos = productoService.obtenerTodosPaginados(pageable);
        if(productos.hasContent()){
            return ResponseEntity.ok(productos);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ProductoSalida>> mostrarTodos(){
        List<ProductoSalida> productos = productoService.obtenerTodos();
        if(!productos.isEmpty()){
            return ResponseEntity.ok(productos);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoSalida> buscarPorId(@PathVariable Integer id) {
        // El método 'obtenerPorId' devuelve un Optional
        Optional<ProductoSalida> productoOptional = productoService.obtenerPorId(id);

        // Verifica si el Optional contiene un valor
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok(productoOptional.get());
        }

        // Si el Optional está vacío, devuelve una respuesta 404 Not Found
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ProductoSalida> crear(@RequestPart("producto") ProductoGuardar productoGuardar,
                                                @RequestParam("archivo") MultipartFile archivo,
                                                BindingResult result, Model model) throws IOException {
        ProductoSalida productos = productoService.crear(productoGuardar, archivo);

        if(productos != null){
            return ResponseEntity.ok(productos);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<ProductoSalida> editar(@PathVariable Integer id,
                                                 @RequestPart("producto") ProductoModificar productoModificar,
                                                 @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        productoModificar.setId(id);
        try {
            ProductoSalida producto = productoService.editar(productoModificar, imagen);
            if (producto != null) {
                return ResponseEntity.ok(producto);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        productoService.eliminarPorId(id);
        return ResponseEntity.ok("El producto fue eliminado correctamente");
    }

    @GetMapping("/peliculas/{id}/imagen")
    public ResponseEntity<byte[]> mostrarImagen(@PathVariable Integer id) {
        Optional<ProductoSalida> productoOpt = productoService.obtenerPorId(id);

        if (productoOpt.isPresent()) {
            ProductoSalida producto = productoOpt.get();
            byte[] imagen = producto.getImagen(); // Ya es un array de bytes

            if (imagen != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // Ajusta según el formato de la imagen

                return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
