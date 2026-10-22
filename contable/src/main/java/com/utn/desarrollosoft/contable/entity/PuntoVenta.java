package com.utn.desarrollosoft.contable.entity;

import com.utn.desarrollosoft.contable.entity.base.AuditoriaApp;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "punto_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class PuntoVenta extends AuditoriaApp {
    private String descripcion;
    private Integer numero;
}