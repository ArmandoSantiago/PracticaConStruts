package mx.edu.utez.estudiante.model;

import mx.edu.utez.domicilio.model.DomicilioBean;
import mx.edu.utez.persona.model.PersonaBean;

public class EstudianteBean extends PersonaBean {
    private int idEstudiante;
    private String numeroMatricula;
    private double promedio;

    public EstudianteBean() {
    }

    public EstudianteBean(int idPersona, String nombre, String apeidos, String axoNacimiento, String sexo, DomicilioBean domicilioBean, int idEstudiante, String numeroMatricula, double promedio) {
        super(idPersona, nombre, apeidos, axoNacimiento, sexo, domicilioBean);
        this.idEstudiante = idEstudiante;
        this.numeroMatricula = numeroMatricula;
        this.promedio = promedio;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "EstudianteBean{" +
                "idEstudiante=" + idEstudiante +
                ", numeroMatricula='" + numeroMatricula + '\'' +
                ", promedio=" + promedio +
                '}';
    }
}
