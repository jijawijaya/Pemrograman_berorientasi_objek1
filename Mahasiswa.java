// File: Mahasiswa.java

public class Mahasiswa extends Pengguna {
    private String nim;
    private Kehadiran dataKehadiran; // Relationship / Komposisi dengan class Kehadiran

    public Mahasiswa(String id, String nama, String nim, String password, Kehadiran dataKehadiran) {
        super(id, nama, "Mahasiswa", password);
        this.nim = nim;
        this.dataKehadiran = dataKehadiran;
    }

    public String getNim() {
        return nim;
    }

    public Kehadiran getDataKehadiran() {
        return dataKehadiran;
    }

    @Override
    public void tampilkanTampilanRole() {
        System.out.println("=== DASHBOARD MAHASISWA ===");
        super.tampilkanTampilanRole();
        System.out.println("NIM  : " + nim);
    }
}