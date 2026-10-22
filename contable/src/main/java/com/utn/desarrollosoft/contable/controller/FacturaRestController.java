package com.utn.desarrollosoft.contable.controller;

import com.utn.desarrollosoft.contable.dto.FacturaReporteDTO;
import com.utn.desarrollosoft.contable.service.FacturaService;
import com.utn.desarrollosoft.contable.service.ReporteExportService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaRestController {

    private final FacturaService facturaService;
    private final ReporteExportService exportService;

    public FacturaRestController(FacturaService facturaService, ReporteExportService exportService) {
        this.facturaService = facturaService;
        this.exportService = exportService;
    }

    @GetMapping
    public ResponseEntity<List<FacturaReporteDTO>> obtenerFacturas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Double montoMinimo
    ) {
        List<FacturaReporteDTO> facturas = facturaService.buscarFacturasFiltradas(fechaDesde, fechaHasta, estado, montoMinimo);
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/exportar/pdf")
    public ResponseEntity<InputStreamResource> exportarPdf(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Double montoMinimo
    ) {
        List<FacturaReporteDTO> facturas = facturaService.buscarFacturasFiltradas(fechaDesde, fechaHasta, estado, montoMinimo);
        ByteArrayInputStream stream = exportService.generarPdf(facturas);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=reporte_facturas.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(stream));
    }

    @GetMapping("/exportar/excel")
    public ResponseEntity<InputStreamResource> exportarExcel(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Double montoMinimo
    ) {
        List<FacturaReporteDTO> facturas = facturaService.buscarFacturasFiltradas(fechaDesde, fechaHasta, estado, montoMinimo);
        ByteArrayInputStream stream = exportService.generarExcelTsv(facturas);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=reporte_facturas.xls");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(stream));
    }
}