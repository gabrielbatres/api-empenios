package com.npm.empenios.service;

import com.npm.empenios.model.PrecioMaterial;
import com.npm.empenios.model.dto.PrestamoEstimadoDTO;
import com.npm.empenios.repository.PrecioMaterialRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Valida el correcto funcionamiento el servicio {@link IValuacionService}
 */
@SpringBootTest
public class ValuacionServiceTest {

    @MockBean
    PrecioMaterialRepository precioMaterialRepository;

    @Autowired
    IValuacionService valuacionService;

    @BeforeEach
    void init(){
        reset(this.precioMaterialRepository);
    }

    /**
     * Valida el correcto funcionamiento del calculo de un prestamo estimado
     */
    @Test
    void whenCalculaPrestamoEstimadoThenOK() {

        final PrecioMaterial precioMaterial = buildPrecioMaterial("002");

        when(this.precioMaterialRepository.findByCodigo(anyString())).thenReturn(precioMaterial);

        PrestamoEstimadoDTO prestamoEstimadoDTO = valuacionService.prestamoEstimado(precioMaterial.getCodigo(), BigDecimal.ONE);

        Assertions.assertNotNull(prestamoEstimadoDTO);
        Assertions.assertEquals(precioMaterial.getCodigo(), prestamoEstimadoDTO.getCodigoMaterial());
        Assertions.assertNotNull(prestamoEstimadoDTO.getMaterial());
        Assertions.assertNotNull(prestamoEstimadoDTO.getPrestamoEstimado());
        Assertions.assertTrue(prestamoEstimadoDTO.getPrestamoEstimado().compareTo(BigDecimal.ZERO) > 0);
    }

    /**
     * Valida cuando no se encuentra el precio por gramo de un material
     */
    @Test
    void whenCalculaPrestamoEstimadoThenNotFound() {
        when(this.precioMaterialRepository.findByCodigo(anyString())).thenReturn(null);

        PrestamoEstimadoDTO prestamoEstimadoDTO = valuacionService.prestamoEstimado("002", BigDecimal.ONE);
        Assertions.assertNull(prestamoEstimadoDTO);
    }

    /**
     * Genera un precio por gramo de un material
     * @param codigo
     * @return
     */
    private PrecioMaterial buildPrecioMaterial(String codigo){
        PrecioMaterial precioMaterial = new PrecioMaterial();
        precioMaterial.setCodigo(codigo);
        precioMaterial.setMaterial("Oro alto 18k");
        precioMaterial.setPrecioGramo(BigDecimal.valueOf(1000));
        return precioMaterial;
    }

}
