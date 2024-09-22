package org.esfe.services.interfaces;

import org.esfe.dtos.Producto.ProductoGuardar;
import org.esfe.dtos.Producto.ProductoModificar;
import org.esfe.dtos.Producto.ProductoSalida;
import org.esfe.dtos.inventario.InventarioSalida;
import org.esfe.dtos.proveedor.ProveedorSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<ProductoSalida> obtenerTodos();

    Page<ProductoSalida> obtenerTodosPaginados(Pageable pageable);

    ProductoSalida obtenerPorId(Integer id);

    ProductoSalida crear(ProductoGuardar productoGuardar);

    ProductoSalida editar(ProductoModificar productoModificar);

    void eliminarPorId(Integer id);


}
