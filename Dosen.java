// File: Dosen.java

public class Dosen extends Pengguna {
    private String nidn;

    public Dosen(String id, String nama, String nidn, String password) {
        super(id, nama, "Dosen", password);
        this.nidn = nidn;
    }

    public String getNidn() { return nidn; }

    @Override
    public void tampilkanTampilanRole() {
        System.out.println("=== DASHBOARD DOSEN ===");
        super.tampilkanTampilanRole();
        System.out.println("NIDN : " + nidn);
    }
}