package org.esfe.dtos.proveedor;

import lombok.Getter;
import lombok.Setter;
import org.esfe.dtos.marca.MarcaSalida;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProveedorSalida implements Serializable {

    private Integer id;

    private MarcaSalida marca;

    private String nombreProveedor;

    private String telefono;

    private String email;

    private String direccion;

    private String redes;

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaActualizacion;

}
