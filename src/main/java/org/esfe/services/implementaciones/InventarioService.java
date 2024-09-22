package org.esfe.services.implementaciones;

import org.esfe.dtos.inventario.InventarioCambiarEstado;
import org.esfe.dtos.inventario.InventarioGuardar;
import org.esfe.dtos.inventario.InventarioModificar;
import org.esfe.dtos.inventario.InventarioSalida;
import org.esfe.modelos.Inventario;
import org.esfe.repositorio.IInventarioRepository;
import org.esfe.services.interfaces.IInventarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioService implements IInventarioService {

    @Autowired
    private IInventarioRepository iInventarioRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<InventarioSalida> obtenerTodos() {
        List<Inventario> inventarios = iInventarioRepository.findAll();
        return inventarios.stream()
                .map(inventario -> modelMapper.map(inventario, InventarioSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<InventarioSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Inventario> page = iInventarioRepository.findAll(pageable);

        List<InventarioSalida> inventarioOto = page.stream()
                .map(inventario -> modelMapper.map(inventario, InventarioSalida.class))
                .collect(Collectors.toList());
        return  new PageImpl<>(inventarioOto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public InventarioSalida obtenerPorId(Integer id) {
        Optional<Inventario> inventario = iInventarioRepository.findById(id);

        if (inventario.isPresent()) {
            return modelMapper.map(inventario.get(), InventarioSalida.class);
        }
        return null;
    }

    @Override
    public List<InventarioSalida> obtenerPorProductoId(Integer idProducto) {
        List<Inventario> inventarios = iInventarioRepository.findByProductoId(idProducto);

        return inventarios.stream()
                .map(inventario -> modelMapper.map(inventario, InventarioSalida.class))
                .collect(Collectors.toList());
    }


    @Override
    public InventarioSalida crear(InventarioGuardar inventarioGuardar) {
        Inventario inventario = modelMapper.map(inventarioGuardar, Inventario.class);
        inventario.setIdInventario(null);
        inventario.setEstado(Inventario.Status.APROBADA);

        return modelMapper.map(iInventarioRepository.save(inventario), InventarioSalida.class);
    }

    @Override
    public InventarioSalida editar(InventarioModificar inventarioModificar) {
        Inventario inventario = iInventarioRepository.save(modelMapper.map(inventarioModificar, Inventario.class));
        return  modelMapper.map(inventario, InventarioSalida.class);
    }

    @Override
    public InventarioSalida cambiarEstado(InventarioCambiarEstado inventarioCambiarEstado) {
        Inventario inventario = iInventarioRepository.findById(inventarioCambiarEstado.getIdInventario()).get();
        inventario.setEstado(Inventario.Status.valueOf(inventarioCambiarEstado.getEstado()));

        return modelMapper.map(iInventarioRepository.save(inventario), InventarioSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {

        iInventarioRepository.deleteById(id);
    }
}
