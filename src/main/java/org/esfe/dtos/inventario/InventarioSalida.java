package org.esfe.dtos.inventario;

import lombok.Getter;
import lombok.Setter;
import org.esfe.dtos.Producto.ProductoSalida;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class InventarioSalida implements Serializable {

    private Integer idInventario;

    private ProductoSalida producto;

    private  int stockActual;

    private  int stockMinimo;

    private  String almacenes;

    private  String ubicacionAlmacenes;

    private  String estado;

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaActualizacion;

}
