package app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;   


public class Controller {
    //total amount
    int counter = 0;
    //stock of items
    static int gc = 32;
    static int gc2 = 32;
    
    static int coke = 16;
    static int rb = 16;
    static int sprite = 16;
    static int water = 16;
    //“in‐this‐sale” usage
    private int currentGc = 0;
    private int currentCoke = 0;
    private int currentRb = 0;
    private int currentSprite = 0;
    private int currentWater = 0;
    //total amount bought
    static int gcBought = 0;
    static int cokeBought = 0;
    static int rbBought = 0;
    static int spriteBought = 0;
    static int waterBought = 0;
    //revenue
    static int gcrevenue = 0;
    static int cokerevenue = 0;
    static int rbrevenue = 0;
    static int spriterevenue = 0;
    static int waterrevenue = 0;
    //total revenue
    static int totalRevenue = 0;
    //popular image key:
    private String popular1Key;
    private String popular2Key;

    private String gcKey;

    //user IF
    private void refreshView() {
        //total
        welcomeText.setText("Total: $"+counter);
        //labels/headers
        if (gcLabel  != null) gcLabel .setText(String.valueOf(gc));
        if (gcLabel2 != null) gcLabel2.setText(String.valueOf(gc2));

        cokeLabel.setText(String.valueOf(coke - currentCoke));
        rbLabel.setText(String.valueOf(rb - currentRb));
        spriteLabel.setText(String.valueOf(sprite - currentSprite));
        waterLabel.setText(String.valueOf(water - currentWater));
    }

