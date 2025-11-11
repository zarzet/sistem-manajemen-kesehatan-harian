package Controller;

import Model.CatatanKesehatan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

/**
 * @author Farraz Firdaus
 */
public class ControllerKesehatan {

    @FXML private DatePicker fieldTanggal;
    @FXML private TextField fieldBeratBadan;
    @FXML private TextField fieldTekananDarah;
    @FXML private TextField fieldDetakJantung;
    @FXML private Slider sliderJamTidur;
    @FXML private Label labelJamTidur;
    @FXML private TextArea fieldCatatan;

    @FXML private Button tombolTambah;
    @FXML private Button tombolUpdate;
    @FXML private Button tombolHapus;
    @FXML private Button tombolBersihkan;

    @FXML private TableView<CatatanKesehatan> tabelKesehatan;
    @FXML private TableColumn<CatatanKesehatan, LocalDate> kolomTanggal;
    @FXML private TableColumn<CatatanKesehatan, Double> kolomBeratBadan;
    @FXML private TableColumn<CatatanKesehatan, String> kolomTekananDarah;
    @FXML private TableColumn<CatatanKesehatan, Integer> kolomDetakJantung;
    @FXML private TableColumn<CatatanKesehatan, Double> kolomJamTidur;
    @FXML private TableColumn<CatatanKesehatan, String> kolomCatatan;

    @FXML private Label labelStatus;

    private ObservableList<CatatanKesehatan> daftarCatatanKesehatan;

