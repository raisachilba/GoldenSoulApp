package GSApp.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDatos {

    Connection c;
    Statement query;

    String usuario, contrasena, baseDatosNombre;

    boolean conectado;

    public BaseDatos(String usuario, String contrasena, String baseDatosNombre){
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.baseDatosNombre = baseDatosNombre;
    }
    public void connect(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:8889/"+baseDatosNombre, usuario, contrasena);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            conectado = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public int getNumFilasTabla(String tabla){
        String q = "SELECT COUNT(*) AS num FROM "+ tabla;
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public boolean loginCorrecte(String usuario, String password){
        String q = "SELECT COUNT(*) AS N "+
                "FROM Usuario "+
                "WHERE Usuario = '"+usuario+"' AND Contraseña ='"+password+"'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            int n = rs.getInt("N");
            return (n==1);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public void insertarUsuario(String u, String n, String a, String pais, String prov, String d, String c){
        String q = "INSERT INTO Usuario (Usuario, Nombre, Apellido, Pais, Provincia, Domicilio, Contraseña) " +
                "VALUES ('"+u+"', '"+n+"', '"+a+"', '"+pais+"', '"+prov+"', '"+d+"', '"+c+"')";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void reservaClase(String n, String d, String h){
        String q = "INSERT INTO Clase (Nombre, Dia, Hora) " +
                "VALUES ('"+n+"', '"+d+"', '"+h+"')";
        System.out.println(q);
        try{
            query.execute(q);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String[][] getClasesPorDia(String fecha){
        String q = "SELECT Nombre, Hora "+
                "FROM Clase "+
                "WHERE Dia '"+fecha+"' ";
        String[][] tabla = new String[7][2];
        int i = 0;

        try{
            ResultSet rs = query.executeQuery(q);
            while(rs.next()){
                tabla[i][0] = rs.getString("Hora");
                tabla[i][1] = rs.getString("Nombre");
                i++;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return tabla;
    }

}
