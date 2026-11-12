
public class Mobil extends Kendaraan{
    private int jumlahKursi;

    public Mobil(String namaKendaraan, String nomorPlat, double hargaSewa, int lamaSewa, int jumlahKursi) {
        super(namaKendaraan, nomorPlat, hargaSewa, lamaSewa);
        setJumlahKursi(jumlahKursi);
    }

    public int getJumlahKursi() {
        return jumlahKursi;
    }

    public void setJumlahKursi(int jumlahKursi) {
        if (jumlahKursi <= 0) {
            System.out.println("Jumlah kursi harus lebih dari 0");
        } else {
            this.jumlahKursi = jumlahKursi;
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jumlah Kursi     : " + jumlahKursi);
    }
}

