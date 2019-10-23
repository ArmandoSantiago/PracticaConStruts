package mx.edu.utez.domicilio.model;

public class DomicilioBean {
    private int idDomicilio;
    private String estado;
    private String municipio;
    private String colonia;
    private String calle;
    private String numero;

    public DomicilioBean(int idDomicilio, String estado, String municipio, String colonia, String calle, String numero) {
        this.idDomicilio = idDomicilio;
        this.estado = estado;
        this.municipio = municipio;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }

    public DomicilioBean() {
    }

    public int getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(int idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "DomicilioBean{" +
                "id=" + idDomicilio +
                ", estado='" + estado + '\'' +
                ", municipio='" + municipio + '\'' +
                ", colonia='" + colonia + '\'' +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
