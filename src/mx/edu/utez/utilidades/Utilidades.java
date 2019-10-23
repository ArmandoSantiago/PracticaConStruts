package mx.edu.utez.utilidades;

import mx.edu.utez.docente.model.DocenteBean;
import mx.edu.utez.domicilio.model.DomicilioBean;
import mx.edu.utez.estudiante.model.EstudianteBean;
import mx.edu.utez.persona.model.PersonaBean;

import java.util.List;
import java.util.Scanner;

public class Utilidades {

    public PersonaBean getDatosPersona(){
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.println("Ingresa datos personales");
        PersonaBean personaBean = new PersonaBean();
        System.out.println("Ingresa nombre");
        personaBean.setNombre(scanner.next());
        System.out.println("Ingresa apellidos");
        personaBean.setApeidos(scanner.next());
        System.out.println("Ingresa año de nacimiento");
        personaBean.setAxoNacimiento(scanner.next());
        System.out.println("Ingresa sexo");
        personaBean.setSexo(scanner.next());

        return personaBean;
    }

    public DomicilioBean getDatosDomicilio(){
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.println("Ingresa datos del domicilio");
        DomicilioBean domicilioBean = new DomicilioBean();
        System.out.println("Ingresa estado");
        domicilioBean.setEstado(scanner.next());
        System.out.println("Ingresa municipio");
        domicilioBean.setMunicipio(scanner.next());
        System.out.println("Ingresa colonia");
        domicilioBean.setColonia(scanner.next());
        System.out.println("Ingresa calle");
        domicilioBean.setCalle(scanner.next());
        System.out.println("Ingresa número");
        domicilioBean.setNumero(scanner.next());

        return domicilioBean;
    }

    public int buscarDocenteById(List<DocenteBean> listDocente, int id){
        for (int i = 0; i < listDocente.size() ; i++) {
            if (listDocente.get(i).getId() == (id)){
                return i;
            }
        }
        return -1;
    }

    public int buscarEstudianteByMatricula(List<EstudianteBean> listEstudiantes, String matricula){
        for (int i = 0; i < listEstudiantes.size() ; i++) {
            if (listEstudiantes.get(i).getNumeroMatricula().equals(matricula)){
                return i;
            }
        }
        return -1;
    }
}
