package View;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * @author Farraz Firdaus
 */
public class BantuanAlert {

    public static void tampilkanInfo(String judul, String pesan) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    public static void tampilkanError(String judul, String pesan) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    public static void tampilkanPeringatan(String judul, String pesan) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    public static boolean tampilkanKonfirmasi(String judul, String pesan) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);

        Optional<ButtonType> hasil = alert.showAndWait();
        return hasil.isPresent() && hasil.get() == ButtonType.OK;
    }
}
