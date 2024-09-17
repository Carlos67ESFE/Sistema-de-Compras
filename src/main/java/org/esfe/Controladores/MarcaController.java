package org.esfe.Controladores;

import org.esfe.dtos.categoria.CategoriaGuardar;
import org.esfe.dtos.categoria.CategoriaModificar;
import org.esfe.dtos.categoria.CategoriaSalida;
import org.esfe.dtos.marca.MarcaGuardar;
import org.esfe.dtos.marca.MarcaModificar;
import org.esfe.dtos.marca.MarcaSalida;
import org.esfe.services.interfaces.IMarcasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private IMarcasService marcasService;

    @GetMapping
    public ResponseEntity<Page<MarcaSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<MarcaSalida> marcas = marcasService.obtenerTodosPaginados(pageable);
        if(marcas.hasContent()){
            return ResponseEntity.ok(marcas);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<MarcaSalida>> mostrarTodos(){
        List<MarcaSalida> marcas = marcasService.obtenerTodos();
        if(!marcas.isEmpty()){
            return ResponseEntity.ok(marcas);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaSalida> buscarPorId(@PathVariable Integer id){
        MarcaSalida marcas = marcasService.obtenerPorId(id);

        if(marcas != null){
            return ResponseEntity.ok(marcas);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MarcaSalida> crear(@RequestBody MarcaGuardar marcaGuardar){
        MarcaSalida marcas = marcasService.crear(marcaGuardar);
        return ResponseEntity.ok(marcas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaSalida> editar(@PathVariable Integer id,@RequestBody MarcaModificar marcaModificar ){
        MarcaSalida marcas = marcasService.editar(marcaModificar);
        return ResponseEntity.ok(marcas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        marcasService.eliminarPorId(id);
        return ResponseEntity.ok("Marca eliminada correctamente");
    }
}
