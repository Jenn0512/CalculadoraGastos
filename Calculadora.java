package modelo;

import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class Calculadora {
    private int id;
    private String tipo; 
    private String descripcion;
    private double monto;
    private LocalDate fecha;
    private int mes; 
    private int anio; 

    public Calculadora() {
    }

    public Calculadora(int id, String tipo, String descripcion, double monto, LocalDate fecha, int mes, int anio) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
        this.mes = mes;
        this.anio = anio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    

}