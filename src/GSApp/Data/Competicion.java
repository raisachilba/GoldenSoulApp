package GSApp.Data;

public class Competicion {

    public String nombre, fechaInicio, fechaFin, federacion, organizador, lugar, ubicacion, finRegistro;

    public Competicion(String n, String fi, String ff, String fed, String org, String l, String u, String fr){
        nombre = n;
        fechaInicio = fi;
        fechaFin = ff;
        federacion = fed;
        organizador = org;
        lugar = l;
        ubicacion = u;
        finRegistro = fr;
    }
}
