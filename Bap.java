// File: Bap.java
// [Konsep: Class] Bap adalah cetak biru untuk menyimpan satu berita acara perkuliahan.
public class Bap {
    // [Konsep: Attribute + data hiding] Rincian sesi disimpan privat di dalam setiap objek Bap.
    private String kodeMk;
    private int pertemuanKe;
    private String materi;
    private int hadir, sakit, izin, alfa;

    // [Konsep: Parameterized constructor] Mengisi keadaan awal objek Bap dengan data satu sesi.
    public Bap(String kodeMk, int pertemuanKe, String materi, int hadir, int sakit, int izin, int alfa) {
        this.kodeMk = kodeMk;
        this.pertemuanKe = pertemuanKe;
        this.materi = materi;
        this.hadir = hadir;
        this.sakit = sakit;
        this.izin = izin;
        this.alfa = alfa;
    }

    // [Konsep: Getter] Menyediakan kode mata kuliah untuk pencarian/pengelompokan BAP.
    public String getKodeMk() { return kodeMk; }
    public int getPertemuanKe() { return pertemuanKe; }

    // [Konsep: Behavior/method] Menampilkan isi objek BAP.
    public void tampilkanBap() {
        System.out.printf("  Pertemuan ke-%d | Materi: %s%n", pertemuanKe, materi);
        System.out.printf("  %-8s Hadir: %-3d | Sakit: %-3d | Izin: %-3d | Alfa: %d%n",
                "Rekap:", hadir, sakit, izin, alfa);
    }
}
