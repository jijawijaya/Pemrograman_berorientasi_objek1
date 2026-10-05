// File: RepositoriBap.java

public class RepositoriBap {
    private Bap[] databaseBap;
    private int jumlahTersimpan;

    public RepositoriBap(int kapasitas) {
        this.databaseBap = new Bap[kapasitas];
        this.jumlahTersimpan = 0;
    }

    public boolean simpan(Bap bap) {
        if (jumlahTersimpan < databaseBap.length) {
            databaseBap[jumlahTersimpan] = bap;
            jumlahTersimpan++;
            return true;
        }
        return false;
    }

    public Bap[] getSemuaBap() {
        return databaseBap;
    }

    public int getJumlahTersimpan() {
        return jumlahTersimpan;
    }
}