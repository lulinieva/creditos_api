package ar.com.ada.api.creditos.models.request;

import java.math.BigDecimal;
import java.util.Date;

public class PrestamoRequest {

    public Integer clienteId;

    public BigDecimal importe;

    public Integer cuotas;

    public Date fechaPrestamo;

    
}