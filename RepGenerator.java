package modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

public class RepGenerator {
    public void generarReporte() {
        try {
            // Crear el diseño del reporte
            JasperDesign jasperDesign = new JasperDesign();

            // Configurar las propiedades básicas del reporte
            jasperDesign.setName("Reporte1");
            jasperDesign.setPageWidth(595);
            jasperDesign.setPageHeight(842);

            // Crear campos de datos
            JRDesignField fieldID = new JRDesignField();
            fieldID.setName("id");
            fieldID.setValueClass(Integer.class);
            jasperDesign.addField(fieldID);
            
            JRDesignField fieldTipo = new JRDesignField();
            fieldTipo.setName("tipo");
            fieldTipo.setValueClass(String.class);
            jasperDesign.addField(fieldTipo);
            
            JRDesignField fieldDesc = new JRDesignField();
            fieldDesc.setName("descripcion");
            fieldDesc.setValueClass(String.class);
            jasperDesign.addField(fieldDesc);

            JRDesignField fieldMonto= new JRDesignField();
            fieldMonto.setName("monto");
            fieldMonto.setValueClass(Double.class);
            jasperDesign.addField(fieldMonto);
            
            JRDesignField fieldFecha = new JRDesignField();
            fieldFecha.setName("fecha");
            fieldFecha.setValueClass(Date.class);
            jasperDesign.addField(fieldFecha);
                    
            // Crear una banda para el título
            JRDesignBand titleBand = new JRDesignBand();
            titleBand.setHeight(50);

            JRDesignStaticText titleText = new JRDesignStaticText();
            titleText.setText("_:: Reporte de Gastos Mensuales ::_");
            titleText.setX(0);
            titleText.setY(0);
            titleText.setWidth(595);
            titleText.setHeight(50);
            titleText.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
            titleText.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            titleText.setFontSize(Float.parseFloat("24"));
            titleBand.addElement(titleText);

            jasperDesign.setTitle(titleBand);

            // Crear una banda para el encabezado de columna
            JRDesignBand columnHeaderBand = new JRDesignBand();
            columnHeaderBand.setHeight(20);

            JRDesignStaticText headerID = new JRDesignStaticText();
            headerID.setText("ID");
            headerID.setX(0);
            headerID.setY(0);
            headerID.setWidth(100);
            headerID.setHeight(20);
            headerID.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
            headerID.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            columnHeaderBand.addElement(headerID);

            JRDesignStaticText headerTipo = new JRDesignStaticText();
            headerTipo.setText("Tipo");
            headerTipo.setX(100);
            headerTipo.setY(0);
            headerTipo.setWidth(200);
            headerTipo.setHeight(20);
            headerTipo.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
            headerTipo.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            columnHeaderBand.addElement(headerTipo);
            
            JRDesignStaticText headerDesc = new JRDesignStaticText();
            headerDesc.setText("Descripción");
            headerDesc.setX(200);
            headerDesc.setY(0);
            headerDesc.setWidth(200);
            headerDesc.setHeight(20);
            headerDesc.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
            headerDesc.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            columnHeaderBand.addElement(headerDesc);
            
            JRDesignStaticText headerMonto = new JRDesignStaticText();
            headerMonto.setText("Monto");
            headerMonto.setX(300);
            headerMonto.setY(0);
            headerMonto.setWidth(200);
            headerMonto.setHeight(20);
            headerMonto.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
            headerMonto.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            columnHeaderBand.addElement(headerMonto);
            
            JRDesignStaticText headerFecha = new JRDesignStaticText();
            headerFecha.setText("Fecha");
            headerFecha.setX(400);
            headerFecha.setY(0);
            headerFecha.setWidth(200);
            headerFecha.setHeight(20);
            headerFecha.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
            headerFecha.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            columnHeaderBand.addElement(headerFecha);

            jasperDesign.setColumnHeader(columnHeaderBand);

            // Crear una banda para el detalle
            JRDesignBand detailBand = new JRDesignBand();
            detailBand.setHeight(20);

            JRDesignTextField textFieldID = new JRDesignTextField();
            textFieldID.setX(0);
            textFieldID.setY(0);
            textFieldID.setWidth(100);
            textFieldID.setHeight(20);
            textFieldID.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
            textFieldID.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            textFieldID.setExpression(new JRDesignExpression("$F{id}"));
            detailBand.addElement(textFieldID);

            JRDesignTextField textFieldTipo = new JRDesignTextField();
            textFieldTipo.setX(100);
            textFieldTipo.setY(0);
            textFieldTipo.setWidth(200);
            textFieldTipo.setHeight(20);
            textFieldTipo.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
            textFieldTipo.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            textFieldTipo.setExpression(new JRDesignExpression("$F{tipo}"));
            detailBand.addElement(textFieldTipo);
            
            JRDesignTextField textFieldDesc = new JRDesignTextField();
            textFieldDesc.setX(200);
            textFieldDesc.setY(0);
            textFieldDesc.setWidth(200);
            textFieldDesc.setHeight(20);
            textFieldDesc.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
            textFieldDesc.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            textFieldDesc.setExpression(new JRDesignExpression("$F{descripcion}"));
            detailBand.addElement(textFieldDesc);
            
            JRDesignTextField textFieldMonto = new JRDesignTextField();
            textFieldMonto.setX(300);
            textFieldMonto.setY(0);
            textFieldMonto.setWidth(200);
            textFieldMonto.setHeight(20);
            textFieldMonto.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
            textFieldMonto.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            textFieldMonto.setExpression(new JRDesignExpression("$F{monto}"));
            detailBand.addElement(textFieldMonto);
            
            JRDesignTextField textFieldFecha= new JRDesignTextField();
            textFieldFecha.setX(400);
            textFieldFecha.setY(0);
            textFieldFecha.setWidth(200);
            textFieldFecha.setHeight(20);
            textFieldFecha.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
            textFieldFecha.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            textFieldFecha.setExpression(new JRDesignExpression("$F{fecha}"));
            detailBand.addElement(textFieldFecha);

            // Añadir la banda de detalle a la sección de detalles del reporte
            ((JRDesignSection) jasperDesign.getDetailSection()).addBand(detailBand);

            // Compilar el diseño del reporte
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Obtener los datos de la base de datos
            DAOCalculadora daoReporte = new DAOCalculadora();
            ArrayList<Map<String, Object>> datos = daoReporte.DatosReporte();

            // Llenar el reporte con los datos de la base de datos
            JRDataSource dataSource = new JRBeanCollectionDataSource(datos);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

            // Exportar el reporte a un archivo PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/Reporte1.pdf");

            // Visualizar el reporte (opcional)
            JasperViewer.viewReport(jasperPrint, false);

            System.out.println("Reporte generado exitosamente!");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
