// File: Main.java
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Matakuliah mkPbo = new Matakuliah("TIF201", "Pemrograman Berorientasi Objek", 3);
        Dosen dosenPbo = new Dosen("DSN-01", "Dr. Simon", "04123456", "dosenpass");

        Mahasiswa[] daftarMahasiswa = {
            new Mahasiswa("MHS-01", "Rian Pratama", "220010012", "mhspass"),
            new Mahasiswa("MHS-02", "Budi Santoso", "220010013", "mhspass"),
            new Mahasiswa("MHS-03", "Siti Aminah",  "220010014", "mhspass")
        };

        ArrayList<Bap> daftarBap = new ArrayList<>();

        while (true) {
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

            // Cek Login Dosen
            if (dosenPbo.getId().equalsIgnoreCase(userId) && dosenPbo.cekPassword(passwordInput)) {
                System.out.println("\n---------------------------------");
                System.out.println("=== DASHBOARD DOSEN ===");
                System.out.println("Nama : " + dosenPbo.getNama());
                System.out.println("NIDN : " + dosenPbo.getNidn());
                System.out.println("---------------------------------");

                boolean sesi = true;
                while (sesi) {
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
                        int pertBaru = daftarBap.size() + 1;
                        System.out.println("\n--- FORM INPUT BAP PERTEMUAN KE-" + pertBaru + " ---");
                        System.out.print("Masukkan Materi Perkuliahan: ");
                        String mat = input.nextLine();

                        System.out.println("\n--- ABSENSI MAHASISWA ---");
                        int totalHadirHariIni = 0;

                        for (Mahasiswa mhs : daftarMahasiswa) {
                            System.out.print("Apakah " + mhs.getNama() + " (" + mhs.getNim() + ") Hadir? (y/n): ");
                            if (input.nextLine().trim().equalsIgnoreCase("y")) {
                                mhs.tambahKehadiran();
                                totalHadirHariIni++;
                            }
                        }

                        daftarBap.add(new Bap(pertBaru, mat, totalHadirHariIni));
                        System.out.println("\n[BERHASIL] BAP & Presensi Pertemuan Ke-" + pertBaru + " Disimpan!");
                    } else if (pilih.equals("3")) {
                        System.out.println("\n--- REKAP BAP PERKULIAHAN ---");
                        if (daftarBap.isEmpty()) {
                            System.out.println("Belum ada BAP.");
                        } else {
                            for (Bap b : daftarBap) b.tampilkanBap();
                        }
                    } else if (pilih.equals("4")) {
                        sesi = false;
                    }
                }
            } else {
                // Cek Login Mahasiswa
                Mahasiswa mhsAktif = null;
                for (Mahasiswa mhs : daftarMahasiswa) {
                    if (mhs.getId().equalsIgnoreCase(userId) && mhs.cekPassword(passwordInput)) {
                        mhsAktif = mhs;
                        break;
                    }
                }

                if (mhsAktif != null) {
                    System.out.println("\n---------------------------------");
                    System.out.println("=== DASHBOARD MAHASISWA ===");
                    System.out.println("Nama : " + mhsAktif.getNama());
                    System.out.println("NIM  : " + mhsAktif.getNim());
                    System.out.println("---------------------------------");

                    boolean sesi = true;
                    while (sesi) {
                        System.out.println("\n--- MENU MAHASISWA ---");
                        System.out.println("1. Lihat Mata Kuliah & SKS");
                        System.out.println("2. Cek Presensi Saya & Syarat UTS/UAS");
                        System.out.println("3. Lihat Riwayat BAP Perkuliahan");
                        System.out.println("4. Logout");
                        System.out.print("Pilih Menu (1-4): ");
                        String pilih = input.nextLine();

                        if (pilih.equals("1")) {
                            System.out.println("\nMatkul: " + mkPbo.getNamaMk() + " | SKS: " + mkPbo.getSks());
                        } else if (pilih.equals("2")) {
                            System.out.println("\n--- STATUS PRESENSI & SYARAT UJIAN ---");
                            mhsAktif.tampilkanRekapKehadiran(daftarBap.size());
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
                } else {
                    System.out.println("\n[ERROR] ID atau Password Salah!");
                }
            }
        }
        input.close();
    }
}