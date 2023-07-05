/*
 *******************************************************
 *      P R O G R A M    B A T T L E S H I P
 *    Author     : Kwong Chun him
 *    Date       : 2019-10-19
 *    Class      : IT114105/1A
 *    Student ID : 190323163
 *    Program    : BATTLESHIP
 *    Description: Battleship is played on four grids, two for each
 *                 player. The grids are typically square – usually
 *                 10×10 – and the individual squares in the grid
 *                 are identified by letter and number. On one grid
 *                 the player arranges ships and records the shots
 *                 by the opponent. On the other grid, the player
 *                 records their own shots.
 *                 The game battleship provides two modes to the player  HUMAN and CPU.  
 *                 About the HUMAN Mode is setting the ship by the user, you can play with other one. 
 *                 There are five ships that have to set. The First ship you need to set up name Carrier, Size of the ship is five. 
 *                 The second ship name Battleship, Size of the ship is 4. The third ship name Destroyer, Size of the ship is 3. 
 *                 The fourth ship name Submarine, Size of the ship is 3. The last ship name Patrol Boat, Size of the ship is 2. 
 *                 About the CPU mode is setting all the ship randomly. 
 *                 After setting, the user had to shot all of the ships on the grid or input 'x', Then that game will be end.      
 *    Input      : user or cpu,horizontal or vertical,the coordinate of ship hide on girds, The coordinate of missile attack on girds
 *    Output     : the ship inside the grid, the player records their own shots inside the grid 
 *******************************************************
 */
import java.util.*;

public class Battleship{
    static Scanner input = new Scanner(System.in);
    //variable directory
    static int [][] ship = new int [10][10];             //10x10 array of ship ,used to print the grid
    static int[][]Playerattack = new int [10][10];      //10x10 array of Play2 , used to print the grid
    static final int xlength = 9;                       //range of x, the range on x is 0-9
    static final int ylength = 9;                       //range of y , the range on y is 0-9          
    static int player = 1 ;                             //change the mode to shot the ship
    static String shiptype;                             //varibale of shiptype 
    static int [] XandY = new int [10];                 //return the X and Y on other methods
    static int shipsize = 0;                                //variable of ship size
    static int gamemoderesult = 0;                      //the result of gamemode        
    static int hit = 0;                                 //time of hit the ship
    static int Launched = 0;                            //how many time does player has been Launched

    //main methods of battleship
    public static void main( String[] args ) {                
        setmap();           //set all the map == 0, 0 = 'o';

        Gamemode();          //select the game mode , Human or CPU

        if (gamemoderesult == 0){
            Human();                        //run for the Human mode methods
        }
        else{
            CPU();                          //run for the CPU mode methods
        }
        //change to the shoting mode
        player++;                        
        //set the array Playerattack to be 0,all the map == 0, 0 = 'o';
        setmap();
        //run the shoting mode methods
        Player2();
    }    
    //gamemode of Human method, user have been input the ship by yourself
    private static void Human(){
        map();                           //print the grid first
        
        shiptype = "Carrier";                //set the shiptype
        shipsize = 5;                        //set the shipt size    
        Inputship();                         //get the input of the ship

        shiptype = "Battleship";             //set the shiptype
        shipsize = 4;                        //set the shipt size    
        Inputship();                         //get the input of the ship

        shiptype = "Destroyer";              //set the shiptype
        shipsize = 3;                        //set the shipt size    
        Inputship();                         //get the input of the ship

        shiptype = "Submarine";              //set the shiptype
        shipsize = 3;                        //set the shipt size 
        Inputship();                         //get the input of the ship

        shiptype = "Patrol Boat";            //set the shiptype
        shipsize = 2;                        //set the shipt size 
        Inputship();                         //get the input of the ship

        CleanScreen();                       //CleanScreen 
    }    
    //random a number for x and y on the map, and print the output to user
    private static void CPU(){
        shipsize = 5;                        //set the shipt size  
        RandomShip();                        //set the ship randomly

        shipsize = 4;
        RandomShip();

        shipsize = 3;
        RandomShip();

        shipsize = 3;
        RandomShip();

        shipsize = 2;
        RandomShip();     

        CleanScreen();
    }

