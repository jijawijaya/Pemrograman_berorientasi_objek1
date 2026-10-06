// File: LayananBap.java

public class LayananBap {
    
    public void buatBapBaru(Bap bap) {
        if (bap != null) {
            System.out.println("[SUKSES] BAP berhasil diinput.");
        } else {
            System.out.println("[ERROR] Data BAP kosong!");
        }
    }

    public void tampilkanLaporanSeluruhBap(Bap[] daftarBap) {
        System.out.println("\n-------------------------------------------");
        System.out.println("     REKAPITULASI BERITA ACARA PERKULIAHAN ");
        System.out.println("-------------------------------------------");
        if (daftarBap != null) {
            for (Bap b : daftarBap) {
                if (b != null) {
                    b.tampilkanBap();
                }
            }
        }
        System.out.println("-------------------------------------------");
    }
}