// File: Kehadiran.java

public class Kehadiran {
    private int jumlahHadir;
    private int totalPertemuan;

    public Kehadiran(int jumlahHadir, int totalPertemuan) {
        this.jumlahHadir = jumlahHadir;
        this.totalPertemuan = totalPertemuan;
    }

    public int getJumlahHadir() {
        return jumlahHadir;
    }

    public int getTotalPertemuan() {
        return totalPertemuan;
    }

    // Hitung persentase kehadiran
    public double getPersentase() {
        if (totalPertemuan == 0) return 0.0;
        return ((double) jumlahHadir / totalPertemuan) * 100;
    }

    // Menampilkan info presensi
    public void tampilkanInfo() {
        System.out.println("Sudah Absen/Hadir : " + jumlahHadir + " dari " + totalPertemuan + " Pertemuan");
        System.out.printf("Persentase Kehadiran: %.1f%%\n", getPersentase());
    }
}