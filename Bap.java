// File: Bap.java

public class Bap {
    private String idBap;
    private int pertemuanKe;
    private Matakuliah mataKuliah;
    private Dosen dosenPengampu;
    private MateriKuliah materi;
    private Kehadiran dataKehadiran;

    public Bap(String idBap, int pertemuanKe, Matakuliah mataKuliah, Dosen dosenPengampu, MateriKuliah materi, Kehadiran dataKehadiran) {
        this.idBap = idBap;
        this.pertemuanKe = pertemuanKe;
        this.mataKuliah = mataKuliah;
        this.dosenPengampu = dosenPengampu;
        this.materi = materi;
        this.dataKehadiran = dataKehadiran;
    }

    public String getIdBap() { return idBap; }
    public Matakuliah getMataKuliah() { return mataKuliah; }
    public Dosen getDosenPengampu() { return dosenPengampu; }
    public Kehadiran getDataKehadiran() { return dataKehadiran; }

    public void cetakBap() {
        System.out.println("ID BAP      : " + idBap);
        System.out.println("Mata Kuliah : " + mataKuliah.getNamaMk() + " (" + mataKuliah.getSks() + " SKS)");
        System.out.println("Dosen       : " + dosenPengampu.getNama());
        System.out.println("Pertemuan   : Ke-" + pertemuanKe);
        System.out.println("Materi      : " + materi.getJudulMateri() + " - " + materi.getRingkasan());
        System.out.println("Kehadiran   : " + dataKehadiran.getInfoKehadiran());
    }
}