    @FXML
    public void initialize() {
        daftarCatatanKesehatan = FXCollections.observableArrayList();

        kolomTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        kolomBeratBadan.setCellValueFactory(new PropertyValueFactory<>("beratBadan"));
        kolomTekananDarah.setCellValueFactory(new PropertyValueFactory<>("tekananDarah"));
        kolomDetakJantung.setCellValueFactory(new PropertyValueFactory<>("detakJantung"));
        kolomJamTidur.setCellValueFactory(new PropertyValueFactory<>("jamTidur"));
        kolomCatatan.setCellValueFactory(new PropertyValueFactory<>("catatan"));

        tabelKesehatan.setItems(daftarCatatanKesehatan);

        sliderJamTidur.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelJamTidur.setText(String.format("%.1f jam", newValue.doubleValue()));
        });

        tabelKesehatan.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    isiFormDenganDataTerpilih(newValue);
                }
            }
        );

        fieldTanggal.setValue(LocalDate.now());

        tambahDataContoh();

        updateStatus("Aplikasi siap digunakan. Total data: " + daftarCatatanKesehatan.size());
    }

    @FXML
    private void tanganiTambah() {
        try {
            if (!validasiInput()) {
                return;
            }

            CatatanKesehatan catatanBaru = new CatatanKesehatan(
                fieldTanggal.getValue(),
                Double.parseDouble(fieldBeratBadan.getText()),
                fieldTekananDarah.getText(),
                Integer.parseInt(fieldDetakJantung.getText()),
                sliderJamTidur.getValue(),
                fieldCatatan.getText()
            );

            daftarCatatanKesehatan.add(catatanBaru);

            bersihkanForm();

            updateStatus("Data berhasil ditambahkan! Total data: " + daftarCatatanKesehatan.size());

            tampilkanAlert(Alert.AlertType.INFORMATION, "Sukses", "Data kesehatan berhasil ditambahkan!");

        } catch (NumberFormatException e) {
            tampilkanAlert(Alert.AlertType.ERROR, "Error Input", "Format angka tidak valid!");
            updateStatus("Error: Format input tidak valid");
        } catch (Exception e) {
            tampilkanAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
            updateStatus("Error: " + e.getMessage());
        }
    }

    private void isiFormDenganDataTerpilih(CatatanKesehatan catatan) {
        fieldTanggal.setValue(catatan.getTanggal());
        fieldBeratBadan.setText(String.valueOf(catatan.getBeratBadan()));
        fieldTekananDarah.setText(catatan.getTekananDarah());
        fieldDetakJantung.setText(String.valueOf(catatan.getDetakJantung()));
        sliderJamTidur.setValue(catatan.getJamTidur());
        fieldCatatan.setText(catatan.getCatatan());

        updateStatus("Data dipilih: " + catatan.getTanggal());
    }

    @FXML
    private void tanganiUpdate() {
        CatatanKesehatan catatanTerpilih = tabelKesehatan.getSelectionModel().getSelectedItem();

        if (catatanTerpilih == null) {
            tampilkanAlert(Alert.AlertType.WARNING, "Tidak Ada Seleksi",
                     "Silakan pilih data yang ingin diupdate dari tabel!");
            updateStatus("Error: Tidak ada data yang dipilih");
            return;
        }

        try {
            if (!validasiInput()) {
                return;
            }

            catatanTerpilih.setTanggal(fieldTanggal.getValue());
            catatanTerpilih.setBeratBadan(Double.parseDouble(fieldBeratBadan.getText()));
            catatanTerpilih.setTekananDarah(fieldTekananDarah.getText());
            catatanTerpilih.setDetakJantung(Integer.parseInt(fieldDetakJantung.getText()));
            catatanTerpilih.setJamTidur(sliderJamTidur.getValue());
            catatanTerpilih.setCatatan(fieldCatatan.getText());

            tabelKesehatan.refresh();

            bersihkanForm();

            updateStatus("Data berhasil diupdate!");

            tampilkanAlert(Alert.AlertType.INFORMATION, "Sukses", "Data kesehatan berhasil diupdate!");

        } catch (NumberFormatException e) {
            tampilkanAlert(Alert.AlertType.ERROR, "Error Input", "Format angka tidak valid!");
            updateStatus("Error: Format input tidak valid");
        } catch (Exception e) {
            tampilkanAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
            updateStatus("Error: " + e.getMessage());
        }
    }

    @FXML
    private void tanganiHapus() {
        CatatanKesehatan catatanTerpilih = tabelKesehatan.getSelectionModel().getSelectedItem();

        if (catatanTerpilih == null) {
            tampilkanAlert(Alert.AlertType.WARNING, "Tidak Ada Seleksi",
                     "Silakan pilih data yang ingin dihapus dari tabel!");
            updateStatus("Error: Tidak ada data yang dipilih");
            return;
        }

        Alert alertKonfirmasi = new Alert(Alert.AlertType.CONFIRMATION);
        alertKonfirmasi.setTitle("Konfirmasi Hapus");
        alertKonfirmasi.setHeaderText("Hapus Data Kesehatan");
        alertKonfirmasi.setContentText("Apakah Anda yakin ingin menghapus data tanggal " +
                                   catatanTerpilih.getTanggal() + "?");

        alertKonfirmasi.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                daftarCatatanKesehatan.remove(catatanTerpilih);

                bersihkanForm();

                updateStatus("Data berhasil dihapus! Total data: " + daftarCatatanKesehatan.size());

                tampilkanAlert(Alert.AlertType.INFORMATION, "Sukses", "Data kesehatan berhasil dihapus!");
            }
        });
    }

    @FXML
    private void tanganiBersihkan() {
        bersihkanForm();
        updateStatus("Form dibersihkan");
    }

    private void bersihkanForm() {
        fieldTanggal.setValue(LocalDate.now());
        fieldBeratBadan.clear();
        fieldTekananDarah.clear();
        fieldDetakJantung.clear();
        sliderJamTidur.setValue(8.0);
        fieldCatatan.clear();
        tabelKesehatan.getSelectionModel().clearSelection();
    }

    private boolean validasiInput() {
        if (fieldTanggal.getValue() == null) {
            tampilkanAlert(Alert.AlertType.WARNING, "Input Tidak Lengkap", "Silakan pilih tanggal!");
            return false;
        }

        if (fieldBeratBadan.getText().trim().isEmpty()) {
            tampilkanAlert(Alert.AlertType.WARNING, "Input Tidak Lengkap", "Silakan masukkan berat badan!");
            return false;
        }

        try {
            double beratBadan = Double.parseDouble(fieldBeratBadan.getText());
            if (beratBadan <= 0 || beratBadan > 300) {
                tampilkanAlert(Alert.AlertType.WARNING, "Input Tidak Valid",
                         "Berat badan harus antara 0-300 kg!");
                return false;
            }
        } catch (NumberFormatException e) {
            tampilkanAlert(Alert.AlertType.WARNING, "Input Tidak Valid",
                     "Berat badan harus berupa angka!");
            return false;
        }

        if (fieldTekananDarah.getText().trim().isEmpty()) {
            tampilkanAlert(Alert.AlertType.WARNING, "Input Tidak Lengkap",
                     "Silakan masukkan tekanan darah!");
            return false;
        }

        if (fieldDetakJantung.getText().trim().isEmpty()) {
            tampilkanAlert(Alert.AlertType.WARNING, "Input Tidak Lengkap",
                     "Silakan masukkan detak jantung!");
            return false;
        }

        try {
            int detakJantung = Integer.parseInt(fieldDetakJantung.getText());
            if (detakJantung <= 0 || detakJantung > 250) {
                tampilkanAlert(Alert.AlertType.WARNING, "Input Tidak Valid",
                         "Detak jantung harus antara 0-250 bpm!");
                return false;
            }
        } catch (NumberFormatException e) {
            tampilkanAlert(Alert.AlertType.WARNING, "Input Tidak Valid",
                     "Detak jantung harus berupa angka bulat!");
            return false;
        }

        return true;
    }

    private void tampilkanAlert(Alert.AlertType tipe, String judul, String pesan) {
        Alert alert = new Alert(tipe);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    private void updateStatus(String pesan) {
        labelStatus.setText("Status: " + pesan);
    }

    private void tambahDataContoh() {
        daftarCatatanKesehatan.add(new CatatanKesehatan(
            LocalDate.now().minusDays(2),
            68.5,
            "120/80",
            72,
            8.0,
            "Kondisi sehat, olahraga pagi"
        ));

        daftarCatatanKesehatan.add(new CatatanKesehatan(
            LocalDate.now().minusDays(1),
            68.2,
            "118/78",
            70,
            7.5,
            "Sedikit kelelahan"
        ));

        daftarCatatanKesehatan.add(new CatatanKesehatan(
            LocalDate.now(),
            68.0,
            "122/82",
            75,
            6.5,
            "Kurang tidur karena begadang"
        ));
    }
}
