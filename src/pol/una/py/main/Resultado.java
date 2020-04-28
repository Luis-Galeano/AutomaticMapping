/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pol.una.py.main;

/**
 *
 * @author konecta
 */
public class Resultado implements Comparable<Resultado>{
    private String configuracion;
    private Double precision;
    private Double exactitud;

    public String getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    }

    public Double getPrecision() {
        return precision;
    }

    public void setPrecision(Double precision) {
        this.precision = precision;
    }

    public Double getExactitud() {
        return exactitud;
    }

    public void setExactitud(Double exactitud) {
        this.exactitud = exactitud;
    }

    @Override
    public String toString() {
        return "Resultado{" + "configuracion=" + configuracion + ", precision=" + precision + ", exactitud=" + exactitud + '}';
    }

    

    

    @Override
    public int compareTo(Resultado t) {
        return this.precision.compareTo(t.getPrecision());
    }
    
    
}
