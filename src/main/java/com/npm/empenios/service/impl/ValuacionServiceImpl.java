package com.npm.empenios.service.impl;

import com.npm.empenios.model.PrecioMaterial;
import com.npm.empenios.model.dto.PrestamoEstimadoDTO;
import com.npm.empenios.repository.PrecioMaterialRepository;
import com.npm.empenios.service.IValuacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Servicio que realiza las operaciones del proceso de valuacion
 *
 * @author Gabriel Batres
 */
@Service
public class ValuacionServiceImpl implements IValuacionService {

    /**
     * Referencia al reporsitorio {@link PrecioMaterialRepository}
     */
    private final PrecioMaterialRepository precioMaterialRepository;

    /***
     * Porcentaje de valuacion
     */
    @Value("${valuacion.porcentaje}")
    private BigDecimal porcentajeValuacion;

    @Autowired
    public ValuacionServiceImpl(PrecioMaterialRepository precioMaterialRepository) {
        this.precioMaterialRepository = precioMaterialRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PrestamoEstimadoDTO prestamoEstimado(String idMaterial, BigDecimal gramosPeso) {
        PrecioMaterial precioMaterial = precioMaterialRepository.findByCodigo(idMaterial);
        if(precioMaterial == null) {
            return null;
        }
        //calculamos el prestamo estimado con la formula Monto_Préstamo = (gramos * PRECIO_GRAMO) * porcentajeValuacion (por defecto 80%)
        BigDecimal prestamo = precioMaterial.getPrecioGramo().multiply(gramosPeso).multiply(getPorcentajeValuacion()).setScale(2, RoundingMode.HALF_UP);
        return new PrestamoEstimadoDTO(precioMaterial.getCodigo(), precioMaterial.getMaterial() ,prestamo);
    }

    /**
     * Obtiene el porcentaje ded valuacion
     * @return porcentajeValuacion
     */
    private BigDecimal getPorcentajeValuacion(){
        return porcentajeValuacion.divide(BigDecimal.valueOf(100),2,RoundingMode.HALF_UP);
    }
}
