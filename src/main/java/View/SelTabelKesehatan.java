package View;

import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;

/**
 * @author Farraz Firdaus
 */
public class SelTabelKesehatan<S, T> extends TableCell<S, T> {

    @Override
    protected void updateItem(T item, boolean kosong) {
        super.updateItem(item, kosong);

        if (kosong || item == null) {
            setText(null);
            setStyle("");
        } else {
            setText(item.toString());

            if (item instanceof Double) {
                Double nilai = (Double) item;
                if (nilai < 50) {
                    setTextFill(Color.RED);
                } else if (nilai > 100) {
                    setTextFill(Color.ORANGE);
                } else {
                    setTextFill(Color.GREEN);
                }
            }
        }
    }
}
