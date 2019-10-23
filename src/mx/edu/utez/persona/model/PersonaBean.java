package mx.edu.utez.persona.model;

import mx.edu.utez.domicilio.model.DomicilioBean;

public class PersonaBean {
    private int idPersona;
    private String nombre;
    private String apeidos;
    private String axoNacimiento;
    private String sexo;
    private DomicilioBean domicilioBean;

    public PersonaBean() {
    }

    public PersonaBean(int idPersona, String nombre, String apeidos, String axoNacimiento, String sexo, DomicilioBean domicilioBean) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apeidos = apeidos;
        this.axoNacimiento = axoNacimiento;
        this.sexo = sexo;
        this.domicilioBean = domicilioBean;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApeidos() {
        return apeidos;
    }

    public void setApeidos(String apeidos) {
        this.apeidos = apeidos;
    }

    public String getAxoNacimiento() {
        return axoNacimiento;
    }

    public void setAxoNacimiento(String axoNacimiento) {
        this.axoNacimiento = axoNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public DomicilioBean getDomicilioBean() {
        return domicilioBean;
    }

    public void setDomicilioBean(DomicilioBean domicilioBean) {
        this.domicilioBean = domicilioBean;
    }

    @Override
    public String toString() {
        return "PersonaBean{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", apeidos='" + apeidos + '\'' +
                ", axoNacimiento='" + axoNacimiento + '\'' +
                ", sexo='" + sexo + '\'' +
                ", domicilioBean=" + domicilioBean +
                '}';
    }
}
