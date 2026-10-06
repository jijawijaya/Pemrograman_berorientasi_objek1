// File: MataKuliah.java

public class Matakuliah {
    private String kodeMk;
    private String namaMk;
    private int sks;

    public Matakuliah(String kodeMk, String namaMk, int sks) {
        this.kodeMk = kodeMk;
        this.namaMk = namaMk;
        setSks(sks);
    }

    public String getKodeMk() { return kodeMk; }
    public String getNamaMk() { return namaMk; }
    public int getSks() { return sks; }

    public void setSks(int sks) {
        if (sks > 0 && sks <= 6) {
            this.sks = sks;
        } else {
            this.sks = 2;
        }
    }
}