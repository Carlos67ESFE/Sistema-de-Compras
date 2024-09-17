package org.esfe.services.implementaciones;

import org.esfe.dtos.categoria.CategoriaGuardar;
import org.esfe.dtos.categoria.CategoriaModificar;
import org.esfe.dtos.categoria.CategoriaSalida;
import org.esfe.modelos.Categoria;
import org.esfe.repositorio.ICategoriasRepository;
import org.esfe.services.interfaces.ICategoriasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService implements ICategoriasService {

    @Autowired
    private ICategoriasRepository categoriasReposiory;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoriaSalida> obtenerTodos() {
        List<Categoria> categorias = categoriasReposiory.findAll();
        return categorias.stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<CategoriaSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Categoria> page = categoriasReposiory.findAll(pageable);

        List<CategoriaSalida> categoriOto = page.stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaSalida.class))
                .collect(Collectors.toList());
        return  new PageImpl<>(categoriOto, page.getPageable(), page.getTotalElements());

    }

    @Override
    public CategoriaSalida obtenerPorId(Integer id) {
        return  modelMapper.map(categoriasReposiory.findById(id).get(), CategoriaSalida.class);
    }

    @Override
    public CategoriaSalida crear(CategoriaGuardar categoriaGuardar) {
        Categoria categoria = categoriasReposiory.save(modelMapper.map(categoriaGuardar, Categoria.class));
        return  modelMapper.map(categoria, CategoriaSalida.class);
    }

    @Override
    public CategoriaSalida editar(CategoriaModificar categoriaModificar) {
        Categoria categoria = categoriasReposiory.save(modelMapper.map(categoriaModificar, Categoria.class));
        return  modelMapper.map(categoria, CategoriaSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        categoriasReposiory.deleteById(id);
    }
}
