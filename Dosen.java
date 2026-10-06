// File: Dosen.java
// [Konsep: Subclass + generalisasi + reuse] Dosen mewarisi atribut dan method dari Pengguna.
public class Dosen extends Pengguna {
    // [Konsep: Attribute + data hiding] NIDN disimpan privat dan dibaca melalui getter.
    private String nidn;

    // [Konsep: Parameterized constructor] Mengisi data dosen dan meneruskan data umum ke superclass.
    public Dosen(String id, String nama, String nidn, String password) {
        super(id, nama, "Dosen", password);
        this.nidn = nidn;
    }

    // [Konsep: Getter] Mengambil NIDN tanpa mengakses attribute langsung.
    public String getNidn() { return nidn; }

    @Override
    // [Konsep: Overriding] Mengganti tampilan role umum dengan tampilan khusus dosen.
    public void tampilkanTampilanRole() {
        System.out.println("=== DASHBOARD DOSEN ===");
        super.tampilkanTampilanRole();
        System.out.println("NIDN : " + nidn);
    }
}
