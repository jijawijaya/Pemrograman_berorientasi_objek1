// File: VerifikatorBap.java

public class VerifikatorBap {
    public boolean verifikasiKelayakan(Bap bap) {
        // BAP dianggap layak jika persentase kehadiran >= 75%
        return bap.getDataKehadiran().hitungPersentase() >= 75.0;
    }

    public void tampilkanStatusVerifikasi(Bap bap) {
        boolean layak = verifikasiKelayakan(bap);
        System.out.println("Status BAP [" + bap.getIdBap() + "] : " + (layak ? "DISETUJUI (Memenuhi Korum)" : "DITOLAK (Kehadiran < 75%)"));
    }
}