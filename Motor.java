
public class Motor extends Kendaraan{
     private String jenisMotor;

    public Motor(String namaKendaraan, String nomorPlat, double hargaSewa, int lamaSewa, String jenisMotor) {
        super(namaKendaraan, nomorPlat, hargaSewa, lamaSewa);
        setJenisMotor(jenisMotor);
    }

    public String getJenisMotor() {
        return jenisMotor;
    }

    public static String normalisasiJenis(String jenis) {
        if (jenis == null) {
            return null;
        }
        if (jenis.trim().equalsIgnoreCase("Matic")) {
            return "Matic";
        }
        if (jenis.trim().equalsIgnoreCase("Manual")) {
            return "Manual";
        }
        if (jenis.trim().equalsIgnoreCase("Sport")) {
            return "Sport";
        }
        return null;
    }

    public void setJenisMotor(String jenisMotor) {
        String hasil = normalisasiJenis(jenisMotor);
        if (hasil == null) {
            System.out.println("Jenis motor hanya boleh Matic, Manual, atau Sport");
        } else {
            this.jenisMotor = hasil;
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis Motor      : " + jenisMotor);
    }
}

