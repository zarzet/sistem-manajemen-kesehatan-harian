import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Farraz Firdaus
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/health_view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 800, 700);

            primaryStage.setTitle("Sistem Manajemen Kesehatan Harian (Farraz Tugas 1)");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.setMinWidth(750);
            primaryStage.setMinHeight(650);

            primaryStage.show();

            System.out.println("Aplikasi Health Management System berhasil dijalankan!");

        } catch (Exception e) {
            System.err.println("Error saat memuat aplikasi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
