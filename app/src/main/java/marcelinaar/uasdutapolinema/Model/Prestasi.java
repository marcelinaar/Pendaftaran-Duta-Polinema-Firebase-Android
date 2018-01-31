package marcelinaar.uasdutapolinema.Model;

/**
 * Created by User on 28/01/2018.
 */

public class Prestasi {

    public String idP;
    public String namaPres;
    public String juaraPres;

    public Prestasi() {
    }

    public Prestasi(String idP, String namaPres, String juaraPres) {
        this.idP = idP;
        this.namaPres = namaPres;
        this.juaraPres = juaraPres;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public String getNamaPres() {
        return namaPres;
    }

    public void setNamaPres(String namaPres) {
        this.namaPres = namaPres;
    }

    public String getJuaraPres() {
        return juaraPres;
    }

    public void setJuaraPres(String juaraPres) {
        this.juaraPres = juaraPres;
    }
}
