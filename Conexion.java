package modelo;
public class Conexion {
    
    private String url;
    private String usuario;
    private String contraseña;
    private String driver;

    public Conexion() {
         this.url = "jdbc:mariadb://localhost:3306/calculadora";
        this.usuario = "root";
        this.contraseña = "kevin56amaya";
        this.driver = "org.mariadb.jdbc.Driver";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
    
}
    