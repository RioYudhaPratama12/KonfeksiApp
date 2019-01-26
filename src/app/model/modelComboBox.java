package app.model;

public class modelComboBox  {
    String jenis_produk, kode_produk, kode_sablon;

    public modelComboBox(String jenis_produk) {
    }

    public String getJenis_produk() {
        return jenis_produk;
    }

    public void setJenis_produk(String jenis_produk) {
        this.jenis_produk = jenis_produk;
    }

    public String getKode_produk() {
        return kode_produk;
    }

    public void setKode_produk(String kode_produk) {
        this.kode_produk = kode_produk;
    }

    public String getKode_sablon() {
        return kode_sablon;
    }

    public void setKode_sablon(String kode_sablon) {
        this.kode_sablon = kode_sablon;
    }


    public modelComboBox(String jenis_produk, String kode_produk, String kode_sablon) {
        this.jenis_produk = jenis_produk;
        this.kode_produk = kode_produk;
        this.kode_sablon = kode_sablon;
    }
}
