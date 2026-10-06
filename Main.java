// File: Main.java

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Data Mata Kuliah
        Matakuliah mkPbo = new Matakuliah("TIF201", "Pemrograman Berorientasi Objek", 3);

        // Inisialisasi Daftar Mahasiswa (Daftar ini disimpan terpisah agar mudah diloop saat absen)
        Mahasiswa[] daftarMahasiswa = {
            new Mahasiswa("MHS-01", "Rian Pratama", "220010012", "mhspass"),
            new Mahasiswa("MHS-02", "Budi Santoso", "220010013", "mhspass"),
            new Mahasiswa("MHS-03", "Siti Aminah",  "220010014", "mhspass")
        };

        // List untuk menampung BAP yang di-input Dosen
        ArrayList<Bap> daftarBap = new ArrayList<>();

        // Array Seluruh Pengguna Sistem untuk Login (Polymorphism)
        Pengguna[] daftarUser = {
            new Dosen("DSN-01", "Dr. Simon", "04123456", "dosenpass"),
            daftarMahasiswa[0],
            daftarMahasiswa[1],
            daftarMahasiswa[2]
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

            // Autentikasi Login
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

            // Dashboard Role
            System.out.println("\n---------------------------------");
            userAktif.tampilkanTampilanRole();
            System.out.println("---------------------------------");

            boolean sesiAktif = true;
            while (sesiAktif) {
                if (userAktif instanceof Dosen) {
                    System.out.println("\n--- MENU DOSEN ---");
                    System.out.println("1. Lihat Mata Kuliah");
                    System.out.println("2. Input BAP & Presensi Mahasiswa");
                    System.out.println("3. Lihat Rekap BAP Perkuliahan");
                    System.out.println("4. Logout");
                    System.out.print("Pilih Menu (1-4): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        System.out.println("\nMata Kuliah: " + mkPbo.getKodeMk() + " - " + mkPbo.getNamaMk() + " (" + mkPbo.getSks() + " SKS)");

                    } else if (pilih.equals("2")) {
                        int pertBaru = daftarBap.size() + 1; // Otomatis pertemuan ke-1, ke-2, dst.
                        System.out.println("\n--- FORM INPUT BAP PERTEMUAN KE-" + pertBaru + " ---");
                        System.out.print("Masukkan Materi Perkuliahan Hari Ini: ");
                        String mat = input.nextLine();

                        // Loop Presensi Mahasiswa secara Otomatis
                        System.out.println("\n--- ABSENSI MAHASISWA ---");
                        int totalHadirHariIni = 0;

                        for (Mahasiswa mhs : daftarMahasiswa) {
                            System.out.print("Apakah " + mhs.getNama() + " (" + mhs.getNim() + ") Hadir? (y/n): ");
                            String jawaban = input.nextLine().trim();

                            if (jawaban.equalsIgnoreCase("y")) {
                                mhs.tambahKehadiran(); // Update realtime jumlah hadir mahasiswa
                                totalHadirHariIni++;
                            }
                        }

                        // Simpan BAP baru
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
                    System.out.println("2. Cek Presensi Saya & Syarat UTS/UAS");
                    System.out.println("3. Lihat Riwayat BAP Perkuliahan");
                    System.out.println("4. Logout");
                    System.out.print("Pilih Menu (1-4): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        System.out.println("\nMatkul Diambil: " + mkPbo.getNamaMk() + " | SKS: " + mkPbo.getSks());

                    } else if (pilih.equals("2")) {
                        System.out.println("\n--- STATUS PRESENSI & SYARAT UJIAN ---");
                        System.out.println("Mata Kuliah : " + mkPbo.getNamaMk());
                        // Memanggil rekap presensi dengan total pertemuan = jumlah BAP yang ada
                        mhs.tampilkanRekapKehadiran(daftarBap.size());

                    } else if (pilih.equals("3")) {
                        System.out.println("\n--- RIWAYAT BAP PERKULIAHAN ---");
                        if (daftarBap.isEmpty()) {
                            System.out.println("Belum ada perkuliahan yang dilaksanakan.");
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
                }
            }
        }

        input.close();
    }
}