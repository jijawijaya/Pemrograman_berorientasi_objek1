// File: Dosen.java

public class Dosen extends Pengguna {
    private String nidn;

    public Dosen(String id, String nama, String nidn, String password) {
        super(id, nama, "Dosen", password);
        setNidn(nidn);
    }

    public String getNidn() { return nidn; }

    public void setNidn(String nidn) {
        if (nidn != null && nidn.length() >= 5) {
            this.nidn = nidn;
        } else {
            this.nidn = "00000000";
        }
    }

    @Override
    public void tampilkanTampilanRole() {
        System.out.println("=== DASHBOARD DOSEN ===");
        super.tampilkanTampilanRole();
        System.out.println("NIDN : " + nidn);
        System.out.println("Akses: [1] Input BAP  [2] Lihat Rekap BAP");
    }
}