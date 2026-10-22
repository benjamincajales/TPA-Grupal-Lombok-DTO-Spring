package com.utn.desarrollosoft.contable.entity;

import com.utn.desarrollosoft.contable.entity.base.AuditoriaApp;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "condicion_iva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class CondicionIva extends AuditoriaApp {
    private String denominacion;
}