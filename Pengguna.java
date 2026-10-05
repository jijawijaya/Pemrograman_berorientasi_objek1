// File: Pengguna.java

public class Pengguna {
    private String id;
    private String nama;
    private String role;
    private String password; // Data Hiding: Password disembunyikan

    public Pengguna(String id, String nama, String role, String password) {
        this.nama = nama;
        this.role = role;
        this.password = password;
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id != null && id.trim().length() >= 3) {
            this.id = id.trim().toUpperCase();
        } else {
            this.id = "USR-000";
        }
    }

    public String getNama() {
        return nama;
    }

    public String getRole() {
        return role;
    }

    // Validasi Password tanpa membocorkan isi variabel password
    public boolean cekPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void tampilkanTampilanRole() {
        System.out.println("ID   : " + id);
        System.out.println("Nama : " + nama);
        System.out.println("Role : " + role);
    }
}