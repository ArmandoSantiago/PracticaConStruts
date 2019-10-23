package mx.edu.utez.docente.model;

import mx.edu.utez.domicilio.model.DomicilioBean;
import mx.edu.utez.persona.model.PersonaBean;

public class DocenteBean extends PersonaBean {
    private int id;
    private String numeroCedula;
    private double salarioDiario;

    public DocenteBean() {
    }

    public DocenteBean(int idPersona, String nombre, String apeidos, String axoNacimiento, String sexo, DomicilioBean domicilioBean, int id, String numeroCedula, double salarioDiario) {
        super(idPersona, nombre, apeidos, axoNacimiento, sexo, domicilioBean);
        this.id = id;
        this.numeroCedula = numeroCedula;
        this.salarioDiario = salarioDiario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroCedula() {
        return numeroCedula;
    }

    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

    public double getSalarioDiario() {
        return salarioDiario;
    }

    public void setSalarioDiario(double salarioDiario) {
        this.salarioDiario = salarioDiario;
    }

    public String toString() {
        return "Docente {" +
                "id: '" + id + '\'' +
                ", numeroCedula: '" + numeroCedula + '\'' +
                ", salarioDiario: " + salarioDiario +
                "  nombre: '" + getNombre() + '\'' +
                ", apeidos: '" + getApeidos() + '\'' +
                ", axoNacimiento: '" + getAxoNacimiento() + '\'' +
                ", sexo: '" + getSexo() + '\'' +
                ", domicilio: " + getDomicilioBean().toString() +
                '}';
    }
}