    //for player input the location to hit the ship and show you missed or hit
    private static void Player2(){
        map();
        do{ 
            Launched++; 
            attack();  
            map();
        }while(hit < 17);
        System.out.println("You have hit all battleships! ");
    }

    private static int[][] attack(){ 
        getxy();        
        int x = XandY[0];
        int y = XandY[1];
        String HitOrNot = "";
        while(Playerattack[y][x] != 0){
            System.out.println("You have already fired this area."); 
            getxy();                    
            x = XandY[0];
            y = XandY[1];
        }

        if(ship[y][x] == 1){
            hit++;
            Playerattack[y][x] = 1;   
            HitOrNot = "It's a hit!! Launched:";
        }   

        if(ship[y][x] == 0){
            Playerattack[y][x] = 2;
            HitOrNot = "Missed. Launched:";
        }        

        System.out.println(HitOrNot + Launched +", Hit: "+hit);
        return Playerattack;
    }

    //get the gamemode.playing with player or CPU
    private static void Gamemode(){
        System.out.print("Battleship Palyer1 (0 - Human, 1 - CPU, x - Exit):");        
        String UserInput = input.nextLine();        
        char gamemode = UserInput.charAt(0); 
        while((gamemode != '1' && gamemode != '0')  || UserInput.length() != 1){              
            System.out.print("Please input 0 or 1, x - Exit : "); 
            UserInput = input.nextLine();   
            xExit(UserInput);
            gamemode = UserInput.charAt(0); 
        }              
        gamemoderesult = Character.getNumericValue(gamemode);

    }
    //use to choosing gamemode or Orientation
    private static int choosing(String input){
        int output = 0;

        xExit(input);
        if(input.equals("0"))
            output = 0;
        if (input.equals("1"))
            output = 1;

        return output;        
    }    

    //print the 10x10 array on map 
    private static void map(){
        String hl = "";        

        for(int num = 0; num <= xlength ; num++){
            if(num  == 0){
                System.out.print("   ");  
            }
            System.out.print(" "+num);
            if(num == 9 )
                System.out.print(" <--X");
        }
        for(int index = 0; index <= xlength; index ++){
            hl += "--";
        }
        System.out.println("\n--+"+hl);
        for(int row = 0; row <= ylength ; row ++){
            System.out.print(row+ " |");
            for(int col = 0 ; col <= xlength; col++){
                if(player == 1){
                    if (ship[row][col] == 1)
                        System.out.print(" S");

                    if (ship[row][col] == 0)
                        System.out.print(" .");
                }
                if(player ==2){
                    if(Playerattack[row][col] == 0)                                            
                        System.out.print(" .");                        
                    else if(Playerattack[row][col] == 1)                    
                        System.out.print(" #");                        
                    else if(Playerattack[row][col] == 2)
                        System.out.print(" o");                                                
                }                       
            }
            if(row == 9){            
                System.out.println("");
                System.out.println("^");
                System.out.print("Y");                
            }
            System.out.println("");
        }
    }

    //get the variable of x and y
    private static int[] getxy(){        
        String userinput;                    //variable of input            
        //get the intput
        if(player == 1){
            System.out.print("Position of " +shiptype+ " [XY], x - Exit : ");   
        }
        if(player == 2){
            System.out.print("Set your missile [XY], x - Exit: ");
        }
        userinput = input.nextLine();         
        xExit(userinput); 
        //the input just allow in two number
        while (!(userinput.matches("\\d{2}")) ){
            System.out.print("Error in [XY]! Please input again, x - Exit: ");
            userinput = input.nextLine(); 
            //if userinput is x, program will be exit    
            xExit(userinput);                     
        }

        char x1 = userinput.charAt(0);                //first number of userinput ,this is x
        char y1 = userinput.charAt(1);                //second number of userinput, this is y
        XandY[0] = Character.getNumericValue(x1);     //save the x location
        XandY[1] = Character.getNumericValue(y1);     //save the y location       
        return XandY;
    }

