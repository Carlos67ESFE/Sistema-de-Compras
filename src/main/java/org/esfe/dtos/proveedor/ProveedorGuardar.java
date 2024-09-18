package org.esfe.dtos.proveedor;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProveedorGuardar implements Serializable {

    private Integer marcaId;

    private String nombreProveedor;

    private String telefono;

    private String email;

    private String direccion;

    private String redes;

}
