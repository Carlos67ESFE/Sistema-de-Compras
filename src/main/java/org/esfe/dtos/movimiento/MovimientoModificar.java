package org.esfe.dtos.movimiento;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class MovimientoModificar implements Serializable {

    private Integer Id;

    private String tipo;

    private int cantidad;

    private Integer productoId;

    private Integer proveedor_Id;

    private LocalDate fechaMovimiento;

    private  String comentario;

    private  String personalResponsable;
}
