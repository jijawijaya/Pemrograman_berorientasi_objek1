// File: MataKuliah.java
public class MataKuliah {
    // [Konsep: Attribute + data hiding] Data mata kuliah disimpan secara private.
    private String kodeMk;
    private String namaMk;
    private int sks;

    // [Konsep: Constructor tanpa parameter (default)] Membuat objek kosong yang dapat diisi melalui setter.
    public MataKuliah() {
        this.kodeMk = "";
        this.namaMk = "";
        this.sks = 0;
    }

    // [Konsep: Parameterized constructor + constructor overloading] Membuat objek langsung dengan data.
    public MataKuliah(String kodeMk, String namaMk, int sks) {
        setKodeMk(kodeMk);
        setNamaMk(namaMk);
        setSks(sks);
    }

    // [Konsep: Getter] Mengizinkan kode lain membaca data tanpa akses langsung ke attribute.
    public String getKodeMk() { return kodeMk; }
    public String getNamaMk() { return namaMk; }
    public int getSks() { return sks; }

    // [Konsep: Setter + validasi] Mengubah data setelah memastikan nilainya layak.
    public void setKodeMk(String kodeMk) {
        if (kodeMk == null || kodeMk.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode mata kuliah tidak boleh kosong.");
        }
        this.kodeMk = kodeMk.trim();
    }

    public void setNamaMk(String namaMk) {
        if (namaMk == null || namaMk.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama mata kuliah tidak boleh kosong.");
        }
        this.namaMk = namaMk.trim();
    }

    public void setSks(int sks) {
        if (sks < 1 || sks > 6) {
            throw new IllegalArgumentException("SKS harus antara 1 sampai 6.");
        }
        this.sks = sks;
    }
}   
