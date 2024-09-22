package org.esfe.dtos.Producto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
public class ProductoModificar implements Serializable {

    private Integer idproducto;

    private String nombre;

    private String descripcion;

    private Double precio;

    private int Stock;

    private Integer marca_Id;

    private Integer categoriaId;

    private String imagen;

}
