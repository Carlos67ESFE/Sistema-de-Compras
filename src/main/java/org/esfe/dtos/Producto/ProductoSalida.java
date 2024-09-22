package org.esfe.dtos.Producto;

import lombok.Getter;
import lombok.Setter;
import org.esfe.dtos.categoria.CategoriaSalida;
import org.esfe.dtos.marca.MarcaSalida;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductoSalida implements Serializable {

    private Integer idProducto;

    private String nombre;

    private String descripcion;

    private Double precio;

    private int Stock;

    private MarcaSalida marca;

    private CategoriaSalida categoria;

    private String imagen;

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaActualizacion;

}
