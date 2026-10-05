// File: LayananBap.java

public class LayananBap {
    private RepositoriBap repo;
    private VerifikatorBap verifikator;

    public LayananBap(RepositoriBap repo, VerifikatorBap verifikator) {
        this.repo = repo;
        this.verifikator = verifikator;
    }

    public void buatBapBaru(Bap bap) {
        if (repo.simpan(bap)) {
            System.out.println("[SUKSES] BAP " + bap.getIdBap() + " berhasil diinput ke sistem.");
        } else {
            System.out.println("[ERROR] Penyimpanan BAP Penuh!");
        }
    }

    public void tampilkanLaporanSeluruhBap() {
        System.out.println("\n------------------------------------------------");
        System.out.println("       REKAPITULASI BERITA ACARA PERKULIAHAN    ");
        System.out.println("------------------------------------------------");
        Bap[] list = repo.getSemuaBap();
        for (int i = 0; i < repo.getJumlahTersimpan(); i++) {
            list[i].cetakBap();
            verifikator.tampilkanStatusVerifikasi(list[i]);
            System.out.println("------------------------------------------------");
        }
    }
}