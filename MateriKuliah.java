// File: MateriKuliah.java

public class MateriKuliah {
    private String judulMateri;
    private String ringkasan;

    public MateriKuliah(String judulMateri, String ringkasan) {
        this.judulMateri = judulMateri;
        this.ringkasan = ringkasan;
    }

    public String getJudulMateri() { return judulMateri; }
    public String getRingkasan() { return ringkasan; }
}