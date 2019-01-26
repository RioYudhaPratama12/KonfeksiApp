package app.model;

public class modelTableSablon {
    String kode_sablon, jenis_sablon, ukuran_sablon, harga_sablon;

    public String getKode_sablon() {
        return kode_sablon;
    }

    public void setKode_sablon(String kode_sablon) {
        this.kode_sablon = kode_sablon;
    }

    public String getJenis_sablon() {
        return jenis_sablon;
    }

    public void setJenis_sablon(String jenis_sablon) {
        this.jenis_sablon = jenis_sablon;
    }

    public String getUkuran_sablon() {
        return ukuran_sablon;
    }

    public void setUkuran_sablon(String ukuran_sablon) {
        this.ukuran_sablon = ukuran_sablon;
    }

    public String getHarga_sablon() {
        return harga_sablon;
    }

    public void setHarga_sablon(String harga_sablon) {
        this.harga_sablon = harga_sablon;
    }

    public modelTableSablon(String kode_sablon, String jenis_sablon, String ukuran_sablon, String harga_sablon) {
        this.kode_sablon = kode_sablon;
        this.jenis_sablon = jenis_sablon;
        this.ukuran_sablon = ukuran_sablon;
        this.harga_sablon = harga_sablon;
    }
}
