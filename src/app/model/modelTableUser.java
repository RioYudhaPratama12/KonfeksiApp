package app.model;

public class modelTableUser {

        String username, password, nama_lengkap, jabatan, filterDataTableUser;


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNama_lengkap() {
            return nama_lengkap;
        }

        public void setNama_lengkap(String nama_lengkap) {
            this.nama_lengkap = nama_lengkap;
        }

        public String getJabatan() {
            return jabatan;
        }

        public void setJabatan(String jabatan) {
            this.jabatan = jabatan;
        }

        public String getFilterDataTableUser() {
            return filterDataTableUser;
        }

        public void setFilterDataTableUser(String filterDataTableUser) {
            this.filterDataTableUser = filterDataTableUser;
        }

        public modelTableUser(String username, String password, String nama_lengkap, String jabatan) {
            this.username = username;
            this.password = password;
            this.nama_lengkap = nama_lengkap;
            this.jabatan = jabatan;

        }
}


