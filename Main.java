// File: Main.java

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Inisialisasi Database Sistem & Layanan BAP
        RepositoriBap repo = new RepositoriBap(10);
        VerifikatorBap verifikator = new VerifikatorBap();
        LayananBap layanan = new LayananBap(repo, verifikator);

        // Data Awal Dummy
        Matakuliah mkPbo = new Matakuliah("TIF201", "Pemrograman Berorientasi Objek", 3);
        Dosen dosenPbo = new Dosen("DSN-01", "Dr. Aris", "04123456", "dosenpass");

        MateriKuliah m1 = new MateriKuliah("PBO Modul 1-3", "Encapsulation & Polymorphism");
        Kehadiran k1 = new Kehadiran(28, 30);
        layanan.buatBapBaru(new Bap("BAP-001", 1, mkPbo, dosenPbo, m1, k1));

        // Array Pengguna Terdaftar (Database User)
        Pengguna[] akunTerdaftar = {
            new Admin("ADM-01", "Siti Aminah", "adminpass"),
            dosenPbo,
            new Mahasiswa("MHS-01", "Rian Pratama", "220010012", "mhspass")
        };

        boolean running = true;

        while (running) {
            System.out.println("\n=================================================");
            System.out.println("   SISTEM LOG IN BERITA ACARA PERKULIAHAN (BAP)  ");
            System.out.println("=================================================");
            System.out.println("Ketik 'EXIT' pada User ID untuk keluar.");
            System.out.print("Masukkan User ID : ");
            String userId = input.nextLine().trim();

            if (userId.equalsIgnoreCase("EXIT")) {
                System.out.println("\n[INFO] Terima kasih telah menggunakan sistem!");
                running = false;
                break;
            }

            System.out.print("Masukkan Password: ");
            String passwordInput = input.nextLine().trim();

            // Autentikasi Pengguna
            Pengguna currentUser = null;
            for (Pengguna p : akunTerdaftar) {
                if (p.getId().equalsIgnoreCase(userId) && p.cekPassword(passwordInput)) {
                    currentUser = p;
                    break;
                }
            }

            if (currentUser == null) {
                System.out.println("\n[ERROR LOGIN] User ID atau Password salah!");
                continue;
            }

            // Tampilan Dashboard Utama Pengguna (Dynamic Binding)
            System.out.println("\n=================================================");
            System.out.println("            LOG IN BERHASIL                      ");
            System.out.println("=================================================");
            currentUser.tampilkanTampilanRole();
            System.out.println("-------------------------------------------------");

            // Menu Interaktif Sesuai Role
            boolean sessionActive = true;
            while (sessionActive) {
                if (currentUser instanceof Admin) {
                    System.out.println("\n=== MENU INTERAKTIF ADMIN ===");
                    System.out.println("1. Lihat & Verifikasi Seluruh Rekap BAP");
                    System.out.println("2. Logout");
                    System.out.print("Pilih Menu (1-2): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        layanan.tampilkanLaporanSeluruhBap();
                    } else if (pilih.equals("2")) {
                        sessionActive = false;
                        System.out.println("[INFO] Admin berhasil Logout.");
                    } else {
                        System.out.println("[ERROR] Pilihan menu tidak valid!");
                    }

                } else if (currentUser instanceof Dosen) {
                    System.out.println("\n=== MENU INTERAKTIF DOSEN ===");
                    System.out.println("1. Input BAP Perkuliahan Baru");
                    System.out.println("2. Lihat Rekap BAP");
                    System.out.println("3. Logout");
                    System.out.print("Pilih Menu (1-3): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        System.out.println("\n--- FORM INPUT BAP BARU ---");
                        System.out.print("ID BAP (cth: BAP-002)     : ");
                        String idBap = input.nextLine();
                        System.out.print("Pertemuan Ke (Angka)       : ");
                        int pert = Integer.parseInt(input.nextLine());
                        System.out.print("Judul Materi               : ");
                        String judul = input.nextLine();
                        System.out.print("Ringkasan Materi           : ");
                        String ringkasan = input.nextLine();
                        System.out.print("Jumlah Mahasiswa Hadir     : ");
                        int hadir = Integer.parseInt(input.nextLine());
                        System.out.print("Total Seluruh Mahasiswa    : ");
                        int totalMhs = Integer.parseInt(input.nextLine());

                        MateriKuliah mat = new MateriKuliah(judul, ringkasan);
                        Kehadiran khd = new Kehadiran(hadir, totalMhs);
                        Bap bapBaru = new Bap(idBap, pert, mkPbo, (Dosen) currentUser, mat, khd);

                        layanan.buatBapBaru(bapBaru);
                    } else if (pilih.equals("2")) {
                        layanan.tampilkanLaporanSeluruhBap();
                    } else if (pilih.equals("3")) {
                        sessionActive = false;
                        System.out.println("[INFO] Dosen berhasil Logout.");
                    } else {
                        System.out.println("[ERROR] Pilihan menu tidak valid!");
                    }

                } else if (currentUser instanceof Mahasiswa) {
                    System.out.println("\n=== MENU INTERAKTIF MAHASISWA ===");
                    System.out.println("1. Lihat BAP & Presensi Kehadiran Perkuliahan");
                    System.out.println("2. Logout");
                    System.out.print("Pilih Menu (1-2): ");
                    String pilih = input.nextLine();

                    if (pilih.equals("1")) {
                        layanan.tampilkanLaporanSeluruhBap();
                    } else if (pilih.equals("2")) {
                        sessionActive = false;
                        System.out.println("[INFO] Mahasiswa berhasil Logout.");
                    } else {
                        System.out.println("[ERROR] Pilihan menu tidak valid!");
                    }
                }
            }
        }

        input.close();
    }
}