    //out of stock
    private void outOfStock(String itemName) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText("Out of Stock");
        alert.setContentText("There are no more " + itemName + " left!");
        alert.showAndWait();
    }

    @FXML
    private Label welcomeText;
    @FXML
    private Label gcLabel;
    @FXML
    private Label gcLabel2;
    @FXML
    private Label cokeLabel;
    @FXML
    private Label rbLabel;
    @FXML
    private Label spriteLabel;
    @FXML
    private Label waterLabel;

    @FXML private javafx.scene.text.Text soldGcLabel;
    @FXML private javafx.scene.text.Text soldCokeLabel;
    @FXML private javafx.scene.text.Text soldRbLabel;
    @FXML private javafx.scene.text.Text soldSpriteLabel;
    @FXML private javafx.scene.text.Text soldWaterLabel;

    @FXML private javafx.scene.text.Text revenueGc;
    @FXML private javafx.scene.text.Text revenueCoke;
    @FXML private javafx.scene.text.Text revenueRb;
    @FXML private javafx.scene.text.Text revenueSprite;
    @FXML private javafx.scene.text.Text revenueWater;

    @FXML private javafx.scene.text.Text totalRevenueLabel;

    @FXML private ImageView popular1;
    @FXML private ImageView popular2;
    @FXML private ImageView leastPopular;

    @FXML
    private void initialize() {
        //Mainview
        if (welcomeText != null) {
            refreshView();
        }
    
        //AdminScreen
        if (soldGcLabel != null) {
            soldGcLabel.setText("Total GC Sold: " + gcBought);
            soldCokeLabel.setText("Total Coke Sold: " + cokeBought);
            soldRbLabel.setText("Total RB Sold: " + rbBought);
            soldSpriteLabel.setText("Total Sprite Sold: " + spriteBought);
            soldWaterLabel.setText("Total Water Sold: " + waterBought);
        }
        if (revenueGc != null) {
            revenueGc.setText("Revenue: $" + (gcBought * 3));
            revenueCoke.setText("Revenue: $" + (cokeBought * 2));
            revenueRb.setText("Revenue: $" + (rbBought * 2));
            revenueSprite.setText("Revenue: $" + (spriteBought * 2));
            revenueWater.setText("Revenue: $" + (waterBought * 2));
        }
        if (totalRevenueLabel != null) {
            totalRevenueLabel.setText("Total Revenue: $" + ((gcBought * 3) + (cokeBought * 2) + (rbBought * 2) + (spriteBought * 2) + (waterBought * 2)));
        }

        //popular items (image) (No Button due to auto reassignment)
        if (popular1 != null) {
            if(cokeBought == 0 && rbBought == 0 && spriteBought == 0 && waterBought == 0) {
                popular1.setImage(new javafx.scene.image.Image("/images/white.jpg")); 
            } 
            if (cokeBought >= rbBought && cokeBought >= spriteBought && cokeBought >= waterBought) {
                popular1.setImage(new javafx.scene.image.Image("/images/coke.jpg")); 
                popular1Key = "coke";
            } 
            else if (rbBought >= cokeBought && rbBought >= spriteBought && rbBought >= waterBought) {
                popular1.setImage(new javafx.scene.image.Image("/images/root_beer.jpg")); 
                popular1Key = "rb";
            } 
            else if (spriteBought >= cokeBought && spriteBought >= rbBought && spriteBought >= waterBought) {
                popular1.setImage(new javafx.scene.image.Image("/images/sprite.png")); 
                popular1Key = "sprite";
            } 
            else if (waterBought >= cokeBought && waterBought >= rbBought && waterBought >= spriteBought) {
                popular1.setImage(new javafx.scene.image.Image("/images/water.png")); 
                popular1Key = "water";
            }
            //set to white if no drinks
            if(cokeBought == 0 && rbBought == 0 && spriteBought == 0 && waterBought == 0) {
                popular1.setImage(new javafx.scene.image.Image("/images/white.jpg")); 
            } 
        }

        if (popular2 != null) {
            if(popular1Key.equals("coke")){
                if(cokeBought == 0 && rbBought == 0 && spriteBought == 0 && waterBought == 0) {
                    popular2.setImage(new javafx.scene.image.Image("/images/white.jpg")); 
                } 
                if(rbBought >= spriteBought && rbBought >= waterBought) {
                    popular2.setImage(new javafx.scene.image.Image("/images/root_beer.jpg")); 
                    popular2Key = "rb";
                } 
                else if (spriteBought >= rbBought && spriteBought >= waterBought) {
                    popular2.setImage(new javafx.scene.image.Image("/images/sprite.png")); 
                    popular2Key = "sprite";
                } 
                else{
                    popular2.setImage(new javafx.scene.image.Image("/images/water.png")); 
                    popular2Key = "water";
                }
            }
            else if(popular1Key.equals("rb")){
                if(cokeBought >= spriteBought && cokeBought >= waterBought) {
                    popular2.setImage(new javafx.scene.image.Image("/images/coke.jpg")); 
                    popular2Key = "coke";
                } 
                else if (spriteBought >= cokeBought && spriteBought >= waterBought) {
                    popular2.setImage(new javafx.scene.image.Image("/images/sprite.png")); 
                    popular2Key = "sprite";
                } 
                else{
                    popular2.setImage(new javafx.scene.image.Image("/images/water.png")); 
                    popular2Key = "water";
                }
            }
            else if(popular1Key.equals("sprite")){
                if(cokeBought >= rbBought && cokeBought >= waterBought) {
                    popular2.setImage(new javafx.scene.image.Image("/images/coke.jpg")); 
                    popular2Key = "coke";
                } 
                else if (rbBought >= cokeBought && rbBought >= waterBought) {
                    popular2.setImage(new javafx.scene.image.Image("/images/root_beer.jpg"));
                    popular2Key = "rb"; 
                } 
                else{
                    popular2.setImage(new javafx.scene.image.Image("/images/water.png")); 
                    popular2Key = "water";
                }
            }
            else if(popular1Key.equals("water")){
                if(cokeBought >= rbBought && cokeBought >= spriteBought) {
                    popular2.setImage(new javafx.scene.image.Image("/images/coke.jpg")); 
                    popular2Key = "coke";
                } 
                else if (rbBought >= cokeBought && rbBought >= spriteBought) {
                    popular2.setImage(new javafx.scene.image.Image("/images/root_beer.jpg")); 
                    popular2Key = "rb";
                } 
                else{
                    popular2.setImage(new javafx.scene.image.Image("/images/sprite.png"));
                    popular2Key = "sprite"; 
                }
            }
            //set to white
            if(cokeBought == 0 && rbBought == 0 && spriteBought == 0 && waterBought == 0) {
                popular2.setImage(new javafx.scene.image.Image("/images/white.jpg")); 
                popular2Key = "none";
            }
            else if(cokeBought == 0 && rbBought == 0 && spriteBought == 0) { //3 out of 4 are 0
                popular2.setImage(new javafx.scene.image.Image("/images/white.jpg"));
                popular2Key = "none"; 
            }
            else if(cokeBought == 0 && rbBought == 0 && waterBought == 0) { 
                popular2.setImage(new javafx.scene.image.Image("/images/white.jpg"));
                popular2Key = "none"; 
            }
            else if(cokeBought == 0 && spriteBought == 0 && waterBought == 0) {
                popular2.setImage(new javafx.scene.image.Image("/images/white.jpg")); 
                popular2Key = "none";
            }
            else if(rbBought == 0 && spriteBought == 0 && waterBought == 0) { 
                popular2.setImage(new javafx.scene.image.Image("/images/white.jpg")); 
                popular2Key = "none";
            }
        }

        if (leastPopular != null) {
            if (waterBought < rbBought && waterBought < spriteBought && waterBought < cokeBought) {
                leastPopular.setImage(new javafx.scene.image.Image("/images/water.png")); 
            } 
            else if (rbBought < cokeBought && rbBought < spriteBought && rbBought < waterBought) {
                leastPopular.setImage(new javafx.scene.image.Image("/images/root_beer.jpg")); 
            } 
            else if (spriteBought < cokeBought && spriteBought < rbBought && spriteBought < waterBought) {
                leastPopular.setImage(new javafx.scene.image.Image("/images/sprite.png")); 
            } 
            else if (cokeBought < waterBought && cokeBought < rbBought && cokeBought < spriteBought) {
                leastPopular.setImage(new javafx.scene.image.Image("/images/water.png")); 
            }
            else {
                leastPopular.setImage(new javafx.scene.image.Image("/images/white.jpg"));  //in case of tie, set to white
            } 
        }
    }

    @FXML
    protected void GCButtonClick(){
        if(gc == 0 && gc2 == 0) {
            outOfStock("Grilled Cheese's");
        }
        else{
            counter += 3;
            welcomeText.setText("Total: $"+counter);


            if(gc >= gc2 && gc > 0){
                gc--;
            } 
            else{
                gc2--;
            }

            // update 
            gcLabel.setText(String.valueOf(gc));
            gcLabel2.setText(String.valueOf(gc2));

            gcBought++;
        }
    }
    @FXML
    protected void CokeButtonClick(){
        if(currentCoke < coke){
            counter += 2;
            currentCoke++;
            refreshView();
        } 
        else{
            outOfStock("Coke's");
        }
    }
    @FXML
    protected void RBButtonClick(){
        if(currentRb < rb){
            counter += 2;
            currentRb++;
            refreshView();
        } 
        else{
            outOfStock("Root Beer's");
        }
    }
    @FXML
    protected void SpriteButtonClick(){
        if(currentSprite < sprite){
            counter += 2;
            currentSprite++;
            refreshView();
        } 
        else{
            outOfStock("Sprite's");
        }
    }
    @FXML
    protected void WaterButtonClick(){
        if(currentWater < water){
            counter += 2;
            currentWater++;
            refreshView();
        } 
        else{
            outOfStock("Water's");
        }
    }
    @FXML
    protected void ResetButtonClick(){
        counter = 0;
        currentGc = currentCoke = currentRb = currentSprite = currentWater = 0;
        refreshView();
    }
    @FXML
    protected void PurchaseButtonClick(){
        if(gcKey == "1"){
            gc -= currentGc;
        }else{
            gc2 -= currentGc;
        }

        coke -= currentCoke;
        rb -= currentRb;
        sprite -= currentSprite;
        water -= currentWater;

        gcBought += currentGc;
        cokeBought += currentCoke;
        rbBought += currentRb;
        spriteBought += currentSprite;
        waterBought += currentWater;

        if (revenueGc!=null) revenueGc.setText("Revenue: $" + (gcBought * 3));
        if (revenueCoke!=null) revenueCoke.setText("Revenue: $" + (cokeBought * 2));
        if (revenueRb!=null) revenueRb.setText("Revenue: $" + (rbBought * 2));
        if (revenueSprite!=null) revenueSprite.setText("Revenue: $" + (spriteBought * 2));
        if (revenueWater!=null) revenueWater.setText("Revenue: $" + (waterBought * 2));

        if (soldGcLabel != null)  soldGcLabel.setText("Sold GC: " + gcBought);
        if (soldCokeLabel != null) soldCokeLabel.setText("Sold Coke: " + cokeBought);
        if (soldRbLabel != null) soldRbLabel.setText("Sold RB: " + rbBought);
        if (soldSpriteLabel != null) soldSpriteLabel.setText("Sold Sprite: " + spriteBought);
        if (soldWaterLabel != null) soldWaterLabel.setText("Sold Water: " + waterBought);

        if (totalRevenueLabel != null) totalRevenueLabel.setText("Total Revenue: $" + ((gcBought * 3) + (cokeBought * 2) + (rbBought * 2) + (spriteBought * 2) + (waterBought * 2)));

        //then clear sale
        counter = 0;
        currentGc = currentCoke = currentRb = currentSprite = currentWater = 0;
        refreshView();
    }
    @FXML
    protected void AdminButtonClick(ActionEvent event){
        try{
            Parent secondRoot = FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(secondRoot));
            stage.show();

        } 
        catch (IOException e){
            e.printStackTrace();
        }
    }
    //admin screen fxml code (didnt create new controller file)
    @FXML
    protected void ReturnButtonClick(ActionEvent event){
        try{
            Parent secondRoot = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(secondRoot));
            stage.show();

        } 
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void RestockGcButton(){
        gc=32;
        refreshView(); 
    }
    @FXML void RestockGc2Button(){
        gc2=32;
        refreshView(); 
    }
    @FXML
    protected void RestockCokeButton(){
        List<Integer> counts = new ArrayList<>(List.of(gcBought, cokeBought, rbBought, spriteBought, waterBought)); //array ist
        counts.sort(Comparator.reverseOrder());
        int secondHighest = counts.get(1);

        boolean isTopTwo = cokeBought >= secondHighest;

        //restock - if top 2 = 32, else 16
        coke = isTopTwo ? 32 : 16;

        if(welcomeText != null) {
            refreshView();
        }
    }
    @FXML
    protected void RestockRbButton(){
        List<Integer> counts = new ArrayList<>(List.of(gcBought, cokeBought, rbBought, spriteBought, waterBought)); //array ist
        counts.sort(Comparator.reverseOrder());
        int secondHighest = counts.get(1);

        boolean isTopTwo = rbBought >= secondHighest;

        //restock - if top 2 = 32, else 16
        rb = isTopTwo ? 32 : 16;

        if(welcomeText != null) {
            refreshView();
        }
    }
    @FXML
    protected void RestockSpriteButton(){
        List<Integer> counts = new ArrayList<>(List.of(gcBought, cokeBought, rbBought, spriteBought, waterBought)); //array ist
        counts.sort(Comparator.reverseOrder());
        int secondHighest = counts.get(1);

        boolean isTopTwo = spriteBought >= secondHighest;

        //restock - if top 2 = 32, else 16
        sprite = isTopTwo ? 32 : 16;

        if(welcomeText != null) {
            refreshView();
        }
    }
    @FXML
    protected void RestockWaterButton(){
        List<Integer> counts = new ArrayList<>(List.of(gcBought, cokeBought, rbBought, spriteBought, waterBought)); //array ist
        counts.sort(Comparator.reverseOrder());
        int secondHighest = counts.get(1);

        boolean isTopTwo = waterBought >= secondHighest;

        //restock - if top 2 = 32, else 16
        water = isTopTwo ? 32 : 16;

        if(welcomeText != null) {
            refreshView();
        }
    }

}