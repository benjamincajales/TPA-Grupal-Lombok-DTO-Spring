package com.utn.desarrollosoft.contable.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.utn.desarrollosoft.contable.dto.FacturaReporteDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReporteExportService {

    public ByteArrayInputStream generarPdf(List<FacturaReporteDTO> facturas) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Reporte Ejecutivo de Ventas").setBold().setFontSize(16));

        Table table = new Table(new float[]{1, 2, 3, 2, 2, 2, 1});
        table.addHeaderCell("N° Factura");
        table.addHeaderCell("Fecha");
        table.addHeaderCell("Cliente");
        table.addHeaderCell("Cond. IVA");
        table.addHeaderCell("Punto Venta");
        table.addHeaderCell("Total");
        table.addHeaderCell("Ítems");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (FacturaReporteDTO f : facturas) {
            table.addCell(String.valueOf(f.getNumeroFactura()));
            table.addCell(f.getFechaEmision() != null ? f.getFechaEmision().format(formatter) : "");
            table.addCell(f.getClienteDenominacion());
            table.addCell(f.getCondicionIva());
            table.addCell(f.getPuntoVentaDescripcion());
            table.addCell(String.format("%.2f", f.getImporteTotal()));
            table.addCell(String.valueOf(f.getCantidadItems()));
        }

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream generarExcelTsv(List<FacturaReporteDTO> facturas) {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Cabecera TSV
        sb.append("N° Factura\tFecha Emisión\tCliente\tCondición IVA\tPunto de Venta\tImporte Total\tCantidad Ítems\n");

        for (FacturaReporteDTO f : facturas) {
            sb.append(f.getNumeroFactura()).append("\t")
              .append(f.getFechaEmision() != null ? f.getFechaEmision().format(formatter) : "").append("\t")
              .append(f.getClienteDenominacion()).append("\t")
              .append(f.getCondicionIva()).append("\t")
              .append(f.getPuntoVentaDescripcion()).append("\t")
              .append(f.getImporteTotal()).append("\t")
              .append(f.getCantidadItems()).append("\n");
        }

        return new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}