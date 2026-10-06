// File: Pengguna.java
// [Konsep: Class + superclass] Pengguna adalah cetak biru dan induk umum Dosen serta Mahasiswa.
public class Pengguna {
    // [Konsep: Attribute + data hiding] Data dibuat private agar tidak diubah langsung dari luar.
    private String id;
    private String nama;
    private String role;
    private String password;

    // [Konsep: Parameterized constructor] Menginisialisasi data umum setiap pengguna.
    public Pengguna(String id, String nama, String role, String password) {
        this.id = id;
        this.nama = nama;
        this.role = role;
        this.password = password;
    }

    // [Konsep: Getter] Memberi akses baca terkontrol untuk data pengguna.
    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getRole() { return role; }

    // [Konsep: Behavior/method + validasi] Memeriksa password yang dimasukkan saat login.
    public boolean cekPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // [Konsep: Behavior/method] Perilaku umum untuk menampilkan identitas pengguna.
    public void tampilkanTampilanRole() {
        System.out.println("ID   : " + id);
        System.out.println("Nama : " + nama);
        System.out.println("Role : " + role);
    }
}
