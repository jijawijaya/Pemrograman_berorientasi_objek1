// File: Mahasiswa.java

public class Mahasiswa extends Pengguna {
    private String nim;
    private int jumlahHadir;

    public Mahasiswa(String id, String nama, String nim, String password) {
        super(id, nama, "Mahasiswa", password);
        this.nim = nim;
        this.jumlahHadir = 0;
    }

    public String getNim() {
        return nim;
    }

    public int getJumlahHadir() {
        return jumlahHadir;
    }

    public void tambahKehadiran() {
        this.jumlahHadir++;
    }

    // Menampilkan rekap presensi dan kelayakan Ujian (UTS/UAS)
    public void tampilkanRekapKehadiran(int totalPertemuanKelas) {
        int alfa = totalPertemuanKelas - jumlahHadir;
        
        System.out.println("Nama Mahasiswa      : " + getNama());
        System.out.println("NIM                 : " + nim);
        System.out.println("Total Pertemuan     : " + totalPertemuanKelas + " Pertemuan");
        System.out.println("Jumlah Hadir        : " + jumlahHadir + " Kali");
        System.out.println("Jumlah Alfa/Absen   : " + alfa + " Kali");
        
        double persentase = 0.0;
        if (totalPertemuanKelas > 0) {
            persentase = ((double) jumlahHadir / totalPertemuanKelas) * 100;
        }
        System.out.printf("Persentase Kehadiran: %.1f%%\n", persentase);
        
        // Logika Syarat Kelayakan UTS / UAS (Maksimal Alfa 3 Kali)
        System.out.println("-------------------------------------------");
        if (alfa <= 3) {
            System.out.println("Status Syarat Ujian : [MEMENUHI SYARAT] - Boleh Ikut UTS/UAS");
        } else {
            System.out.println("Status Syarat Ujian : [TIDAK MEMENUHI SYARAT] - Cekal UTS/UAS (Alfa > 3 Kali)");
        }
    }

    @Override
    public void tampilkanTampilanRole() {
        System.out.println("=== DASHBOARD MAHASISWA ===");
        super.tampilkanTampilanRole();
        System.out.println("NIM  : " + nim);
    }
}