package View;

import javafx.scene.control.TextField;

/**
 * @author Farraz Firdaus
 */
public class ValidatorInput {

    public static boolean isAngka(String teks) {
        if (teks == null || teks.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(teks);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isAngkaBulat(String teks) {
        if (teks == null || teks.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(teks);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDalamRentang(String teks, double min, double maks) {
        if (!isAngka(teks)) {
            return false;
        }
        double nilai = Double.parseDouble(teks);
        return nilai >= min && nilai <= maks;
    }

    public static void tandaiError(TextField field) {
        field.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
    }

    public static void hapusTanda(TextField field) {
        field.setStyle("");
    }
}
