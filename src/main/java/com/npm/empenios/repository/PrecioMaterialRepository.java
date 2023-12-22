package com.npm.empenios.repository;

import com.npm.empenios.model.PrecioMaterial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para realizar las operaciones CRUD del documento {@link PrecioMaterial}
 */
@Repository
public interface PrecioMaterialRepository extends MongoRepository<PrecioMaterial, String> {

    /**
     * Obtiene el precio de un material por su codigo
     * @param codigo Identificador del material
     * @return PrecioMaterial
     */
    PrecioMaterial findByCodigo(String codigo);
}
