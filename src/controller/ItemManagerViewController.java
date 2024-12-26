package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Item;

import java.io.IOException;

public class ItemManagerViewController {

    private final ItemController itemController = ItemController.getInstance();
    public TextField textFieldID;
    public TextField textFieldName;
    public TextField textFieldQuantity;
    public TextField textFieldDescription;
    public TextField textFieldPrice;
    private Stage viewItemStage;
    private ViewItemsViewController viewItemsViewController;


    public void initialize() {
        textFieldID.setText(itemController.getNextItemID());
    }
    private void updateItemID() {
        textFieldID.setText(itemController.getNextItemID());
    }

    public void btnViewItemAction(ActionEvent actionEvent) throws IOException {
        if (viewItemStage == null) {
            this.viewItemStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/view_item.fxml"));
            Parent root = loader.load();

            viewItemsViewController = (ViewItemsViewController) loader.getController();

            viewItemStage.setScene(new Scene(root));
        }

        viewItemStage.show();
    }



    public void btnAddItemAction(ActionEvent actionEvent) {
        Item newItem = new Item(
                textFieldID.getText(),
                textFieldName.getText(),
                Integer.parseInt(textFieldQuantity.getText()),
                Double.parseDouble(textFieldPrice.getText()),
                textFieldDescription.getText()
        );

        boolean isAdded = itemController.addItem(newItem);

        itemController.printItems();

        if (isAdded) {
            clearTextFields();
            updateItemID();

            if (viewItemsViewController != null) {
                viewItemsViewController.populateItemTable();
            }

            return;
        }


    }

    private void clearTextFields() {
        TextField[] allTextFields = {textFieldID, textFieldName, textFieldQuantity, textFieldPrice, textFieldDescription};
        for (TextField textfield : allTextFields) {
            textfield.clear();
        }
    }
}
