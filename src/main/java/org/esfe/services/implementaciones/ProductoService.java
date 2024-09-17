package org.esfe.services.implementaciones;

import jakarta.persistence.EntityNotFoundException;
import org.esfe.dtos.Producto.ProductoGuardar;
import org.esfe.dtos.Producto.ProductoModificar;
import org.esfe.dtos.Producto.ProductoSalida;
import org.esfe.modelos.Producto;
import org.esfe.repositorio.IProductoRepository;
import org.esfe.services.interfaces.IProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    public Optional<ProductoSalida> obtenerPorId(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent()) {
            ProductoSalida productoSalida = modelMapper.map(producto.get(), ProductoSalida.class);
            return Optional.of(productoSalida);
        }
        return Optional.empty();
    }

    @Override
    public ProductoSalida crear(ProductoGuardar productoGuardar, MultipartFile archivo) throws IOException {
        Producto producto = modelMapper.map(productoGuardar, Producto.class);

        if (archivo != null && !archivo.isEmpty()) {
            producto.setImagen(archivo.getBytes());
        }

        // Guarda el producto en la base de datos
        producto = productoRepository.save(producto);

        // Mapea el producto guardado a ProductoSalida y lo retorna
        return modelMapper.map(producto, ProductoSalida.class);
    }

    @Override
    public ProductoSalida editar(ProductoModificar productoModificar, MultipartFile imagen) {
        Producto producto = productoRepository.findById(productoModificar.getId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        modelMapper.map(productoModificar, producto);

        if (imagen != null && !imagen.isEmpty()) {
            try {
                producto.setImagen(imagen.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error al procesar la imagen", e);
            }
        }

        producto = productoRepository.save(producto);
        return modelMapper.map(producto, ProductoSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        productoRepository.deleteById(id);
    }

}
