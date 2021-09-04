package test;

import datos.PersonaDAO;
import domain.Persona;

import java.util.List;
import java.util.Scanner;

public class TestManejoPersonas {

    public static void main(String[] args) {
        int resp = 0;
        do{
            resp = Menu();
            switch(resp){
                case 1:
                    AgregarRegistro();
                    break;
                case 2:
                    EliminarRegistro();
                    break;
                case 3:
                    EditarContacto();
                    break;
                case 4:
                    MostrarPersonas();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opción No Valida");
                    break;
            }
        }
        while(resp != 5);
    }

    public static int Menu(){
        Scanner reader = new Scanner(System.in);
        int resp = 0;
        System.out.println("----------------------Menú----------------------");
        System.out.println("1.- Agregar registro");
        System.out.println("2.- Eliminar Registro");
        System.out.println("3.- Actualizar Registro");
        System.out.println("4.- Mostrar Registros en la Base de Datos");
        System.out.println("5.-Salir");
        System.out.println("------------------------------------------------\n");
        resp = reader.nextInt();

        return resp;
    }

    public static void EditarContacto(){
        Scanner reader = new Scanner(System.in);
        PersonaDAO dao = new PersonaDAO();
        Persona persona = new Persona();
        String seguir;
        int registro = 0;

        System.out.println("----------------------Registro----------------------");

        System.out.println("Ingrese el id de la persona: ");
        persona.setIdPersona(reader.nextInt());

        System.out.println("Ingrese el nombre de la persona: ");
        persona.setNombre(reader.next());

        System.out.println("Ingrese el apellido de la persona: ");
        persona.setApellido(reader.next());

        System.out.println("Ingrese el email de la persona: ");
        persona.setEmail(reader.next());

        System.out.println("Ingrese el telefono de la persona: ");
        persona.setTelefono(reader.next());

        registro = dao.Acutalizar(persona);

        if(registro > 0){
            System.out.println("Registro acutalizado con éxito!");
        }else{
            System.out.println("No se ha encontrado el registro con el id proporcionado");
        }

        System.out.println("Presione enter para continuar...");
        seguir = reader.nextLine();
        System.out.println("----------------------------------------------------\n");
    }

    public static void AgregarRegistro(){
        Scanner reader = new Scanner(System.in);
        Persona persona = new Persona();
        String seguir;

        System.out.println("----------------------Registro----------------------");

        System.out.println("Ingrese el nombre de la persona: ");
        persona.setNombre(reader.next());

        System.out.println("Ingrese el apellido de la persona: ");
        persona.setApellido(reader.next());

        System.out.println("Ingrese el email de la persona: ");
        persona.setEmail(reader.next());

        System.out.println("Ingrese el telefono de la persona: ");
        persona.setTelefono(reader.next());

        PersonaDAO dao = new PersonaDAO();
        dao.Insertar(persona);
        System.out.println("Registro agregado con éxito!");

        System.out.println("Pulse enter para continuar....");
        seguir = reader.nextLine();
        System.out.println("----------------------------------------------------\n");
    }

    public static void EliminarRegistro(){
        Persona persona = new Persona();
        Scanner reader = new Scanner(System.in);
        PersonaDAO dao = new PersonaDAO();
        String seguir;

        int registro = 0;
        System.out.println("----------------------Eliminar----------------------");
        System.out.println("Ingrese el id del usuario a elminar: ");
        persona.setIdPersona(reader.nextInt());

        registro = dao.Eliminar(persona);

        if(registro > 0){
            System.out.println("Registro Eliminado Satisfactoriamente!");
        }else{
            System.out.println("No se encontro registro con el id proporcionado");
        }

        System.out.println("Pulse enter para continuar....");
        seguir = reader.nextLine();
        System.out.println("----------------------------------------------------\n");
    }

    public static void MostrarPersonas(){
        Scanner reader = new Scanner(System.in);
        String seguir;
        PersonaDAO per = new PersonaDAO();
        List<Persona> personas = per.Seleccionar();

        for(Persona persona:personas){
            System.out.println("--------------------Persona--------------------");
            System.out.println(persona.getIdPersona());
            System.out.println(persona.getNombre());
            System.out.println(persona.getApellido());
            System.out.println(persona.getEmail());
            System.out.println(persona.getTelefono());
            System.out.println("-----------------------------------------------\n");
        }

        System.out.println("Pulse enter para continuar....");
        seguir = reader.nextLine();
    }
}
