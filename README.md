# food_truck

To compile my project, I was not able to use the run bar in VS Code. Here are the steps to run my Food Truck:

1. In the terminal, 'cd' into my proper folder (C:\CSCE314_Java\JavaFXProject\src>)
2. Next, load in the java file with: javac --module-path ../lib --add-modules javafx.controls,javafx.fxml app/*.java
3. Finally, use this command to run it: java "-Djava.library.path=../lib/bin" --module-path ../lib --add-modules javafx.controls,javafx.fxml app.App
(Had to include "-Djava.library.path=../lib/bin" because it wasn't working otherwise)

Now you have loaded in my food truck.

Special Features:

Features of Admin GUI:
- Restocks all items, including 2 buttons for the grilled cheeses (32 of each)
- Will restock 32 into the 2 most popular drinks, while only restocking 16 into the other 2 (high demand = high product)
- States amount of each product sold AND the revenue for each product AND the total revenue aquired
- States the least popular drink (I didn't really understand what the prompt meant in "Assign that slot to the most popular drink, to increase its
availability" as this would leave 6 slots to just 3 drinks, so I instead posted a picture of the least popular drink while maximizing the capacity to
32 for the 2 most popular drinks) 
- The least popular drink feature will remain empty if there is a tie, so there is no bias towards any of the drinks
- Return button back to the food truck menu, where it stores the data of previous orders.

Most applied/useful files for this project:
(within src/app)
- controller.java, adminscreen.fxml, mainview.fxml, styles.css (front-end)

lib folder contains the jar files


Compile:
javac --module-path ../lib --add-modules javafx.controls,javafx.fxml app/*.java

Run:
java "-Djava.library.path=../lib/bin" --module-path ../lib --add-modules javafx.controls,javafx.fxml app.App
