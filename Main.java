// File: Main.java
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Data 3 Mata Kuliah
        MataKuliah[] daftarMk = {
            new MataKuliah("TIF201", "Praktikum Pemrograman Berorientasi Objek", 3),
            new MataKuliah("TIF202", "Struktur Data", 3),
            new MataKuliah("TIF203", "Analisis & Desain Perangkat Lunak", 3)
        };

        // Penghitung jumlah BAP/Pertemuan per Mata Kuliah [PBO, StructData, ADPL]
        int[] totalPertemuanPerMk = {0, 0, 0};

        // 2. Data Pengguna
        Dosen dosenPbo = new Dosen("04123456", "Dr. Simon", "04123456", "Matana12");

        Mahasiswa[] daftarMahasiswa = {
            new Mahasiswa("220010012", "Rian Pratama", "220010012", "Jakarta13"),
            new Mahasiswa("220010013", "Budi Santoso", "220010013", "Tangerang12"),
            new Mahasiswa("220010014", "Siti Aminah",  "220010014", "Banten14")
        };

        // Upcasting ke Array Superclass Pengguna
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

            // Login Validation (Polymorphism)
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

            // Dynamic Binding
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

                                mhs.catatPresensi(mkIdx, st);

                                if (st.equals("h")) statHadir++;
                                else if (st.equals("s")) statSakit++;
                                else if (st.equals("i")) statIzin++;
                                else if (st.equals("a")) statAlfa++;
                            }

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
                            for (Bap b : daftarBap) b.tampilkanBap();
                        }

                    } else if (pilih.equals("4")) {
                        sesi = false;
                    }

                } else if (userAktif instanceof Mahasiswa) {
                    Mahasiswa mhsAktif = (Mahasiswa) userAktif;

                    System.out.println("\n--- MENU MAHASISWA ---");
                    System.out.println("1. Lihat Mata Kuliah & SKS");
                    System.out.println("2. Cek Presensi Saya & Syarat UTS/UAS");
                    System.out.println("3. Lihat Riwayat BAP Perkuliahan");
                    System.out.println("4. Logout");
                    System.out.print("Pilih Menu (1-4): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        System.out.println("\n=== MATA KULIAH YANG DIAMPUL ===");
                        for (MataKuliah mk : daftarMk) {
                            System.out.println("- " + mk.getKodeMk() + " : " + mk.getNamaMk() + " (" + mk.getSks() + " SKS)");
                        }

                    } else if (pilih.equals("2")) {
                        System.out.println("\n--- REKAP PRESENSI & SYARAT UJIAN ---");
                        mhsAktif.tampilkanRekapKehadiran(daftarMk, totalPertemuanPerMk);

                    } else if (pilih.equals("3")) {
                        System.out.println("\n--- RIWAYAT BAP PERKULIAHAN ---");
                        if (daftarBap.isEmpty()) {
                            System.out.println("Belum ada perkuliahan.");
                        } else {
                            for (Bap b : daftarBap) b.tampilkanBap();
                        }

                    } else if (pilih.equals("4")) {
                        sesi = false;
                    }
                }
            }
        }
        input.close();
    }
}