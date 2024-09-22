package org.esfe.dtos.movimiento;

import lombok.Getter;
import lombok.Setter;
import org.esfe.dtos.Producto.ProductoSalida;
import org.esfe.dtos.proveedor.ProveedorSalida;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class MovimientoSalida implements Serializable {

    private Integer Id;

    private String tipo;

    private int cantidad;

    private ProductoSalida producto;

    private ProveedorSalida proveedor;

    private LocalDate fechaMovimiento;

    private  String comentario;

    private  String personalResponsable;
}
