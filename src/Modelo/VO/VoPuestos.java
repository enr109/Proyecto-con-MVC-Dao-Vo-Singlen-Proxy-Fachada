package Modelo.VO;

/**
 *
 * @author kike6
 */
public class VoPuestos {
    private int id;
    private String nompuesto;
    private String datos[] = new String[2];

    public String[] getDatos() {
        return datos;
    }

    public void setDatos(String[] datos) {
        this.datos = datos;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNompuesto() {
        return nompuesto;
    }

    public void setNompuesto(String nompuesto) {
        this.nompuesto = nompuesto;
    }
    
    
}
