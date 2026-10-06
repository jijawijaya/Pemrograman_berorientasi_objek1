// File: Bap.java

public class Bap {
    private int pertemuanKe;
    private String materi;
    private int mhsHadir;

    public Bap(int pertemuanKe, String materi, int mhsHadir) {
        this.pertemuanKe = pertemuanKe;
        this.materi = materi;
        this.mhsHadir = mhsHadir;
    }

    public void tampilkanBap() {
        System.out.println("• Pertemuan Ke-" + pertemuanKe + " | Materi: " + materi + " | Hadir: " + mhsHadir + " Mhs");
    }
}