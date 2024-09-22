package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdProveedor;

    @ManyToOne
    @JoinColumn (name = "marca_id")
    private  Marca marca;

    @NotBlank(message = "El nombre del proveedor es requerido")
    private String nombreProveedor;

    private String telefono;

    private String email;

    private String direccion;

    private String redes;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @OneToMany (mappedBy = "proveedor")
    private List<Movimiento> movimientos;

    // Método que se ejecuta antes de persistir la entidad
    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now(); // Asigna la fecha actual por defecto
    }

    // Método que se ejecuta antes de actualizar la entidad
    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now(); // Actualiza la fecha de modificación
    }

    public Integer getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        IdProveedor = idProveedor;
    }
}