    //check the location will not over the boundary
    private static void CheckRange(int orien){
        int testX = 0;
        int testY = 0;
        do{
            if(gamemoderesult == 0){          
                getxy();
            }
            if(gamemoderesult == 1){
                XandY[0] = (int)(Math.random()*10);          //the random number of x
                XandY[1] = (int)(Math.random()*10);          //the random number of y
            }                                    

            if(orien == 0 )
                testX = XandY[0] + shipsize - 1;
            if(orien == 1 )
                testY = XandY[1] + shipsize - 1;  

            if(testX > xlength || testY > ylength){
                if(gamemoderesult == 0)
                    System.out.println("The ships cannot be over the boundary!");               
            }    

        }while(testX > xlength || testY > ylength);
    }

    //check the location is null       
    private static void Checkcoverlap(int orien){            
        int testX = 0;
        int testY = 0;
        do{                                        
            CheckRange(orien);            
            testX = XandY[0];
            testY = XandY[1];

            for(int addship = 0; addship < shipsize ; addship++){                                                                      
                if(ship[testY][testX] == 1){
                    break;
                }  
                if(orien == 0 )
                    testX = XandY[0] + addship;

                if(orien == 1 )
                    testY = XandY[1] + addship; 
            }                                              
            if(ship[testY][testX] == 1){
                if(gamemoderesult == 0){
                    System.out.println("The ships cannot overlap");
                } 
            }
        } while(ship[testY][testX] == 1 );                 

    }
    //add the ship on map
    private static void Setship(int orien){
        //change the number on map to be 1
        for(int addship = 0; addship < shipsize; addship++){
            ship[XandY[1]][XandY[0]] = 1;       //the ship on map will be 1                  
            //check the ship is horizontal or vertical
            if(orien == 0 )
                XandY[0]++;
            if(orien == 1 )
                XandY[1]++;                                         
        }
    }

    /*get the input orientation , x and y from user to add the ship. Also, it will check is it 
    the input are English, is it x and y between 0-9, is it the location have been used */
    private static void Inputship(){                                
        String userinput = "" ;                                                         

        System.out.println("Player1: Set the ship: "+shiptype+", ship size: "+shipsize);

        int orien = Orientation();                        

        Checkcoverlap(orien);    

        Setship(orien);

        map();          //print the output

    }
    //get the input of the ship is horizontal or  vertical
    //if userinput orientation is 0, the ship will be horizontal and 
    //if userinput orientation is 1,the ship will be vertical,
    private static int Orientation(){        
        System.out.print("Orientation (0 - horizontal, 1 - vertical), x - Exit: ");               
        String UserInput = input.nextLine();   
        xExit(UserInput);
        char orientation = UserInput.charAt(0);
        //check user input just allow 0,1,x
        while((orientation != '1' && orientation != '0')  || UserInput.length() != 1){              
            System.out.print("Please input 0 or 1, x - Exit : "); 
            UserInput = input.nextLine();   
            xExit(UserInput);
            orientation = UserInput.charAt(0); 
        }        
        int result = Character.getNumericValue(orientation);        //transform the userinput to number   

        return result;           //return it is 0 or 1
    }

    //random the ship in the ship array
    private static void RandomShip(){        

        int Orientation = (int)(Math.random()*2);
        Checkcoverlap(Orientation);
        Setship(Orientation);  

    }
    //clean the Screen
    private static void CleanScreen(){
        System.out.println("Press Enter for Player2 to start ...");
        String scanner = input.nextLine();
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
    //default the Map is 0 
    private static void setmap(){
        for(int y = 0;y < 10; y++){
            for(int x = 0; x <10; x++){
                if(player == 1)
                    ship[y][x] = 0;

                if(player == 2)
                    Playerattack[y][x] = 0;
            }
        }
    }
    //the exit fuction if input x = exit
    private static void xExit(String x){
        if (x.length() == 1 && x.charAt(0)=='x'){
            System.exit(0);
        }
    } 
}   
