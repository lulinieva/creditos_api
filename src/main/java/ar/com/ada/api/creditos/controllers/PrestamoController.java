package ar.com.ada.api.creditos.controllers;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.creditos.entities.Prestamo;
import ar.com.ada.api.creditos.models.request.EstadoPrestamoRequest;
import ar.com.ada.api.creditos.models.request.PrestamoRequest;
import ar.com.ada.api.creditos.models.response.GenericResponse;
import ar.com.ada.api.creditos.services.PrestamoService;

public class PrestamoController {
    

    @Autowired
    PrestamoService service;

    @PostMapping ("/prestamos")
    public ResponseEntity<GenericResponse> emitirPrestamo(@RequestBody PrestamoRequest request){
        GenericResponse r = new GenericResponse();

        Prestamo prestamo = service.emitirPrestamo(request.clienteId, request.importe, request.cuotas, request.fechaPrestamo);

        r.id = prestamo.getPrestamoId();
        r.isOk = true;
        r.message = "El prestamo ha sido ingresado en el sistema "+ "Estado del prestamo:" + prestamo.getEstadoId();

        return ResponseEntity.ok(r);


    }

    @GetMapping("/prestamos")
    public ResponseEntity<List<Prestamo>> traerPrestamos() {
        List<Prestamo> lista = service.traerPrestamos();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/prestamos/{id}")
    public ResponseEntity<Prestamo> getPrestamoPorId(@PathVariable Integer id){
        Prestamo prestamo = service.buscarPrestamoPorId(id);

        return ResponseEntity.ok(prestamo);
    }

    @PutMapping("/prestamos/{id}")
    public ResponseEntity<GenericResponse> modificarEstadoPrestamo(@PathVariable Integer id,
            @RequestBody EstadoPrestamoRequest estadoPrestamo) {

        GenericResponse respuesta = new GenericResponse();

        Prestamo prestamo = service.buscarPrestamoPorId(id);
        prestamo.setEstadoId(estadoPrestamo.estadoNuevo);
        service.actualizar(prestamo);

        respuesta.id = prestamo.getPrestamoId();
        respuesta.isOk = true;
        respuesta.message = "Estado de Préstamo actualizado";

        return ResponseEntity.ok(respuesta);
    }
}

