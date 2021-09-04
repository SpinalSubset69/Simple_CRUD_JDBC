package datos;

import domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    //Esta clase se encargara de las opereaciones en la base de datos de la entidad Persona

    //Variables para almacenar los Querys a ejecutar
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono from persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "Delete FROM persona WHERE id_persona = ?";


    public List<Persona> Seleccionar(){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<Persona>();

        try {
            conn = Conexion.getConnection();
            statement = conn.prepareStatement(SQL_SELECT); //Preparamos la ejecución del query
            rs = statement.executeQuery(); //Aquí se ejecuta el query y el resultado se guarda en rs

            while(rs.next()){
                persona = new Persona();
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setEmail(rs.getString("email"));
                persona.setTelefono(rs.getString("telefono"));
                personas.add(persona);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                Conexion.Close(rs);
                Conexion.Close(statement);
                Conexion.Close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return personas;
    }

    public int Insertar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            //Se llama este metodo para darle valor a los valores que se enviaran
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            registros = stmt.executeUpdate(); //Como vamos a alterar el estado de la base de datos se llama este método
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                Conexion.Close(stmt);
                Conexion.Close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return registros;
    }

    public int Acutalizar(Persona persona){
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());
            registros = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                Conexion.Close(stmt);
                Conexion.Close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return registros;
    }

    public int Eliminar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getIdPersona());
            registros = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                Conexion.Close(stmt);
                Conexion.Close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return registros;
    }
}
