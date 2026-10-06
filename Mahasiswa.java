// File: Mahasiswa.java
public class Mahasiswa {
    private String id;
    private String nama;
    private String nim;
    private String password;
    private int jumlahHadir;

    public Mahasiswa(String id, String nama, String nim, String password) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.password = password;
        this.jumlahHadir = 0;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getNim() { return nim; }
    public boolean cekPassword(String pass) { return this.password.equals(pass); }

    public void tambahKehadiran() {
        this.jumlahHadir++;
    }

    public void tampilkanRekapKehadiran(int totalPertemuan) {
        int alfa = totalPertemuan - jumlahHadir;
        System.out.println("Nama Mahasiswa      : " + nama);
        System.out.println("NIM                 : " + nim);
        System.out.println("Total Pertemuan     : " + totalPertemuan + " Pertemuan");
        System.out.println("Jumlah Hadir        : " + jumlahHadir + " Kali");
        System.out.println("Jumlah Alfa/Absen   : " + alfa + " Kali");

        double persentase = (totalPertemuan > 0) ? ((double) jumlahHadir / totalPertemuan) * 100 : 0.0;
        System.out.printf("Persentase Kehadiran: %.1f%%\n", persentase);
        System.out.println("-------------------------------------------");

        if (alfa <= 3) {
            System.out.println("Status Syarat Ujian : [MEMENUHI SYARAT] - Boleh Ikut UTS/UAS");
        } else {
            System.out.println("Status Syarat Ujian : [TIDAK MEMENUHI SYARAT] - Cekal UTS/UAS (Alfa > 3 Kali)");
        }
    }
}