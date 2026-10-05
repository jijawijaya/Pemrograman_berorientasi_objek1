public class Matakuliah {
    private String kodeMk;
    private String namaMk;
    private int sks;

    public Matakuliah(String kodeMk, String namaMk, int sks) {
        this.kodeMk = kodeMk;
        this.namaMk = namaMk;
        setSks(sks); // Penulisan setSks disesuaikan dengan nama method di bawah
    }

    public String getKodeMk() {
        return kodeMk;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        if (sks > 0 && sks <= 6) { // Operator <= ditulis rapat tanpa spasi
            this.sks = sks;
        } else {
            System.out.println("[VALIDASI] SKS " + sks + " tidak valid! Diatur ke default 2 SKS.");
            this.sks = 2;
        }
    }
}