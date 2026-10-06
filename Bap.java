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
    public int getPertemuanKe() { return pertemuanKe; }

    public void tampilkanBap() {
        System.out.printf("  Pertemuan ke-%d | Materi: %s%n", pertemuanKe, materi);
        System.out.printf("  %-8s Hadir: %-3d | Sakit: %-3d | Izin: %-3d | Alfa: %d%n",
                "Rekap:", hadir, sakit, izin, alfa);
    }
}
