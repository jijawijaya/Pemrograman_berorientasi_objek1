// File: Dosen.java
public class Dosen {
    private String id;
    private String nama;
    private String nidn;
    private String password;

    public Dosen(String id, String nama, String nidn, String password) {
        this.id = id;
        this.nama = nama;
        this.nidn = nidn;
        this.password = password;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getNidn() { return nidn; }
    public boolean cekPassword(String pass) { return this.password.equals(pass); }
}