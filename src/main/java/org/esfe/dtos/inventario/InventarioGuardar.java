package org.esfe.dtos.inventario;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class InventarioGuardar implements Serializable {

    private Integer productoId;

    private  int stockActual;

    private  int stockMinimo;

    private  String almacenes;

    private  String ubicacionAlmacenes;

}
