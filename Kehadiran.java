// File: Kehadiran.java

public class Kehadiran {
    private int jumlahHadir;
    private int totalMahasiswa;

    public Kehadiran(int jumlahHadir, int totalMahasiswa) {
        this.totalMahasiswa = totalMahasiswa > 0 ? totalMahasiswa : 1;
        setJumlahHadir(jumlahHadir);
    }

    public void setJumlahHadir(int jumlahHadir) {
        if (jumlahHadir >= 0 && jumlahHadir <= totalMahasiswa) {
            this.jumlahHadir = jumlahHadir;
        } else {
            System.out.println("[VALIDASI] Jumlah hadir (" + jumlahHadir + ") melampaui batas! Diatur ke 0.");
            this.jumlahHadir = 0;
        }
    }

    public double hitungPersentase() {
        return ((double) jumlahHadir / totalMahasiswa) * 100.0;
    }

    public String getInfoKehadiran() {
        return jumlahHadir + "/" + totalMahasiswa + " Mahasiswa (" + String.format("%.1f", hitungPersentase()) + "%)";
    }
}