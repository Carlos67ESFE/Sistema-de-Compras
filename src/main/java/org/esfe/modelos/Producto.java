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
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "La descripción es requerido")
    private String descripcion;

    private Double precio;

    private int Stock;

    @ManyToOne
    @JoinColumn (name = "marca_id")
    private  Marca marca;

    @ManyToOne
    @JoinColumn (name = "categoria_id")
    private  Categoria categoria;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] imagen;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @OneToMany (mappedBy = "producto")
    private List<Movimiento> movimientos;

    @OneToMany (mappedBy = "producto")
    private List<Inventario> inventarios;

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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
