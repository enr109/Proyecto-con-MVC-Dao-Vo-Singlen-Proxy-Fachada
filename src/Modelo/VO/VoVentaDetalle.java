package Modelo.VO;

/**
 *
 * @author kike6
 */
public class VoVentaDetalle {
    private int idventa,idprod,idinvent,iddetalle;
    private float cantidad;

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdprod() {
        return idprod;
    }

    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }

    public int getIdinvent() {
        return idinvent;
    }

    public void setIdinvent(int idinvent) {
        this.idinvent = idinvent;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
