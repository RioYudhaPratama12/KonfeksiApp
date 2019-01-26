package app.model;

public class modelTableBahan {
    String kode_bahan, jenis_produk, jenis_bahan, harga_bahan;

    public String getKode_bahan() {
        return kode_bahan;
    }

    public void setKode_bahan(String kode_bahan) {
        this.kode_bahan = kode_bahan;
    }

    public String getJenis_produk() {
        return jenis_produk;
    }

    public void setJenis_produk(String jenis_produk) {
        this.jenis_produk = jenis_produk;
    }

    public String getJenis_bahan() {
        return jenis_bahan;
    }

    public void setJenis_bahan(String jenis_bahan) {
        this.jenis_bahan = jenis_bahan;
    }

    public String getHarga_bahan() {
        return harga_bahan;
    }

    public void setHarga_bahan(String harga_bahan) {
        this.harga_bahan = harga_bahan;
    }

    public modelTableBahan(String kode_bahan, String jenis_produk, String jenis_bahan, String harga_bahan) {
        this.kode_bahan = kode_bahan;
        this.jenis_produk = jenis_produk;
        this.jenis_bahan = jenis_bahan;
        this.harga_bahan = harga_bahan;
    }
}
