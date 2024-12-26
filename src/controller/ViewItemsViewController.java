package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.util.Arrays;

public class ViewItemsViewController {
    public TableView<Item> tableViewItems;
    public TableColumn<Object, Object> colID;
    public TableColumn<Object, Object> colName;
    public TableColumn<Object, Object> colQuantity;
    public TableColumn<Object, Object> colPrice;
    public TableColumn<Object, Object> colDescription;

    public void initialize() {
        populateItemTable();
    }
    public void btnDeleteItemAction(ActionEvent actionEvent) {
        Item selectedItem = tableViewItems.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            System.out.println("Selected item");
            if (ItemController.getInstance().removeItem(selectedItem) != null) {
                System.out.println("Removed item");

            }
            populateItemTable();
        }
    }

    public void populateItemTable () {
        tableViewItems.getItems().clear();

        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        if (ItemController.getInstance().getItems() != null) {

            itemObservableList.addAll(Arrays.asList(ItemController.getInstance().getItems()));
        }

        tableViewItems.setItems(itemObservableList);


    }

    public void btnIncreaseQty(ActionEvent actionEvent) {
        Item selectedItem = tableViewItems.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            ItemController.getInstance().increaseQuantity(selectedItem);
            populateItemTable();
        }

    }

    public void btnDecreaseQty(ActionEvent actionEvent) {
        Item selectedItem = tableViewItems.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            ItemController.getInstance().decreaseQuantity(selectedItem);
            populateItemTable();
        }
    }
}
