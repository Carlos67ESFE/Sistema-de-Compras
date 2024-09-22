package org.esfe.dtos.proveedor;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProveedorModificar implements Serializable {

    private Integer IdProveedor;

    private Integer marcaId;

    private String nombreProveedor;

    private String telefono;

    private String email;

    private String direccion;

    private String redes;
}
