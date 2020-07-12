package dominio;

import java.util.Date;

public class Paciente {
    private int idPaciente;
    private String rut;
    private String nombre;
    private String apellido;
    private int edad;
    private boolean estadoCovid;
    private Date fechaContagio;    

    public Paciente() {
    }

    public Paciente(int idPaciente, String rut, String nombre, String apellido, int edad, boolean estadoCovid, Date fechaContagio) {
        this.idPaciente = idPaciente;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.estadoCovid = estadoCovid;
        this.fechaContagio = fechaContagio;
    }

    public Paciente(String rut, String nombre, String apellido, int edad, boolean estadoCovid, Date fechaContagio) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.estadoCovid = estadoCovid;
        this.fechaContagio = fechaContagio;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isEstadoCovid() {
        return estadoCovid;
    }

    public void setEstadoCovid(boolean estadoCovid) {
        this.estadoCovid = estadoCovid;
    }

    public Date getFechaContagio() {
        return fechaContagio;
    }

    public void setFechaContagio(Date fechaContagio) {
        this.fechaContagio = fechaContagio;
    }




}
