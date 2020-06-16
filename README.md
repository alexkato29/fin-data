# Financial Data Management Group

## Objective
The purpose of this project was to automate the manual data formatting our mentor does everyday when importing and exporting information from one financial software to another. Our product, is essentially a "middle-man" that process the data between the software our mentor Uses
## Installation
To use the software, clone the git repository. Only the fin-data.zip is necessary. Extract the fin-data.zip file to a directory on your comptuer (Desktop for example). Double click the .jar file in order to start the application.
Note: The *data* and *styles* folderas well as the .jar file must stay in the same directory. The application will crash otherwise.

## Design Explanation

#### Dependencies
The application is dependent on two I/O libraries
- JSON.Simple
    - Documentaion: http://alex-public-doc.s3.amazonaws.com/json_simple-1.1/index.html
    - Download: https://code.google.com/archive/p/json-simple/downloads
- Commons Apache CSV
    - Documentaion: https://commons.apache.org/proper/commons-csv/
    - Download: https://commons.apache.org/proper/commons-csv/download_csv.cgi
It is also dependent on a GUI library
- JavaFX
     - Documentation: https://openjfx.io/
     - Download: https://gluonhq.com/products/javafx/

#### Objects
So we have 4 main objects
- Database 
- Portfolio
- Security
- Trade

The Database class cotnatins several portfolios held in a hashmap. The portfolio class contains several securities also held in a hashmap. The Security class contains fields that describe itself in the portfolio (Ticker, price, quantity, etc.)

The trade class is related to the security class only. Just like the Security class, it contains fields, such as *accountNum* and *accountHolder*. However, the trade class is used to represent the changes in quantity and price of a security. It is used to update the secuitie in a particular portfolio.

#### Input and Output
Our program requires input and output of files. Our file input logic is handled in the *readData.java* class. This is file contains two important methods, *tradeCSVParse(String FilePath)* and *portfolioCSVParse(FilePath)*. These two methods are called on when the user is attempting to update the database through either trade changes or portfolio changes for csv Files.

The only instance of JSON input parsing is when the application starts and the user is prompted to upload a JSON Database File. The logic for uploading and reating a database lies in the *Database.java* class constructor.

The program also needs to reformat the data into viable CSV and JSON files for other softwares to use. The trade logs and portfolio csv files that are exported is handled by the *TableViewController.java* and the *exportTrades.java*.

JSON output is handeled when the window is closed by the user (the 'x' button on the top right of the window). Upon closure of the window, the application will create a new JSON Database file in to the *data* directory in the fin-data folder. The logic for this is located in the overridden *stop()* method.
#### Scenes and Controllers
The Application uses XML to style the *Scenes* (aka the front end). The FXML controllers are used to listen for fired events (click of a button). The Styles Directory holds the XML styles for the scenes and the Java classes *LandingController* and *tableViewController* are essentially event listeners for button clicks.

## Pitch Video and Technical Presentation

#### Pitch: https://youtu.be/GyEqmVnEHn0?list=PLPCVXjRy-oy48czE1RkAbNW2P38lCuwSl
#### Presentation: https://youtu.be/O5NKWTysM3A?list=PLPCVXjRy-oy48czE1RkAbNW2P38lCuwSl
