package com.npm.empenios.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO con la informacion base para el calculo del prestamo estimado
 */
public class PrestamoEstimadoRequestDTO implements Serializable {

    private static final long serialVersionUID = 6669913383590962176L;

    @Valid
    @NotNull(message = "El código del material es requerido")
    private String codigoMaterial;

    @Valid
    @NotNull(message = "El peso del artículo es requerido")
    @DecimalMin("0.001")
    private BigDecimal pesoArticulo;

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public BigDecimal getPesoArticulo() {
        return pesoArticulo;
    }

    public void setPesoArticulo(BigDecimal pesoArticulo) {
        this.pesoArticulo = pesoArticulo;
    }
}
