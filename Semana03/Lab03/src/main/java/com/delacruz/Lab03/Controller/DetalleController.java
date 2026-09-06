package com.delacruz.Lab03.Controller;

import com.delacruz.Lab03.Model.Detalle;
import com.delacruz.Lab03.Service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class DetalleController {

    @Autowired
    private DetalleService detalleService;

    @GetMapping
    public List<Detalle> listar() {
        return detalleService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detalle> buscarPorId(@PathVariable Long id) {
        return detalleService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Detalle guardar(@RequestBody Detalle detalle) {
        return detalleService.guardar(detalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detalle> actualizar(@PathVariable Long id, @RequestBody Detalle detalle) {
        return detalleService.buscarPorId(id)
                .map(det -> {
                    detalle.setIdDetalle(id);
                    return ResponseEntity.ok(detalleService.guardar(detalle));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (detalleService.buscarPorId(id).isPresent()) {
            detalleService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}