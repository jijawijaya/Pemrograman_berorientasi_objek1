// File: Bap.java
public class Bap {
    private String kodeMk;
    private int pertemuanKe;
    private String materi;
    private int hadir, sakit, izin, alfa;

    public Bap(String kodeMk, int pertemuanKe, String materi, int hadir, int sakit, int izin, int alfa) {
        this.kodeMk = kodeMk;
        this.pertemuanKe = pertemuanKe;
        this.materi = materi;
        this.hadir = hadir;
        this.sakit = sakit;
        this.izin = izin;
        this.alfa = alfa;
    }

    public String getKodeMk() { return kodeMk; }

    public void tampilkanBap() {
        System.out.println("Pertemuan Ke-" + pertemuanKe + " | Materi: " + materi);
        System.out.println("   Rekap: Hadir: " + hadir + " | Sakit: " + sakit + " | Izin: " + izin + " | Alfa: " + alfa);
    }
}