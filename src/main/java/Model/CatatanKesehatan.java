package Model;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * @author Farraz Firdaus
 */
public class CatatanKesehatan {
    private final ObjectProperty<LocalDate> tanggal;
    private final DoubleProperty beratBadan;
    private final StringProperty tekananDarah;
    private final IntegerProperty detakJantung;
    private final DoubleProperty jamTidur;
    private final StringProperty catatan;

    public CatatanKesehatan(LocalDate tanggal, double beratBadan, String tekananDarah,
                       int detakJantung, double jamTidur, String catatan) {
        this.tanggal = new SimpleObjectProperty<>(tanggal);
        this.beratBadan = new SimpleDoubleProperty(beratBadan);
        this.tekananDarah = new SimpleStringProperty(tekananDarah);
        this.detakJantung = new SimpleIntegerProperty(detakJantung);
        this.jamTidur = new SimpleDoubleProperty(jamTidur);
        this.catatan = new SimpleStringProperty(catatan);
    }

    public LocalDate getTanggal() {

        return tanggal.get();
    }

    public void setTanggal(LocalDate tanggal) {

        this.tanggal.set(tanggal);
    }

    public ObjectProperty<LocalDate> tanggalProperty() {

        return tanggal;
    }

    public double getBeratBadan() {

        return beratBadan.get();
    }

    public void setBeratBadan(double beratBadan) {

        this.beratBadan.set(beratBadan);
    }

    public DoubleProperty beratBadanProperty() {

        return beratBadan;
    }

    public String getTekananDarah() {

        return tekananDarah.get();
    }

    public void setTekananDarah(String tekananDarah) {

        this.tekananDarah.set(tekananDarah);
    }

    public StringProperty tekananDarahProperty() {

        return tekananDarah;
    }

    public int getDetakJantung() {

        return detakJantung.get();
    }

    public void setDetakJantung(int detakJantung) {

        this.detakJantung.set(detakJantung);
    }

    public IntegerProperty detakJantungProperty() {

        return detakJantung;
    }

    public double getJamTidur() {

        return jamTidur.get();
    }

    public void setJamTidur(double jamTidur) {

        this.jamTidur.set(jamTidur);
    }

    public DoubleProperty jamTidurProperty() {

        return jamTidur;
    }

    public String getCatatan() {

        return catatan.get();
    }

    public void setCatatan(String catatan) {

        this.catatan.set(catatan);
    }

    public StringProperty catatanProperty() {

        return catatan;
    }

    @Override
    public String toString() {
        return "CatatanKesehatan{" +
                "tanggal=" + tanggal.get() +
                ", beratBadan=" + beratBadan.get() +
                ", tekananDarah='" + tekananDarah.get() + '\'' +
                ", detakJantung=" + detakJantung.get() +
                ", jamTidur=" + jamTidur.get() +
                ", catatan='" + catatan.get() + '\'' +
                '}';
    }
}
