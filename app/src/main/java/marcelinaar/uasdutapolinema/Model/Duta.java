package marcelinaar.uasdutapolinema.Model;

/**
 * Created by User on 28/01/2018.
 */

public class Duta {

    public String iduta;
    public String nim;
    public String nama;
    public String kelas;
    public String ipk;
    public String nohp;
    public String ket;
    public String jkS;

    public Duta() {
    }

    public Duta(String iduta, String nim, String nama, String kelas, String ipk, String nohp, String ket, String jkS) {
        this.iduta = iduta;
        this.nim = nim;
        this.nama = nama;
        this.kelas = kelas;
        this.ipk = ipk;
        this.nohp = nohp;
        this.ket = ket;
        this.jkS = jkS;
    }

    public String getIduta() {
        return iduta;
    }

    public void setIduta(String iduta) {
        this.iduta = iduta;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getIpk() {
        return ipk;
    }

    public void setIpk(String ipk) {
        this.ipk = ipk;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getJkS() {
        return jkS;
    }

    public void setJkS(String jkS) {
        this.jkS = jkS;
    }
}
