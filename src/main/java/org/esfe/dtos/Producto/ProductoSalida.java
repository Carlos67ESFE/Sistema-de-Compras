package org.esfe.dtos.Producto;

import lombok.Getter;
import lombok.Setter;
import org.esfe.dtos.categoria.CategoriaSalida;
import org.esfe.dtos.marca.MarcaSalida;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductoSalida implements Serializable {

    private Integer id;

    private String nombre;

    private String descripcion;

    private Double precio;

    private int Stock;

    private MarcaSalida marca;

    private CategoriaSalida categoria;

    private byte[] imagen; // Cambiado de 'byte' a 'byte[]'

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaActualizacion;

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
