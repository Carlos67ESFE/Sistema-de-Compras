package org.esfe.services.implementaciones;

import org.esfe.dtos.movimiento.MovimientoGuardar;
import org.esfe.dtos.movimiento.MovimientoModificar;
import org.esfe.dtos.movimiento.MovimientoSalida;
import org.esfe.modelos.Movimiento;
import org.esfe.repositorio.IMovimientoRepository;
import org.esfe.services.interfaces.ImovimientoService;
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
public class MovimientoService implements ImovimientoService {

    @Autowired
    private IMovimientoRepository iMovimientoRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<MovimientoSalida> obtenerTodos() {
        List<Movimiento> movimientos = iMovimientoRepository.findAll();
        return movimientos.stream()
                .map(movimiento -> modelMapper.map(movimientos, MovimientoSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<MovimientoSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Movimiento> page = iMovimientoRepository.findAll(pageable);

        List<MovimientoSalida> movimientoOto = page.stream()
                .map(movimiento -> modelMapper.map(movimiento, MovimientoSalida.class))
                .collect(Collectors.toList());
        return  new PageImpl<>(movimientoOto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public MovimientoSalida obtenerPorId(Integer id) {
        Optional<Movimiento> movimiento = iMovimientoRepository.findById(id);

        if (movimiento.isPresent()) {
            return modelMapper.map(movimiento.get(), MovimientoSalida.class);
        }
        return null;
    }

    @Override
    public MovimientoSalida crear(MovimientoGuardar movimientoGuardar) {
        Movimiento movimiento = modelMapper.map(movimientoGuardar, Movimiento.class);
        movimiento.setId(null);

        return modelMapper.map(iMovimientoRepository.save(movimiento), MovimientoSalida.class);
    }

    @Override
    public MovimientoSalida editar(MovimientoModificar movimientoModificar) {
        Movimiento movimiento = iMovimientoRepository.save(modelMapper.map(movimientoModificar, Movimiento.class));
        return  modelMapper.map(movimiento, MovimientoSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {

        iMovimientoRepository.deleteById(id);
    }
}
