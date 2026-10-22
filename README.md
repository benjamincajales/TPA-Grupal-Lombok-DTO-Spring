# Sistema Contable - API REST & Reportes (Spring Boot)

Proyecto desarrollado para el Trabajo Práctico Universitario utilizando **Spring Boot 3**, **Spring Data JPA**, **PostgreSQL**, **Lombok** e **iText 7**.

---

## 🚀 Tecnologías Utilizadas

* **Java**: Version 17+ / 25
* **Framework**: Spring Boot 3.x
* **Persistencia**: Spring Data JPA / Hibernate 7
* **Base de Datos**: PostgreSQL
* **Generación de PDF**: iText 7
* **Gestión de Dependencias**: Apache Maven
* **Lombok**: Para reducción de código boilerplate (`@Getter`, `@Setter`, `@Builder`, etc.)

---

## 📁 Estructura del Proyecto

```text
com.utn.desarrollosoft.contable
├── controller/          # Controladores REST
│   └── FacturaRestController.java
├── dto/                 # Data Transfer Objects
│   └── FacturaReporteDTO.java
├── entity/              # Entidades del Dominio
│   ├── base/            # Clases base de auditoría e ID
│   │   ├── EntityId.java
│   │   └── AuditoriaApp.java
│   ├── Cliente.java
│   ├── CondicionIva.java
│   ├── FacturaVenta.java
│   ├── FacturaVentaDetalle.java
│   ├── PuntoVenta.java
│   └── TipoMoneda.java
└── service/             # Lógica de Negocio y Exportación
    ├── FacturaService.java
    └── ReporteExportService.java
```
---
## ⚙️ Configuración del Entorno
1. Base de Datos

Asegúrate de tener un servidor PostgreSQL en ejecución y crea la base de datos:
```text
CREATE DATABASE contable_db;
```
---
## 2. Configuración (application.properties)

Configura las credenciales en src/main/resources/application.properties:
```text
spring.datasource.url=jdbc:postgresql://localhost:5432/contable_db
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD:postgres}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
---
## 🛠️ Ejecución del Proyecto

Desde Terminal (Wrapper de Maven):
```text
# Dentro de la subcarpeta contable
.\mvnw spring-boot:run
```

O abre el proyecto en tu IDE (VS Code, IntelliJ IDEA) y ejecuta la clase ContableApplication.java.

---
## 📡 Endpoints de la API REST

| Método | Endpoint | Descripción |
| :--- | :---: | ---: |
| GET | /api/facturas | Obtiene el listado de facturas en JSON (admite filtros dinámicos). |
| GET | /api/facturas/exportar/pdf | Descarga el reporte consolidado en formato PDF. |
| GET | /api/facturas/exportar/excel | Descarga el reporte consolidado en formato Excel / TSV. |
