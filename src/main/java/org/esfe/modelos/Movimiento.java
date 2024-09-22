package org.esfe.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String tipo;

    private int cantidad;

    @ManyToOne
    @JoinColumn (name = "producto_id")
    private  Producto producto;

    @ManyToOne
    @JoinColumn (name = "proveedor_id")
    private  Proveedor proveedor;

    private LocalDate fechaMovimiento;

    private  String comentario;

    private  String personalResponsable;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
