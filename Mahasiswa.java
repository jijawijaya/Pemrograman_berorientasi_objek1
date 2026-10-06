// File: Mahasiswa.java

public class Mahasiswa extends Pengguna {
    private String nim;
    private int jumlahHadir;

    public Mahasiswa(String id, String nama, String nim, String password) {
        super(id, nama, "Mahasiswa", password);
        this.nim = nim;
        this.jumlahHadir = 0; // Awalnya 0 pertemuan hadir
    }

    public String getNim() {
        return nim;
    }

    public int getJumlahHadir() {
        return jumlahHadir;
    }

    // Method ini dipanggil saat Dosen melakukan absensi 'y' (Hadir)
    public void tambahKehadiran() {
        this.jumlahHadir++;
    }

    // Menampilkan rekap presensi berdasarkan total BAP yang sudah di-input Dosen
    public void tampilkanRekapKehadiran(int totalPertemuan) {
        int alfa = totalPertemuan - jumlahHadir;
        
        System.out.println("Nama Mahasiswa      : " + getNama());
        System.out.println("NIM                 : " + nim);
        System.out.println("Total Pertemuan     : " + totalPertemuan + " Pertemuan");
        System.out.println("Jumlah Hadir        : " + jumlahHadir + " Kali");
        System.out.println("Jumlah Alfa/Absen   : " + alfa + " Kali");
        
        double persentase = 0.0;
        if (totalPertemuan > 0) {
            persentase = ((double) jumlahHadir / totalPertemuan) * 100;
        }
        System.out.printf("Persentase Kehadiran: %.1f%%\n", persentase);
        
        // Logika Batas Maksimal Alfa = 3 Kali
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