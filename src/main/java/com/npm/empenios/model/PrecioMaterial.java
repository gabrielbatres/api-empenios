package com.npm.empenios.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

/**
 * Repesenta la información del precio por gramo de un material
 */
@Document(collection = "precios_material")
public class PrecioMaterial {

    @Id
    private String id;

    /**
     * Codigo del material
     */
    private String codigo;

    /**
     * Material (Oro puro 24K)
     */
    private String material;

    /**
     * Precio del material
     */
    @Field(name = "precio_gramo")
    private BigDecimal precioGramo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getPrecioGramo() {
        return precioGramo;
    }

    public void setPrecioGramo(BigDecimal precioGramo) {
        this.precioGramo = precioGramo;
    }
}
