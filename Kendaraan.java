
public class Kendaraan {
    private String namaKendaraan;
    private String nomorPlat;
    private double hargaSewa;
    private int lamaSewa;

    public Kendaraan(String namaKendaraan, String nomorPlat, double hargaSewa, int lamaSewa) {
        setNamaKendaraan(namaKendaraan);
        setNomorPlat(nomorPlat);
        setHargaSewa(hargaSewa);
        setLamaSewa(lamaSewa);
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public String getNomorPlat() {
        return nomorPlat;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public int getLamaSewa() {
        return lamaSewa;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        if (namaKendaraan == null || namaKendaraan.trim().isEmpty()) {
            System.out.println("Nama kendaraan tidak boleh kosong");
        } else {
            this.namaKendaraan = namaKendaraan.trim();
        }
    }

    public void setNomorPlat(String nomorPlat) {
        if (nomorPlat == null || nomorPlat.trim().isEmpty()) {
            System.out.println("Nomor plat tidak boleh kosong");
        } else {
            this.nomorPlat = nomorPlat.trim();
        }
    }

    public void setHargaSewa(double hargaSewa) {
        if (hargaSewa <= 0) {
            System.out.println("Harga sewa harus lebih dari 0");
        } else {
            this.hargaSewa = hargaSewa;
        }
    }

    public void setLamaSewa(int lamaSewa) {
        if (lamaSewa < 1) {
            System.out.println("Lama sewa minimal 1 hari");
        } else {
            this.lamaSewa = lamaSewa;
        }
    }

    public double hitungBiaya() {
        return hargaSewa * lamaSewa;
    }

    public void tampilkanInfo() {
        System.out.println("Nama             : " + namaKendaraan);
        System.out.println("Nomor Plat       : " + nomorPlat);
        System.out.println("Harga Sewa/Hari  : " + formatRupiah(hargaSewa));
        System.out.println("Lama Sewa        : " + lamaSewa + " hari");
    }

    public static String formatRupiah(double angka) {
        String s = String.valueOf((long) angka);
        String hasil = "";
        int hitung = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            hasil = s.charAt(i) + hasil;
            hitung++;
            if (hitung % 3 == 0 && i != 0) {
                hasil = "." + hasil;
            }
        }
        return "Rp" + hasil;
    }
}

