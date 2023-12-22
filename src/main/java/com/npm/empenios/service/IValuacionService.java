package com.npm.empenios.service;

import com.npm.empenios.model.dto.PrestamoEstimadoDTO;

import java.math.BigDecimal;

/**s
 * Interface que define las operaciones del proceso de valuacion
 *
 * @author Gabriel Batres
 */
public interface IValuacionService {

    /**

    /**
     * Obtiene el prestamo estimado de acuerdo a un material y peso
     * @param idMaterial
     * @param gramosPeso
     * @return prestamoEstimadoDTO
     */
    public PrestamoEstimadoDTO prestamoEstimado(String idMaterial, BigDecimal gramosPeso);

}
