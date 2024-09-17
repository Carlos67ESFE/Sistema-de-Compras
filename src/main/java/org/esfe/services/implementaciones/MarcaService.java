package org.esfe.services.implementaciones;

import org.esfe.dtos.marca.MarcaGuardar;
import org.esfe.dtos.marca.MarcaModificar;
import org.esfe.dtos.marca.MarcaSalida;
import org.esfe.modelos.Marca;
import org.esfe.repositorio.IMarcasRepository;
import org.esfe.services.interfaces.IMarcasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaService implements IMarcasService {

    @Autowired
    private IMarcasRepository marcasRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MarcaSalida> obtenerTodos() {
        List<Marca> marcas = marcasRepository.findAll();
        return marcas.stream()
                .map(marca -> modelMapper.map(marca, MarcaSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<MarcaSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Marca> page = marcasRepository.findAll(pageable);

        List<MarcaSalida> marcaOto = page.stream()
                .map(marca -> modelMapper.map(marca, MarcaSalida.class))
                .collect(Collectors.toList());
        return  new PageImpl<>(marcaOto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public MarcaSalida obtenerPorId(Integer id) {
        return  modelMapper.map(marcasRepository.findById(id).get(), MarcaSalida.class);
    }

    @Override
    public MarcaSalida crear(MarcaGuardar marcaGuardar) {
        Marca marca = marcasRepository.save(modelMapper.map(marcaGuardar, Marca.class));
        return  modelMapper.map(marca, MarcaSalida.class);
    }

    @Override
    public MarcaSalida editar(MarcaModificar marcaModificar) {
        Marca marca = marcasRepository.save(modelMapper.map(marcaModificar, Marca.class));
        return  modelMapper.map(marca, MarcaSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {

        marcasRepository.deleteById(id);

    }
}
