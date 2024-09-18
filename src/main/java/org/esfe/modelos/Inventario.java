package org.esfe.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "Inventarios")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "producto_id")
    private  Producto producto;

    private  int stockActual;

    private  int stockMinimo;

    private  String almacenes;

    private  String ubicacionAlmacenes;

    private  String estado;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
