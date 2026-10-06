// File: Main.java

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Data Mata Kuliah
        Matakuliah mkPbo = new Matakuliah("TIF201", "Pemrograman Berorientasi Objek", 3);

        // Inisialisasi 3 Data Mahasiswa
        Mahasiswa mhs1 = new Mahasiswa("MHS-01", "Rian Pratama", "220010012", "mhspass");
        Mahasiswa mhs2 = new Mahasiswa("MHS-02", "Budi Santoso", "220010013", "mhspass");
        Mahasiswa mhs3 = new Mahasiswa("MHS-03", "Siti Aminah",  "220010014", "mhspass");

        // BAP & Data Awal (Contoh sudah berjalan 5 Pertemuan)
        ArrayList<Bap> daftarBap = new ArrayList<>();
        daftarBap.add(new Bap(1, "Pengenalan PBO & Java", 3));
        daftarBap.add(new Bap(2, "Class, Object, & Attribute", 3));
        daftarBap.add(new Bap(3, "Encapsulation & Access Modifier", 2));
        daftarBap.add(new Bap(4, "Inheritance & Superclass", 2));
        daftarBap.add(new Bap(5, "Polymorphism & Overriding", 2));

        // Simulasi Kehadiran 5 Pertemuan Awal:
        // Rian (MHS-01): Hadir 5x (Alfa 0x)
        for (int i = 0; i < 5; i++) mhs1.tambahKehadiran();
        
        // Budi (MHS-02): Hadir 3x (Alfa 2x)
        for (int i = 0; i < 3; i++) mhs2.tambahKehadiran();
        
        // Siti (MHS-03): Hadir 1x (Alfa 4x -> Melebihi batas 3x Alfa!)
        mhs3.tambahKehadiran();

        // Array Pengguna (Polymorphism)
        Pengguna[] daftarUser = {
            new Dosen("DSN-01", "Dr. Simon", "04123456", "dosenpass"),
            mhs1,
            mhs2,
            mhs3
        };

        boolean running = true;

        while (running) {
            System.out.println("\n=================================");
            System.out.println("      SISTEM LOGIN BAP SIMPLES   ");
            System.out.println("=================================");
            System.out.print("Masukkan User ID (atau EXIT): ");
            String userId = input.nextLine().trim();

            if (userId.equalsIgnoreCase("EXIT")) {
                System.out.println("[INFO] Program selesai.");
                break;
            }

            System.out.print("Masukkan Password            : ");
            String passwordInput = input.nextLine().trim();

            // Autentikasi Pengguna
            Pengguna userAktif = null;
            for (Pengguna p : daftarUser) {
                if (p.getId().equalsIgnoreCase(userId) && p.cekPassword(passwordInput)) {
                    userAktif = p;
                    break;
                }
            }

            if (userAktif == null) {
                System.out.println("\n[ERROR] ID atau Password Anda Salah!");
                continue;
            }

            // Dashboard
            System.out.println("\n---------------------------------");
            userAktif.tampilkanTampilanRole();
            System.out.println("---------------------------------");

            boolean sesiAktif = true;
            while (sesiAktif) {
                if (userAktif instanceof Dosen) {
                    System.out.println("\n--- MENU DOSEN ---");
                    System.out.println("1. Lihat Mata Kuliah");
                    System.out.println("2. Input BAP & Presensi Mahasiswa Baru");
                    System.out.println("3. Lihat Rekap BAP Perkuliahan");
                    System.out.println("4. Logout");
                    System.out.print("Pilih Menu (1-4): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        System.out.println("\nMata Kuliah: " + mkPbo.getKodeMk() + " - " + mkPbo.getNamaMk() + " (" + mkPbo.getSks() + " SKS)");

                    } else if (pilih.equals("2")) {
                        int pertBaru = daftarBap.size() + 1;
                        System.out.println("\n--- FORM INPUT BAP PERTEMUAN KE-" + pertBaru + " ---");
                        System.out.print("Masukkan Materi Hari Ini: ");
                        String mat = input.nextLine();

                        System.out.println("\n--- ABSENSI MAHASISWA ---");
                        int totalHadirHariIni = 0;

                        System.out.print("Apakah " + mhs1.getNama() + " (" + mhs1.getNim() + ") Hadir? (y/n): ");
                        if (input.nextLine().equalsIgnoreCase("y")) {
                            mhs1.tambahKehadiran();
                            totalHadirHariIni++;
                        }

                        System.out.print("Apakah " + mhs2.getNama() + " (" + mhs2.getNim() + ") Hadir? (y/n): ");
                        if (input.nextLine().equalsIgnoreCase("y")) {
                            mhs2.tambahKehadiran();
                            totalHadirHariIni++;
                        }

                        System.out.print("Apakah " + mhs3.getNama() + " (" + mhs3.getNim() + ") Hadir? (y/n): ");
                        if (input.nextLine().equalsIgnoreCase("y")) {
                            mhs3.tambahKehadiran();
                            totalHadirHariIni++;
                        }

                        daftarBap.add(new Bap(pertBaru, mat, totalHadirHariIni));
                        System.out.println("\n[BERHASIL] BAP & Presensi Pertemuan Ke-" + pertBaru + " Berhasil Disimpan!");

                    } else if (pilih.equals("3")) {
                        System.out.println("\n--- REKAP BAP PERKULIAHAN ---");
                        if (daftarBap.isEmpty()) {
                            System.out.println("Belum ada BAP yang di-input.");
                        } else {
                            for (Bap b : daftarBap) {
                                b.tampilkanBap();
                            }
                        }

                    } else if (pilih.equals("4")) {
                        sesiAktif = false;
                        System.out.println("[INFO] Logout Berhasil.");
                    } else {
                        System.out.println("[ERROR] Pilihan salah!");
                    }

                } else if (userAktif instanceof Mahasiswa) {
                    Mahasiswa mhs = (Mahasiswa) userAktif;

                    System.out.println("\n--- MENU MAHASISWA ---");
                    System.out.println("1. Lihat Mata Kuliah & SKS");
                    System.out.println("2. Cek Kelayakan UTS / UAS & Presensi Saya");
                    System.out.println("3. Lihat Riwayat BAP Perkuliahan");
                    System.out.println("4. Logout");
                    System.out.print("Pilih Menu (1-4): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        System.out.println("\nMatkul Diambil: " + mkPbo.getNamaMk() + " | SKS: " + mkPbo.getSks());

                    } else if (pilih.equals("2")) {
                        System.out.println("\n--- STATUS PRESENSI & SYARAT UJIAN ---");
                        System.out.println("Mata Kuliah : " + mkPbo.getNamaMk());
                        mhs.tampilkanRekapKehadiran(daftarBap.size());

                    } else if (pilih.equals("3")) {
                        System.out.println("\n--- RIWAYAT BAP PERKULIAHAN ---");
                        for (Bap b : daftarBap) {
                            b.tampilkanBap();
                        }

                    } else if (pilih.equals("4")) {
                        sesiAktif = false;
                        System.out.println("[INFO] Logout Berhasil.");
                    } else {
                        System.out.println("[ERROR] Pilihan salah!");
                    }
                }
            }
        }

        input.close();
    }
}