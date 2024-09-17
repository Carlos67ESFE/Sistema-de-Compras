package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "marcas")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El nombre es requerido")
    private String nombreEmpresa;

    @OneToMany (mappedBy = "marca")
    private List<Proveedor> proveedores;

    @OneToMany (mappedBy = "marca")
    private List<Producto> productos;

}
