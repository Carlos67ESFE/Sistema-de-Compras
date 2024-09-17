package org.esfe.Controladores;

import org.esfe.dtos.categoria.CategoriaGuardar;
import org.esfe.dtos.categoria.CategoriaModificar;
import org.esfe.dtos.categoria.CategoriaSalida;
import org.esfe.services.interfaces.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private ICategoriasService categoriasService;

    @GetMapping
    public ResponseEntity<Page<CategoriaSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<CategoriaSalida> categorias = categoriasService.obtenerTodosPaginados(pageable);
        if(categorias.hasContent()){
            return ResponseEntity.ok(categorias);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<CategoriaSalida>> mostrarTodos(){
        List<CategoriaSalida> categorias = categoriasService.obtenerTodos();
        if(!categorias.isEmpty()){
            return ResponseEntity.ok(categorias);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaSalida> buscarPorId(@PathVariable Integer id){
        CategoriaSalida categoria = categoriasService.obtenerPorId(id);

        if(categoria != null){
            return ResponseEntity.ok(categoria);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoriaSalida> crear(@RequestBody CategoriaGuardar categoriaGuardar){
        CategoriaSalida categoria = categoriasService.crear(categoriaGuardar);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaSalida> editar(@PathVariable Integer id,@RequestBody CategoriaModificar categoriaModificar ){
        CategoriaSalida categoria = categoriasService.editar(categoriaModificar);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        categoriasService.eliminarPorId(id);
        return ResponseEntity.ok("Categoria eliminada correctamente");
    }


}
