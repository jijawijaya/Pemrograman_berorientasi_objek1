// File: Main.java

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Data Awal Mata Kuliah
        Matakuliah mkPbo = new Matakuliah("TIF201", "Pemrograman Berorientasi Objek", 3);

        // Data Absensi Mahasiswa (Hadir 12 dari 14 pertemuan)
        Kehadiran absensiRian = new Kehadiran(12, 14);

        // List untuk menyimpan BAP yang diinput oleh Dosen
        ArrayList<Bap> daftarBap = new ArrayList<>();

        // BAP Awal / Contoh BAP Bawaan
        daftarBap.add(new Bap(1, "Konsep Dasarr PBO & Class Diagram", 28));

        // Array Pengguna (Polymorphism)
        Pengguna[] daftarUser = {
            new Dosen("DSN-01", "Dr. Aris", "04123456", "dosenpass"),
            new Mahasiswa("MHS-01", "Rian Pratama", "220010012", "mhspass", absensiRian)
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

            // Tampilan Dashboard Sesuai Role
            System.out.println("\n---------------------------------");
            userAktif.tampilkanTampilanRole();
            System.out.println("---------------------------------");

            boolean sesiAktif = true;
            while (sesiAktif) {
                if (userAktif instanceof Dosen) {
                    System.out.println("\n--- MENU DOSEN ---");
                    System.out.println("1. Lihat Mata Kuliah");
                    System.out.println("2. Input BAP Perkuliahan Baru");
                    System.out.println("3. Lihat Rekap BAP Perkuliahan");
                    System.out.println("4. Logout");
                    System.out.print("Pilih Menu (1-4): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        System.out.println("\nMata Kuliah: " + mkPbo.getKodeMk() + " - " + mkPbo.getNamaMk() + " (" + mkPbo.getSks() + " SKS)");

                    } else if (pilih.equals("2")) {
                        System.out.println("\n--- FORM INPUT BAP PERKULIAHAN ---");
                        System.out.print("Pertemuan Ke- : ");
                        int pert = Integer.parseInt(input.nextLine());
                        System.out.print("Materi        : ");
                        String mat = input.nextLine();
                        System.out.print("Mhs Hadir     : ");
                        int hadir = Integer.parseInt(input.nextLine());

                        // Menyimpan ke list BAP
                        daftarBap.add(new Bap(pert, mat, hadir));
                        System.out.println("[BERHASIL] BAP Pertemuan Ke-" + pert + " berhasil disimpan!");

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
                    System.out.println("2. Lihat Rekap Absensi & Kehadiran");
                    System.out.println("3. Lihat Riwayat BAP Perkuliahan");
                    System.out.println("4. Logout");
                    System.out.print("Pilih Menu (1-4): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        System.out.println("\nMatkul Diambil: " + mkPbo.getNamaMk() + " | SKS: " + mkPbo.getSks());

                    } else if (pilih.equals("2")) {
                        System.out.println("\n--- REKAP KEHADIRAN MAHASISWA ---");
                        System.out.println("Mata Kuliah : " + mkPbo.getNamaMk());
                        mhs.getDataKehadiran().tampilkanInfo();

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