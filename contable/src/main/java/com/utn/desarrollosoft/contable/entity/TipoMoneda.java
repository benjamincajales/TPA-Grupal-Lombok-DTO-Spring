package com.utn.desarrollosoft.contable.entity;

import com.utn.desarrollosoft.contable.entity.base.AuditoriaApp;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tipo_moneda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class TipoMoneda extends AuditoriaApp {
    private String descripcion;
    private String simbolo;
}