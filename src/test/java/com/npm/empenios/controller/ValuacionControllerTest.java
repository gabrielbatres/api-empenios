package com.npm.empenios.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.npm.empenios.model.dto.PrestamoEstimadoDTO;
import com.npm.empenios.model.dto.PrestamoEstimadoRequestDTO;
import com.npm.empenios.service.IValuacionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Prueba los endpoints definidos en {@link ValuacionController}
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ValuacionControllerTest {

    private final static String ENDPOINT_BASE = "/valuacion";


    @Autowired
    MockMvc mockMvc;

    @MockBean
    IValuacionService valuacionService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void init(){
        reset(this.valuacionService);
    }

    /**
     * Valida el correcto funcionamiento del endpoint (/prestamoEstimado)
     */
    @Test
    void whenCalculaPrestamoEstimadoReturnOK() throws Exception {
        PrestamoEstimadoDTO prestamoEstimadoDTO = buildPrestamoEstimado();

        when(this.valuacionService.prestamoEstimado(anyString(), any(BigDecimal.class))).thenReturn(prestamoEstimadoDTO);

        this.mockMvc.perform(post(ENDPOINT_BASE +"/prestamoEstimado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(buildPrestamoEstimadoRequest())))
                .andExpect(status().isOk())
                .andExpect((m) -> {
                    PrestamoEstimadoDTO response = objectMapper.readValue(m.getResponse().getContentAsString(), PrestamoEstimadoDTO.class);
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(response.getCodigoMaterial(),prestamoEstimadoDTO.getCodigoMaterial());
                    Assertions.assertEquals(response.getMaterial(),prestamoEstimadoDTO.getMaterial());
                    Assertions.assertEquals(response.getPrestamoEstimado(),prestamoEstimadoDTO.getPrestamoEstimado());
                });
    }

    /**
     * Valida el correcto funcionamiento del endpoint (/prestamoEstimado) cuando no se encuentra un material
     */
    @Test
    void whenCalculaPrestamoEstimadoReturnNotFound() throws Exception {
        when(this.valuacionService.prestamoEstimado(anyString(), any(BigDecimal.class))).thenReturn(null);

        this.mockMvc.perform(post(ENDPOINT_BASE +"/prestamoEstimado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(buildPrestamoEstimadoRequest())))
                .andExpect(status().isNotFound());
    }

    /**
     * Valida el correcto funcionamiento del endpoint (/prestamoEstimado) con parametros invalidos
     */
    @Test
    void whenCalculaPrestamoEstimadoNotCodigoMaterialReturnBadRequest() throws Exception {

        PrestamoEstimadoRequestDTO request = buildPrestamoEstimadoRequest();
        request.setCodigoMaterial(null);

        this.mockMvc.perform(post(ENDPOINT_BASE +"/prestamoEstimado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Genera un request para obtener un prestamo estimado de un articulo
     * @return prestamoEstimadoDTO
     */
    private PrestamoEstimadoRequestDTO buildPrestamoEstimadoRequest (){
        PrestamoEstimadoRequestDTO prestamoEstimadoRequestDTO = new PrestamoEstimadoRequestDTO();
        prestamoEstimadoRequestDTO.setCodigoMaterial("002");
        prestamoEstimadoRequestDTO.setPesoArticulo(BigDecimal.ONE);
        return prestamoEstimadoRequestDTO;
    }

    /**
     * Genera un respone del prestamo estimado
     * @return prestamoEstimadoDTO
     */
    private PrestamoEstimadoDTO buildPrestamoEstimado(){
        PrestamoEstimadoDTO prestamoEstimadoDTO = new PrestamoEstimadoDTO();
        prestamoEstimadoDTO.setCodigoMaterial("002");
        prestamoEstimadoDTO.setMaterial("Oro alto 18k");
        prestamoEstimadoDTO.setPrestamoEstimado(BigDecimal.valueOf(1000));
        return prestamoEstimadoDTO;
    }

}
