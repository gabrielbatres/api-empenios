package com.npm.empenios.controller;

import com.npm.empenios.model.dto.PrestamoEstimadoDTO;
import com.npm.empenios.model.dto.PrestamoEstimadoRequestDTO;
import com.npm.empenios.service.IValuacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controlador REST que expone la operaciones relacionadas con al valuacion de artículos
 *
 * @author Gabriel Batres
 */
@RestController
@RequestMapping("/valuacion")
@Tag(name = "API Valuacion articulos", description = "API que realiza las operaciones relacionadas con al valuacion de artículo")
public class ValuacionController {

    /**
     * Referencia al servcio {@link IValuacionService}
     */
    @Autowired
    private final IValuacionService valuacionService;

    @Autowired
    public ValuacionController(IValuacionService valuacionService) {
        this.valuacionService = valuacionService;
    }

    @ResponseBody
    @PostMapping(value = "/prestamoEstimado", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "Calcula el préstamo estimado según el material y peso del artículo", responses = {
            @ApiResponse(responseCode = "200", description = "Calculo del prestamo estimado exitoso."),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos."),
            @ApiResponse(responseCode = "404", description = "El recurso que desea no fue encontrado"),
            @ApiResponse(responseCode = "500", description = "Error no esperado") })
    public ResponseEntity<PrestamoEstimadoDTO> prestamoEstimado(@RequestBody @Valid @NotNull PrestamoEstimadoRequestDTO request){

        PrestamoEstimadoDTO response = valuacionService.prestamoEstimado(request.getCodigoMaterial(), request.getPesoArticulo());

        if (response == null) {
            return new ResponseEntity<>(new PrestamoEstimadoDTO(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
