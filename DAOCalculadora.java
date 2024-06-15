package modelo;

/**
 *
 * @author RGHUEZO
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class DAOCalculadora implements OperacionesBasicas{
    //objeto para la configuracion de acceso a la bd
    Conexion bd = new Conexion();
    //objeto que representa la entidad
    Calculadora calculadora =  new Calculadora();

    @Override
    public boolean insertar(Object obj) {
       this.calculadora =(Calculadora) obj;
       Connection con;
       PreparedStatement pst;
       String sql= "insert into transactions values(?,?,?,?,?)";
       
       try{
           Class.forName(this.bd.getDriver());
           con = DriverManager.getConnection(this.bd.getUrl(),
                                            this.bd.getUsuario(),
                                            this.bd.getContraseña());
           pst = con.prepareStatement(sql);
           
           
           pst.setInt(1, this.calculadora.getId());
           pst.setString(2, this.calculadora.getTipo());
           pst.setString(3, this.calculadora.getDescripcion());
           double monto = this.calculadora.getMonto();
            if (this.calculadora.getTipo().equalsIgnoreCase("Gasto")) {
                monto = -Math.abs(monto); // Asegurarse de que el monto sea negativo
            } else {
                monto = Math.abs(monto); // Asegurarse de que el monto sea positivo
            }
           pst.setDouble(4,monto);
           pst.setDate(5, Date.valueOf(this.calculadora.getFecha()));
           
           int filasAfectadas = pst.executeUpdate();
           
           if(filasAfectadas>0){
               con.close();
               pst.close();
               return true;
           }else{
               con.close();
               pst.close();
               return false;
           }
           
       }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
           return false;
       }
    }

    @Override
    public boolean modificar(Object obj) {
this.calculadora = (Calculadora) obj;
        Connection con;
        PreparedStatement pst;
        String sql = "update transactions set tipo=?, descripcion=?, monto=?, fecha=? where id=?";

        try {
            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContraseña());
            pst = con.prepareStatement(sql);

            pst.setString(1, this.calculadora.getTipo());
            pst.setString(2, this.calculadora.getDescripcion());
            double monto = this.calculadora.getMonto();
            if (this.calculadora.getTipo().equalsIgnoreCase("Gasto")) {
                monto = -Math.abs(monto);
            } else {
                monto = Math.abs(monto);
            }
            pst.setDouble(3, monto);
            pst.setDate(4, Date.valueOf(this.calculadora.getFecha()));
            pst.setInt(5, this.calculadora.getId());

            int filasAfectadas = pst.executeUpdate();

            con.close();
            pst.close();
            return filasAfectadas > 0;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
       }
    }

    @Override
    public boolean eliminar(Object obj) {
       this.calculadora = (Calculadora) obj;
    Connection con = null;
    PreparedStatement pst = null;
    String sql = "DELETE FROM transactions WHERE id = ?";

    try {
        Class.forName(this.bd.getDriver());
        con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContraseña());
        pst = con.prepareStatement(sql);
        pst.setInt(1, this.calculadora.getId());

        int filasAfectadas = pst.executeUpdate();
        
        return filasAfectadas > 0;
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
        return false;
    } finally {
        try {
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
       }
    }

    @Override
    public ArrayList<Object[]> seleccionar() {
        ArrayList<Object[]> datos = new ArrayList<>();
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "select * from transactions";
        try {
             //cargando el Driver de conexion
             Class.forName(this.bd.getDriver());
             con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContraseña());
             //realizacion de consulta preparada
             pst = con.prepareStatement(sql);
             rs = pst.executeQuery(); //ejecuta la sentencia sql
            //recorriendo el resultset
            while(rs.next()){
                Object [] fila = new Object[5];
                for (int i=0; i<=4; i++) {
                    fila[i] = rs.getObject(i+1);
                }
                datos.add(fila);
            }
            con.close();
            pst.close();
            rs.close();
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());  
        }
        return datos;
    }
    
    @Override
            public ArrayList<Object[]> total() {
        ArrayList<Object[]> datos = new ArrayList<>();
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT SUM(monto) AS total FROM transactions";
        try {
             //cargando el Driver de conexion
             Class.forName(this.bd.getDriver());
             con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContraseña());
             //realizacion de consulta preparada
             pst = con.prepareStatement(sql);
             rs = pst.executeQuery(); //ejecuta la sentencia sql
            //recorriendo el resultset
            while(rs.next()){
                Object [] fila = new Object[1];
                for (int i=0; i<1; i++) {
                    fila[i] = rs.getObject(i+1);
                }
                datos.add(fila);
            }
            con.close();
            pst.close();
            rs.close();
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());  
        }
        return datos;
    }
            

    
public ArrayList<Map<String, Object>> DatosReporte() {
    ArrayList<Map<String, Object>> datos = new ArrayList<>();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String sql = "SELECT * FROM transactions WHERE MONTH(fecha) = 6 AND YEAR(fecha) = 2024";
    
    try {
        // Cargando el Driver de conexión
        Class.forName(this.bd.getDriver());
        con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContraseña());
        // Realización de consulta preparada
        pst = con.prepareStatement(sql);
        pst.setInt(1, this.calculadora.getMes());
        pst.setInt(2, this.calculadora.getAnio());
        
        rs = pst.executeQuery(); //ejecuta la sentencia sql
        
             
        // Recorriendo el resultset
        while (rs.next()) {
            Map<String, Object> fila = new HashMap<>();
            fila.put("id", rs.getObject("id"));
            fila.put("tipo", rs.getObject("tipo"));
            fila.put("descripcion", rs.getObject("descripcion"));
            fila.put("monto", rs.getObject("monto"));
            fila.put("fecha", rs.getObject("fecha"));            
            datos.add(fila);
        }
        con.close();
        pst.close();
        rs.close();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    return datos;
}
public Map<String, Double> obtenerDatosParaGrafico(int mes, int anio) {
    Map<String, Double> datosGrafico = new HashMap<>();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String sql = "SELECT tipo, SUM(monto) AS total FROM transactions " +
                 "WHERE MONTH(fecha) = ? AND YEAR(fecha) = ? " +
                 "GROUP BY tipo";

    try {
        Class.forName(this.bd.getDriver());
        con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContraseña());
        pst = con.prepareStatement(sql);
        pst.setInt(1, mes);
        pst.setInt(2, anio);
        rs = pst.executeQuery();

        while (rs.next()) {
            String tipo = rs.getString("tipo");
            double total = rs.getDouble("total");
            datosGrafico.put(tipo, total);
        }

        con.close();
        pst.close();
        rs.close();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }

    return datosGrafico;
}

   
}



