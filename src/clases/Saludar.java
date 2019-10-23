package clases;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class Saludar {

    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String hacerSaludo() {
        mensaje = "Pepe";
        return SUCCESS;
    }
}
