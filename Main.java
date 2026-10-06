// File: Main.java
import java.util.ArrayList;
import java.util.Scanner;

// [Konsep: Class] Main menjadi titik masuk yang mengatur jalannya aplikasi.
public class Main {
    // [Konsep: Behavior/method] main menjalankan alur login, menu, dan proses perkuliahan.
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // [Konsep: Default constructor + setter] Objek dibuat kosong, lalu diisi lewat setter.
        MataKuliah mkPbo = new MataKuliah();
        mkPbo.setKodeMk("TIF201");
        mkPbo.setNamaMk("Praktikum Pemrograman Berorientasi Objek");
        mkPbo.setSks(3);

        // [Konsep: Object + parameterized constructor] Objek mata kuliah lain dibuat dengan data awal.
        MataKuliah[] daftarMk = {
            mkPbo,
            new MataKuliah("TIF202", "Struktur Data", 3),
            new MataKuliah("TIF203", "Analisis & Desain Perangkat Lunak", 3)
        };

        // Penghitung jumlah BAP/Pertemuan per Mata Kuliah [PBO, StructData, ADPL]
        int[] totalPertemuanPerMk = {0, 0, 0};
        // Target sesi per mata kuliah dalam satu semester untuk tampilan mahasiswa.
        int[] targetPertemuanPerMk = {16, 16, 16};

        // 2. Data Pengguna
        // [Konsep: Object] Membuat satu objek Dosen dari class Dosen.
        Dosen dosenPbo = new Dosen("04123456", "Dr. Simon", "04123456", "Matana12");

        // [Konsep: Object + parameterized constructor] Membuat objek-objek Mahasiswa.
        Mahasiswa[] daftarMahasiswa = {
            new Mahasiswa("220010012", "Rian Pratama", "220010012", "123"),
            new Mahasiswa("220010013", "Budi Santoso", "220010013", "Tangerang12"),
            new Mahasiswa("220010014", "Siti Aminah",  "220010014", "Banten14")
        };

        // [Konsep: Upcasting + polymorphism] Objek subclass disimpan dalam array superclass Pengguna.
        Pengguna[] daftarUser = {
            dosenPbo,
            daftarMahasiswa[0],
            daftarMahasiswa[1],
            daftarMahasiswa[2]
        };

        ArrayList<Bap> daftarBap = new ArrayList<>();

