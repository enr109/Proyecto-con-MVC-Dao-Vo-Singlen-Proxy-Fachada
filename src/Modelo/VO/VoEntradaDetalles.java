package Modelo.VO;

/**
 *
 * @author kike6
 */
public class VoEntradaDetalles {
    private int identrada,idproducto,idventa,iddetall;
    private float costo,cantidad;

    public int getIddetall() {
        return iddetall;
    }

    public void setIddetall(int iddetall) {
        this.iddetall = iddetall;
    }
    
    

    public int getIdentrada() {
        return identrada;
    }

    public void setIdentrada(int identrada) {
        this.identrada = identrada;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
