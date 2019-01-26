package app.model;

public class modelStatusPesanan {

    String no_pesanan, nama_pemesan, jenis_produk, status_pesanan, gambar_desain;

    int jml_produk;


//    public void setGambar_desain(String gambar_desain) {
//        this.gambar_desain = gambar_desain;
//    }
////    public byte[] gambar_desain;

    public String getNo_pesanan() {
        return no_pesanan;
    }

    public void setNo_pesanan(String no_pesanan) {
        this.no_pesanan = no_pesanan;
    }

    public String getNama_pemesan() {
        return nama_pemesan;
    }

    public void setNama_pemesan(String nama_pemesan) {
        this.nama_pemesan = nama_pemesan;
    }

    public String getJenis_produk() {
        return jenis_produk;
    }

    public void setJenis_produk(String jenis_produk) {
        this.jenis_produk = jenis_produk;
    }

    public String getStatus_pesanan() {
        return status_pesanan;
    }

    public void setStatus_pesanan(String status_pesanan) {
        this.status_pesanan = status_pesanan;
    }

    public int getJml_produk() {
        return jml_produk;
    }

    public void setJml_produk(int jml_produk) {
        this.jml_produk = jml_produk;

    }

    public String getGambar_desain() {
        return gambar_desain;
    }

    public void setGambar_desain(String gambar_desain) {
        this.gambar_desain = gambar_desain;
    }

    public modelStatusPesanan(String no_pesanan, String nama_pemesan, String jenis_produk,Integer jml_produk, String status_pesanan, String gambar_desain
    ) {
        this.no_pesanan = no_pesanan;
        this.nama_pemesan = nama_pemesan;
        this.jenis_produk = jenis_produk;
        this.jml_produk = jml_produk;
        this.status_pesanan = status_pesanan;
        this.gambar_desain = gambar_desain;
    }

}



