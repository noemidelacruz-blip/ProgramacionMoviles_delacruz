package com.delacruz.Lab03.Service;

import com.delacruz.Lab03.Model.Detalle;
import com.delacruz.Lab03.Repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleService {

    @Autowired
    private DetalleRepository detalleRepository;

    public List<Detalle> listarTodos() {
        return detalleRepository.findAll();
    }

    public Optional<Detalle> buscarPorId(Long id) {
        return detalleRepository.findById(id);
    }

    public Detalle guardar(Detalle detalle) {
        return detalleRepository.save(detalle);
    }

    public void eliminar(Long id) {
        detalleRepository.deleteById(id);
    }
}