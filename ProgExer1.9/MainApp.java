import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class MainApp{
    public static void main(String[] args)  throws IOException {
        Scanner in = new Scanner(System.in);

        FileReader fr = new FileReader("software.txt");
        Scanner inFile = new Scanner(fr);
        BST x = new BST();
        String softwareName = "";
        String version = "";
        int quantity;
        int price;
        String temp, temp2, temp3;
        int input = 0;

        while(inFile.hasNextLine()){
            if (softwareName.equals(""))
                softwareName = inFile.nextLine();
            temp = inFile.nextLine();
            temp2 = inFile.nextLine().trim();
            temp3 = inFile.nextLine().trim();
            try{
                version = temp;
                quantity = Integer.parseInt(temp2);
                price = Integer.parseInt(temp3);
                x.insert(softwareName, version, quantity, price);
                softwareName = "";
            } catch(Exception e){
                version = "-";
                quantity = Integer.parseInt(temp);
                price = Integer.parseInt(temp2);
                x.insert(softwareName, version, quantity, price);
            }
        }

        System.out.println("Software Store");
        while(input != 4){
            printMenu();
            try{
                input = in.nextInt();
                switch(input){
                    case 1: 
                        System.out.println("======================================================");
                        System.out.printf("%-25s %-10s %-10s %-5s \n", "Software Name", "Version", "Quantity", "Price");
                        x.breadthFirst();
                        System.out.println("======================================================");
                        break;
                    case 2:
                        try{
                            in.nextLine();
                            System.out.print("Software Name: "); 
                            softwareName = in.nextLine();
                            System.out.print("Software Version: ");
                            version = in.nextLine();
                            System.out.print("Quantity: ");
                            quantity = in.nextInt();
                            System.out.print("Price: ");
                            price = in.nextInt();
                            x.addStock(softwareName, version, quantity, price);
                        } catch (Exception e) {
                            System.err.println("Invalid input");
                        }
                        break;
                    case 3:
                        in.nextLine();
                        System.out.print("Software Name: "); 
                        softwareName = in.nextLine();
                        System.out.print("Software Version: ");
                            version = in.nextLine();
                        System.out.print("Quantity: ");
                        quantity = in.nextInt();
                        x.sellStock(softwareName, version, quantity);
                        break;
                    case 4:
                        x.save();
                        break;
                    default:
                        System.err.println("Invalid input");
                        break;
                }
                if (input == 4)
                    break;
            } catch(Exception e){
                System.err.println("Input Mismatch");
            }
        }
        in.close();
        inFile.close();
    }

    public static void printMenu(){
        System.out.println("[1] Show Software" );
        System.out.println("[2] Add Software" );
        System.out.println("[3] Sell Software" );
        System.out.println("[4] Quit" );
    }
}
