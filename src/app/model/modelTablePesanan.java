package app.model;

public class modelTablePesanan {
    String no_pesanan, nama_pemesan, no_telp, alamat, jenis_produk, jenis_bahan, kode_bahan,
            kode_sablon, jenis_sablon, ukuran_sablon,  tanggal_pemesanan, status_pesanan, username,
            tahun, bulan ;

    int harga_bahan, jml_produk,jml_ukuran1,jml_ukuran2,jml_ukuran3,jml_ukuran4,jml_ukuran5,harga_sablon,
            total_pembayaran, bayar_dp,
            sisa_pembayaran;

    public byte[] gambar_desain;


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

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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

    public String getKode_bahan() {
        return kode_bahan;
    }

    public void setKode_bahan(String kode_bahan) {
        this.kode_bahan = kode_bahan;
    }

    public void setJenis_bahan(String jenis_bahan) {
        this.jenis_bahan = jenis_bahan;
    }

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


    public String getTanggal_pemesanan() {
        return tanggal_pemesanan;
    }

    public void setTanggal_pemesanan(String tanggal_pemesanan) {
        this.tanggal_pemesanan = tanggal_pemesanan;
    }

    public String getStatus_pesanan() {
        return status_pesanan;
    }

    public void setStatus_pesanan(String status_pesanan) {
        this.status_pesanan = status_pesanan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public int getHarga_bahan() {
        return harga_bahan;
    }

    public void setHarga_bahan(int harga_bahan) {
        this.harga_bahan = harga_bahan;
    }

    public int getJml_produk() {
        return jml_produk;
    }

    public void setJml_produk(int jml_produk) {
        this.jml_produk = jml_produk;
    }

    public int getJml_ukuran1() {
        return jml_ukuran1;
    }

    public void setJml_ukuran1(int jml_ukuran1) {
        this.jml_ukuran1 = jml_ukuran1;
    }

    public int getJml_ukuran2() {
        return jml_ukuran2;
    }

    public void setJml_ukuran2(int jml_ukuran2) {
        this.jml_ukuran2 = jml_ukuran2;
    }

    public int getJml_ukuran3() {
        return jml_ukuran3;
    }

    public void setJml_ukuran3(int jml_ukuran3) {
        this.jml_ukuran3 = jml_ukuran3;
    }

    public int getJml_ukuran4() {
        return jml_ukuran4;
    }

    public void setJml_ukuran4(int jml_ukuran4) {
        this.jml_ukuran4 = jml_ukuran4;
    }

    public int getJml_ukuran5() {
        return jml_ukuran5;
    }

    public void setJml_ukuran5(int jml_ukuran5) {
        this.jml_ukuran5 = jml_ukuran5;
    }

    public int getHarga_sablon() {
        return harga_sablon;
    }

    public void setHarga_sablon(int harga_sablon) {
        this.harga_sablon = harga_sablon;
    }

    public int getTotal_pembayaran() {
        return total_pembayaran;
    }

    public void setTotal_pembayaran(int total_pembayaran) {
        this.total_pembayaran = total_pembayaran;
    }

    public int getBayar_dp() {
        return bayar_dp;
    }

    public void setBayar_dp(int bayar_dp) {
        this.bayar_dp = bayar_dp;
    }

    public int getSisa_pembayaran() {
        return sisa_pembayaran;
    }

    public void setSisa_pembayaran(int sisa_pembayaran) {
        this.sisa_pembayaran = sisa_pembayaran;
    }

    public byte[] getGambar_desain() {
        return gambar_desain;
    }

    public void setGambar_desain(byte[] gambar_desain) {
        this.gambar_desain = gambar_desain;
    }

    public modelTablePesanan(String no_pesanan, String nama_pemesan, String no_telp, String alamat,
                             String jenis_produk, String jenis_bahan, String kode_bahan, String kode_sablon,
                             String jenis_sablon, String ukuran_sablon, String tanggal_pemesanan, String status_pesanan,
                             String username, String tahun, String bulan, int harga_bahan, int jml_produk, int jml_ukuran1,
                             int jml_ukuran2, int jml_ukuran3, int jml_ukuran4, int jml_ukuran5, int harga_sablon,
                             int total_pembayaran, int bayar_dp, int sisa_pembayaran, byte[] gambar_desain) {
        this.no_pesanan = no_pesanan;
        this.nama_pemesan = nama_pemesan;
        this.no_telp = no_telp;
        this.alamat = alamat;
        this.jenis_produk = jenis_produk;
        this.jenis_bahan = jenis_bahan;
        this.kode_bahan = kode_bahan;
        this.kode_sablon = kode_sablon;
        this.jenis_sablon = jenis_sablon;
        this.ukuran_sablon = ukuran_sablon;
        this.tanggal_pemesanan = tanggal_pemesanan;
        this.status_pesanan = status_pesanan;
        this.username = username;
        this.tahun = tahun;
        this.bulan = bulan;
        this.harga_bahan = harga_bahan;
        this.jml_produk = jml_produk;
        this.jml_ukuran1 = jml_ukuran1;
        this.jml_ukuran2 = jml_ukuran2;
        this.jml_ukuran3 = jml_ukuran3;
        this.jml_ukuran4 = jml_ukuran4;
        this.jml_ukuran5 = jml_ukuran5;
        this.harga_sablon = harga_sablon;
        this.total_pembayaran = total_pembayaran;
        this.bayar_dp = bayar_dp;
        this.sisa_pembayaran = sisa_pembayaran;
        this.gambar_desain = gambar_desain;
    }
}
