// File: Pengguna.java

public class Pengguna {
    private String id;
    private String nama;
    private String role;
    private String password;

    public Pengguna(String id, String nama, String role, String password) {
        this.id = id;
        this.nama = nama;
        this.role = role;
        this.password = password;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }

    public boolean cekPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void tampilkanTampilanRole() {
        System.out.println("ID   : " + id);
        System.out.println("Nama : " + nama);
        System.out.println("Role : " + role);
    }
}