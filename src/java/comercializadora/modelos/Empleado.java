/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.modelos;

import java.util.Date;

/**
 *
 * @author FRANCISCO
 */
public class Empleado {

    private long empleadoId;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private Date fechaNacimiento;
    private int reporta_a;
    private int extension;
    private String jefe;

    public Empleado() {
    }

    public Empleado(long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Empleado(long empleadoId, String nombreEmpleado, String apellidoEmpleado, Date fechaNacimiento, int reporta_a, int extension) {
        this.empleadoId = empleadoId;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.fechaNacimiento = fechaNacimiento;
        this.reporta_a = reporta_a;
        this.extension = extension;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    public long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getReporta_a() {
        return reporta_a;
    }

    public void setReporta_a(int reporta_a) {
        this.reporta_a = reporta_a;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public String getNombreCompleto() {
        return nombreEmpleado + " " + apellidoEmpleado;
    }

}
