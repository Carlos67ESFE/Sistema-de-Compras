package org.esfe.services.implementaciones;

import org.esfe.dtos.Producto.ProductoGuardar;
import org.esfe.dtos.Producto.ProductoModificar;
import org.esfe.dtos.Producto.ProductoSalida;
import org.esfe.dtos.inventario.InventarioSalida;
import org.esfe.modelos.Inventario;
import org.esfe.modelos.Producto;
import org.esfe.repositorio.IProductoRepository;
import org.esfe.services.interfaces.IProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ProductoSalida> obtenerTodos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(producto -> modelMapper.map(producto, ProductoSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductoSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Producto> page = productoRepository.findAll(pageable);

        List<ProductoSalida> productoOto = page.stream()
                .map(producto -> modelMapper.map(producto, ProductoSalida.class))
                .collect(Collectors.toList());
        return  new PageImpl<>(productoOto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public ProductoSalida obtenerPorId(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent()) {
            return modelMapper.map(producto.get(), ProductoSalida.class);
        }
        return null;
    }

    @Override
    public ProductoSalida crear(ProductoGuardar productoGuardar) {
        Producto producto = modelMapper.map(productoGuardar, Producto.class);
        producto.setId(null);

        return modelMapper.map(productoRepository.save(producto), ProductoSalida.class);
    }

    @Override
    public ProductoSalida editar(ProductoModificar productoModificar) {
        Producto producto = productoRepository.save(modelMapper.map(productoModificar, Producto.class));
        return  modelMapper.map(producto, ProductoSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        productoRepository.deleteById(id);
    }

}
