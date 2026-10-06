// File: Mahasiswa.java
// [Konsep: Subclass + generalisasi + reuse] Mahasiswa mewarisi data dan method umum dari Pengguna.
public class Mahasiswa extends Pengguna {
    // [Konsep: Attribute + data hiding] NIM hanya dapat diakses melalui method class ini.
    private String nim;
    
    // [Konsep: Object state] Array ini menyimpan perubahan jumlah presensi objek mahasiswa.
    // Indeks mata kuliah: 0=PBO, 1=Struktur Data, 2=ADPL.
    private int[] totalHadir = new int[3];
    private int[] totalSakit = new int[3];
    private int[] totalIzin  = new int[3];
    private int[] totalAlfa  = new int[3];

    // [Konsep: Parameterized constructor] Mengisi data mahasiswa dan bagian Pengguna melalui super.
    public Mahasiswa(String id, String nama, String nim, String password) {
        super(id, nama, "Mahasiswa", password);
        this.nim = nim;
    }

    // [Konsep: Getter] Membaca NIM tanpa membuka attribute secara langsung.
    public String getNim() { return nim; }
    public int getTotalHadir(int indexMk) { return totalHadir[indexMk]; }

    public void tampilkanRekapKehadiranMatkul(int indexMk, MataKuliah mk, int totalPertemuan) {
        int h = totalHadir[indexMk];
        int s = totalSakit[indexMk];
        int iz = totalIzin[indexMk];
        int a = totalAlfa[indexMk];

        System.out.println("-----------------------------------------------");
        System.out.printf("%-19s: %s%n", "Mata Kuliah", mk.getNamaMk());
        System.out.printf("%-19s: %s%n", "Kode", mk.getKodeMk());
        System.out.printf("%-19s: %d%n", "SKS", mk.getSks());
        System.out.printf("%-19s: %d dari %d pertemuan%n", "Presensi", h, totalPertemuan);
        System.out.printf("%-19s: Hadir %d | Sakit %d | Izin %d | Alfa %d%n", "Rincian", h, s, iz, a);
        double persentase = totalPertemuan > 0 ? ((double) h / totalPertemuan) * 100 : 0.0;
        System.out.printf("%-19s: %.1f%%%n", "Persentase hadir", persentase);
        System.out.printf("%-19s: %s%n", "Syarat ujian", a <= 3
                ? "Memenuhi syarat"
                : "Tidak memenuhi syarat (Alfa > 3)");
        System.out.println("-----------------------------------------------");
    }

    // [Konsep: Method overloading] Versi char memudahkan pemanggilan dari input satu karakter.
    public void catatPresensi(int indexMk, char status) {
        catatPresensi(indexMk, String.valueOf(status));
    }

    // [Konsep: Behavior/method + validasi] Mencatat status dan menolak indeks/status di luar aturan.
    public void catatPresensi(int indexMk, String status) {
        if (indexMk < 0 || indexMk >= totalHadir.length) {
            throw new IllegalArgumentException("Indeks mata kuliah tidak valid.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status presensi tidak boleh kosong.");
        }

        if (status.equalsIgnoreCase("h")) totalHadir[indexMk]++;
        else if (status.equalsIgnoreCase("s")) totalSakit[indexMk]++;
        else if (status.equalsIgnoreCase("i")) totalIzin[indexMk]++;
        else if (status.equalsIgnoreCase("a")) totalAlfa[indexMk]++;
        else throw new IllegalArgumentException("Status harus h, s, i, atau a.");
    }

    // [Konsep: Behavior/method] Menampilkan ringkasan kehadiran mahasiswa per mata kuliah.
    public void tampilkanRekapKehadiran(MataKuliah[] listMk, int[] totalPertemuanPerMk) {
        System.out.println("Nama Mahasiswa : " + getNama());
        System.out.println("NIM            : " + nim);
        System.out.println("=================================================");

        for (int i = 0; i < listMk.length; i++) {
            int pert = totalPertemuanPerMk[i];
            int h = totalHadir[i];
            int s = totalSakit[i];
            int iz = totalIzin[i];
            int a = totalAlfa[i];

            System.out.println("\nMATKUL [" + listMk[i].getKodeMk() + "] : " + listMk[i].getNamaMk());
            System.out.println("-------------------------------------------------");
            System.out.println(" Rincian Presensi:");
            System.out.println("  - Hadir : " + h + " | Sakit : " + s + " | Izin : " + iz + " | Alfa : " + a);
            
            double persentase = (pert > 0) ? ((double) h / pert) * 100 : 0.0;
            System.out.printf(" Persentase Hadir    : %.1f%%\n", persentase);
            
            if (a <= 3) {
                System.out.println(" Status Syarat Ujian : [MEMENUHI SYARAT] - Boleh Ujian");
            } else {
                System.out.println(" Status Syarat Ujian : [TIDAK MEMENUHI SYARAT] - Cekal Ujian (Alfa > 3)");
            }

            // Total Kehadiran Ringkas (Format: Hadir/Total Pertemuan)
            System.out.println(" Total Kehadiran     : " + h + "/" + pert + " Pertemuan Hadir");
            System.out.println("-------------------------------------------------");
        }
    }

    @Override
    // [Konsep: Overriding] Menambahkan tampilan khusus mahasiswa pada method Pengguna.
    public void tampilkanTampilanRole() {
        System.out.println("=== DASHBOARD MAHASISWA ===");
        super.tampilkanTampilanRole();
        System.out.println("NIM  : " + nim);
    }
}
