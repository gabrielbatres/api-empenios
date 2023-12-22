package com.npm.empenios.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO con la información basica de un prestamo estimado
 * @author Gabriel Batres
 */
public class PrestamoEstimadoDTO implements Serializable {
    private static final long serialVersionUID = -4410972791408551980L;

    /**
     * Identificador del Material
     */
    private String codigoMaterial;

    /**
     * Material (Oro puro 24K)
     *
     */
    private String material;

    /**
     * Prestamo estimado
     */
    private BigDecimal prestamoEstimado;

    public PrestamoEstimadoDTO() {
    }

    public PrestamoEstimadoDTO(String codigoMaterial, String material, BigDecimal prestamoEstimado) {
        this.codigoMaterial = codigoMaterial;
        this.material = material;
        this.prestamoEstimado = prestamoEstimado;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getPrestamoEstimado() {
        return prestamoEstimado;
    }

    public void setPrestamoEstimado(BigDecimal prestamoEstimado) {
        this.prestamoEstimado = prestamoEstimado;
    }
}
