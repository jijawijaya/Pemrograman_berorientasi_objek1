// File: VerifikatorBap.java

public class VerifikatorBap {

    // Memeriksa kelayakan BAP sederhana (misal layak jika mhsHadir > 0)
    public boolean verifikasiKelayakan(Bap bap) {
        return bap != null;
    }

    public void tampilkanStatusVerifikasi(Bap bap) {
        boolean layak = verifikasiKelayakan(bap);
        System.out.print("Status BAP : ");
        if (layak) {
            System.out.println("DISETUJUI");
        } else {
            System.out.println("DITOLAK");
        }
    }
}