        while (true) {
            System.out.println("\n=================================");
            System.out.println("      BERITA ACARA PERKULIAHAN   ");
            System.out.println("=================================");
            System.out.print("Masukkan User ID : ");
            String userId = input.nextLine().trim();

            if (userId.equalsIgnoreCase("EXIT")) {
                System.out.println("[INFO] Program selesai.");
                break;
            }

            System.out.print("Masukkan Password            : ");
            String passwordInput = input.nextLine().trim();

            // [Konsep: Validasi] Mencocokkan ID dan password sebelum memberi akses.
            Pengguna userAktif = null;
            for (Pengguna u : daftarUser) {
                if (u.getId().equalsIgnoreCase(userId) && u.cekPassword(passwordInput)) {
                    userAktif = u;
                    break;
                }
            }

            if (userAktif == null) {
                System.out.println("\n[ERROR] ID atau Password Anda Salah!");
                continue;
            }

            // [Konsep: Dynamic binding + overriding] Method yang dipanggil mengikuti tipe objek sebenarnya.
            System.out.println("\n---------------------------------");
            userAktif.tampilkanTampilanRole();
            System.out.println("---------------------------------");

            boolean sesi = true;
            while (sesi) {
                if (userAktif instanceof Dosen) {
                    System.out.println("\n--- MENU DOSEN ---");
                    System.out.println("1. Lihat Daftar Mata Kuliah");
                    System.out.println("2. Input BAP & Presensi Mahasiswa");
                    System.out.println("3. Lihat Rekap BAP Perkuliahan");
                    System.out.println("4. Logout");
                    System.out.print("Pilih Menu (1-4): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        System.out.println("\n=== DAFTAR MATA KULIAH AMBILAN ===");
                        for (int i = 0; i < daftarMk.length; i++) {
                            System.out.println((i + 1) + ". " + daftarMk[i].getKodeMk() + " - " + daftarMk[i].getNamaMk() + " (" + daftarMk[i].getSks() + " SKS)");
                        }

                    } else if (pilih.equals("2")) {
                        System.out.println("\n--- PILIH MATA KULIAH ---");
                        for (int i = 0; i < daftarMk.length; i++) {
                            System.out.println((i + 1) + ". " + daftarMk[i].getNamaMk());
                        }
                        System.out.print("Pilih Matkul (1-3): ");
                        int mkIdx = Integer.parseInt(input.nextLine()) - 1;

                        if (mkIdx >= 0 && mkIdx < daftarMk.length) {
                            totalPertemuanPerMk[mkIdx]++;
                            int pertBaru = totalPertemuanPerMk[mkIdx];

                            System.out.println("\n--- FORM INPUT BAP [" + daftarMk[mkIdx].getNamaMk() + "] PERTEMUAN KE-" + pertBaru + " ---");
                            System.out.print("Masukkan Materi Perkuliahan: ");
                            String mat = input.nextLine();

                            System.out.println("\n--- ABSENSI MAHASISWA ---");
                            System.out.println("Keterangan Status: [h] Hadir | [s] Sakit | [i] Izin | [a] Alfa");
                            
                            int statHadir = 0, statSakit = 0, statIzin = 0, statAlfa = 0;

                            for (Mahasiswa mhs : daftarMahasiswa) {
                                String st = "";
                                while (true) {
                                    System.out.print("Status " + mhs.getNama() + " (" + mhs.getNim() + ") [h/s/i/a]: ");
                                    st = input.nextLine().trim().toLowerCase();
                                    if (st.equals("h") || st.equals("s") || st.equals("i") || st.equals("a")) {
                                        break;
                                    }
                                    System.out.println("[ERROR] Pilihan salah! Ketik 'h', 's', 'i', atau 'a'.");
                                }

                                // [Konsep: Method overloading] Memanggil versi catatPresensi yang menerima char.
                                mhs.catatPresensi(mkIdx, st.charAt(0));

                                if (st.equals("h")) statHadir++;
                                else if (st.equals("s")) statSakit++;
                                else if (st.equals("i")) statIzin++;
                                else if (st.equals("a")) statAlfa++;
                            }

                            // [Konsep: Object + state] BAP menyimpan data materi dan rekap sesi ini.
                            daftarBap.add(new Bap(daftarMk[mkIdx].getKodeMk(), pertBaru, mat, statHadir, statSakit, statIzin, statAlfa));
                            System.out.println("\n[BERHASIL] BAP & Presensi " + daftarMk[mkIdx].getNamaMk() + " Pertemuan Ke-" + pertBaru + " Disimpan!");
                        } else {
                            System.out.println("[ERROR] Pilihan Mata Kuliah tidak valid!");
                        }

                    } else if (pilih.equals("3")) {
                        System.out.println("\n--- REKAP BAP PERKULIAHAN ---");
                        if (daftarBap.isEmpty()) {
                            System.out.println("Belum ada BAP yang tersimpan.");
                        } else {
                            tampilkanRekapBapPerMataKuliah(daftarMk, daftarBap);
                        }

                    } else if (pilih.equals("4")) {
                        sesi = false;
                    }

                } else if (userAktif instanceof Mahasiswa) {
                    Mahasiswa mhsAktif = (Mahasiswa) userAktif;

                    System.out.println("\n--- MENU MAHASISWA ---");
                    System.out.println("1. Kelas Saya (Mata Kuliah & Presensi)");
                    System.out.println("2. Cek Rekap Presensi Semua Mata Kuliah");
                    System.out.println("3. Lihat Riwayat BAP Perkuliahan");
                    System.out.println("4. Logout");
                    System.out.print("Pilih Menu (1-4): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        System.out.println("\n=== KELAS SAYA ===");
                        for (int i = 0; i < daftarMk.length; i++) {
                            System.out.println((i + 1) + ". " + daftarMk[i].getNamaMk());
                            System.out.println("   " + daftarMk[i].getKodeMk() + " | " + daftarMk[i].getSks() + " SKS");
                            System.out.println("   Presensi: " + mhsAktif.getTotalHadir(i) + " dari "
                                    + targetPertemuanPerMk[i] + " pertemuan");
                        }
                        System.out.print("Pilih kelas untuk melihat detail (1-" + daftarMk.length + ", 0 untuk kembali): ");
                        int mkIdx = Integer.parseInt(input.nextLine()) - 1;
                        if (mkIdx >= 0 && mkIdx < daftarMk.length) {
                            System.out.println("\n=== DETAIL KELAS ===");
                            mhsAktif.tampilkanRekapKehadiranMatkul(mkIdx, daftarMk[mkIdx], targetPertemuanPerMk[mkIdx]);
                            System.out.println("\n--- DAFTAR SESI / BAP ---");
                            tampilkanRiwayatBapMataKuliah(daftarMk[mkIdx], daftarBap);
                        }

                    } else if (pilih.equals("2")) {
                        System.out.println("\n--- REKAP PRESENSI & SYARAT UJIAN ---");
                        mhsAktif.tampilkanRekapKehadiran(daftarMk, targetPertemuanPerMk);

                    } else if (pilih.equals("3")) {
                        System.out.println("\n--- RIWAYAT BAP PERKULIAHAN ---");
                        if (daftarBap.isEmpty()) {
                            System.out.println("Belum ada perkuliahan.");
                        } else {
                            tampilkanRekapBapPerMataKuliah(daftarMk, daftarBap);
                        }

                    } else if (pilih.equals("4")) {
                        sesi = false;
                    }
                }
            }
        }
        input.close();
    }

    private static void tampilkanRekapBapPerMataKuliah(MataKuliah[] daftarMk, ArrayList<Bap> daftarBap) {
        for (MataKuliah mk : daftarMk) {
            System.out.println("\n=== " + mk.getKodeMk() + " - " + mk.getNamaMk() + " ===");
            tampilkanRiwayatBapMataKuliah(mk, daftarBap);
        }
    }

    private static void tampilkanRiwayatBapMataKuliah(MataKuliah mk, ArrayList<Bap> daftarBap) {
        boolean adaBap = false;
        for (Bap b : daftarBap) {
            if (b.getKodeMk().equals(mk.getKodeMk())) {
                b.tampilkanBap();
                adaBap = true;
            }
        }
        if (!adaBap) {
            System.out.println("Belum ada BAP untuk mata kuliah ini.");
        }
    }
}
