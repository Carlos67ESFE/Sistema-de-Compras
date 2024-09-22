package org.esfe.dtos.inventario;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class InventarioModificar implements Serializable {

    private Integer idInventario;

    private Integer productoId;

    private  int stockActual;

    private  int stockMinimo;

    private  String almacenes;

    private  String ubicacionAlmacenes;

    private  String estado;
}
