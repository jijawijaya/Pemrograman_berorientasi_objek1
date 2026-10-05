// File: Admin.java

public class Admin extends Pengguna {

    public Admin(String id, String nama, String password) {
        super(id, nama, "Admin Akademik", password);
    }

    @Override
    public void tampilkanTampilanRole() {
        System.out.println("=== DASHBOARD ADMIN ===");
        super.tampilkanTampilanRole();
        System.out.println("Akses: [1] Verifikasi All BAP  [2] Cetak Rekap Laporan");
    }
}