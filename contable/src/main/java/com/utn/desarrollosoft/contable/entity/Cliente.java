package com.utn.desarrollosoft.contable.entity;

import com.utn.desarrollosoft.contable.entity.base.AuditoriaApp;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Cliente extends AuditoriaApp {
    private String denominacion;
    private String cuit;
}