package org.esfe.services.implementaciones;

import org.esfe.dtos.proveedor.ProveedorGuardar;
import org.esfe.dtos.proveedor.ProveedorModificar;
import org.esfe.dtos.proveedor.ProveedorSalida;
import org.esfe.modelos.Proveedor;
import org.esfe.repositorio.IProveedorRepository;
import org.esfe.services.interfaces.IProveedorService;
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
public class ProveedorService implements IProveedorService {

    @Autowired
    private IProveedorRepository proveedorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProveedorSalida> obtenerTodos() {
        List<Proveedor> proveedors = proveedorRepository.findAll();
        return proveedors.stream()
                .map(proveedor -> modelMapper.map(proveedor, ProveedorSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProveedorSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Proveedor> page = proveedorRepository.findAll(pageable);

        List<ProveedorSalida> proveedorOto = page.stream()
                .map(proveedor -> modelMapper.map(proveedor, ProveedorSalida.class))
                .collect(Collectors.toList());
        return  new PageImpl<>(proveedorOto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public ProveedorSalida obtenerPorId(Integer id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);

        if (proveedor.isPresent()) {
            return modelMapper.map(proveedor.get(), ProveedorSalida.class);
        }
        return null;
    }

    @Override
    public ProveedorSalida crear(ProveedorGuardar proveedorGuardar) {
        Proveedor proveedor = modelMapper.map(proveedorGuardar, Proveedor.class);
        proveedor.setIdProveedor(null);

        return modelMapper.map(proveedorRepository.save(proveedor), ProveedorSalida.class);
    }

    @Override
    public ProveedorSalida editar(ProveedorModificar proveedorModificar) {
        Proveedor proveedor = proveedorRepository.save(modelMapper.map(proveedorModificar, Proveedor.class));
        return  modelMapper.map(proveedor, ProveedorSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        proveedorRepository.deleteById(id);
    }
}
