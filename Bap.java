// File: Bap.java
public class Bap {
    private int pertemuanKe;
    private String materi;
    private int jumlahHadir;

    public Bap(int pertemuanKe, String materi, int jumlahHadir) {
        this.pertemuanKe = pertemuanKe;
        this.materi = materi;
        this.jumlahHadir = jumlahHadir;
    }

    public void tampilkanBap() {
        System.out.println("Pertemuan Ke-" + pertemuanKe + " | Materi: " + materi + " | Total Hadir: " + jumlahHadir + " Mhs");
    }
}