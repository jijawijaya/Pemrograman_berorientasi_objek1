// File: Mahasiswa.java

public class Mahasiswa extends Pengguna {
    private String nim;

    public Mahasiswa(String id, String nama, String nim, String password) {
        super(id, nama, "Mahasiswa", password);
        setNim(nim);
    }

    public String getNim() { return nim; }

    public void setNim(String nim) {
        if (nim != null && nim.length() >= 5) {
            this.nim = nim;
        } else {
            this.nim = "00000000";
        }
    }

    @Override
    public void tampilkanTampilanRole() {
        System.out.println("=== DASHBOARD MAHASISWA ===");
        super.tampilkanTampilanRole();
        System.out.println("NIM  : " + nim);
        System.out.println("Akses: [1] Lihat BAP & Presensi Perkuliahan");
    }
}