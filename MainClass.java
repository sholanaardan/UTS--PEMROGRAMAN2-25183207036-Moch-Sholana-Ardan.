import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    private static ArrayList<Kendaraan> daftar = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaInt("Pilih menu: ");
            System.out.println();

            switch (pilihan) {
                case 1:
                    tambahKendaraan();
                    break;
                case 2:
                    tampilkanDaftar();
                    break;
                case 3:
                    ubahData();
                    break;
                case 4:
                    hitungBiaya();
                    break;
                case 5:
                    detailKendaraan();
                    break;
                case 0:
                    System.out.println("Terima kasih, program selesai.");
                    break;
                default:
                    System.out.println("Menu tidak valid.");
            }
            System.out.println();
        } while (pilihan != 0);
    }

    private static void tampilkanMenu() {
        System.out.println("===== RENTAL KENDARAAN =====");
        System.out.println("1. Tambah Kendaraan");
        System.out.println("2. Tampilkan Kendaraan");
        System.out.println("3. Ubah Data");
        System.out.println("4. Hitung Biaya");
        System.out.println("5. Detail Kendaraan");
        System.out.println("0. Keluar");
    }

    private static void tambahKendaraan() {
        System.out.println("--- Tambah Kendaraan ---");
        System.out.println("1. Mobil");
        System.out.println("2. Motor");
        int jenis = bacaInt("Pilih jenis: ");
        if (jenis != 1 && jenis != 2) {
            System.out.println("Jenis tidak valid.");
            return;
        }

        String nama = bacaString("Nama kendaraan            : ");
        String plat = bacaString("Nomor plat                : ");
        double harga = bacaDouble("Harga sewa per hari        : ");
        int lama = bacaInt("Lama sewa (hari)           : ");

        if (nama.isEmpty() || plat.isEmpty()) {
            System.out.println("Nama dan nomor plat tidak boleh kosong.");
            return;
        }
        if (harga <= 0) {
            System.out.println("Harga sewa harus lebih dari 0.");
            return;
        }
        if (lama < 1) {
            System.out.println("Lama sewa minimal 1 hari.");
            return;
        }

        if (jenis == 1) {
            int kursi = bacaInt("Jumlah kursi              : ");
            if (kursi <= 0) {
                System.out.println("Jumlah kursi harus lebih dari 0.");
                return;
            }
            daftar.add(new Mobil(nama, plat, harga, lama, kursi));
        } else {
            String jm = bacaString("Jenis (Matic/Manual/Sport) : ");
            if (Motor.normalisasiJenis(jm) == null) {
                System.out.println("Jenis motor hanya boleh Matic, Manual, atau Sport.");
                return;
            }
            daftar.add(new Motor(nama, plat, harga, lama, jm));
        }
        System.out.println("Kendaraan berhasil ditambahkan.");
    }

    private static void tampilkanDaftar() {
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data kendaraan.");
            return;
        }
        System.out.println("--- Daftar Kendaraan ---");
        for (int i = 0; i < daftar.size(); i++) {
            Kendaraan k = daftar.get(i);
            System.out.println((i + 1) + ". " + jenisDari(k)
                    + " | " + k.getNamaKendaraan()
                    + " | " + k.getNomorPlat()
                    + " | " + Kendaraan.formatRupiah(k.getHargaSewa()) + "/hari");
        }
    }

    private static void ubahData() {
        Kendaraan k = pilihKendaraan();
        if (k == null) {
            return;
        }
        System.out.println("Data yang ingin diubah:");
        System.out.println("1. Nama kendaraan");
        System.out.println("2. Nomor plat");
        System.out.println("3. Harga sewa");
        System.out.println("4. Lama sewa");
        if (k instanceof Mobil) {
            System.out.println("5. Jumlah kursi");
        } else {
            System.out.println("5. Jenis motor");
        }
        int p = bacaInt("Pilih: ");

        switch (p) {
            case 1:
                k.setNamaKendaraan(bacaString("Nama baru : "));
                break;
            case 2:
                k.setNomorPlat(bacaString("Plat baru : "));
                break;
            case 3:
                k.setHargaSewa(bacaDouble("Harga sewa baru : "));
                break;
            case 4:
                k.setLamaSewa(bacaInt("Lama sewa baru (hari) : "));
                break;
            case 5:
                if (k instanceof Mobil) {
                    Mobil m = (Mobil) k;
                    m.setJumlahKursi(bacaInt("Jumlah kursi baru : "));
                } else if (k instanceof Motor) {
                    Motor mt = (Motor) k;
                    mt.setJenisMotor(bacaString("Jenis motor baru (Matic/Manual/Sport) : "));
                }
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }
        System.out.println("Proses ubah data selesai.");
    }

    private static void hitungBiaya() {
        Kendaraan k = pilihKendaraan();
        if (k == null) {
            return;
        }
        System.out.println("Kendaraan   : " + k.getNamaKendaraan());
        System.out.println("Harga Sewa  : " + Kendaraan.formatRupiah(k.getHargaSewa()) + " x " + k.getLamaSewa() + " hari");
        System.out.println("Total Biaya : " + Kendaraan.formatRupiah(k.hitungBiaya()));
    }

    private static void detailKendaraan() {
        Kendaraan k = pilihKendaraan();
        if (k == null) {
            return;
        }
        System.out.println("--- Detail Kendaraan ---");
        System.out.println("Jenis Kendaraan  : " + jenisDari(k));
        k.tampilkanInfo();
        System.out.println("Total Biaya      : " + Kendaraan.formatRupiah(k.hitungBiaya()));
    }

    private static String jenisDari(Kendaraan k) {
        if (k instanceof Mobil) {
            return "Mobil";
        } else if (k instanceof Motor) {
            return "Motor";
        }
        return "Kendaraan";
    }

    private static Kendaraan pilihKendaraan() {
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data kendaraan.");
            return null;
        }
        tampilkanDaftar();
        int no = bacaInt("Masukkan nomor kendaraan: ");
        if (no < 1 || no > daftar.size()) {
            System.out.println("Nomor tidak ditemukan.");
            return null;
        }
        return daftar.get(no - 1);
    }

    private static String bacaString(String pesan) {
        System.out.print(pesan);
        return input.nextLine().trim();
    }

    private static int bacaInt(String pesan) {
        System.out.print(pesan);
        return Integer.parseInt(input.nextLine().trim());
    }

    private static double bacaDouble(String pesan) {
        System.out.print(pesan);
        return Double.parseDouble(input.nextLine().trim());
    }
}

