import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException {

		ReadFile readData = new ReadFile();//Read file
		readData.readFile();
		int client 		= readData.getClient();
		int resources 	= readData.getResources();


		PrintWriter out = new PrintWriter(new FileWriter("output.txt")); 
		Scanner in = new Scanner(System.in); 

		int required[] = new int[resources];

		//ask request from each client and show whether its granted or not on console as well as output file
		for(int i = 0;i<client;i++){
			System.out.println("Enter the resources required by Client "+ (i+1));
			out.print("List of resources requested by Client ");
			out.print((i+1));
			out.print(" are:");
			for(int j =0;j<resources;j++){
				required[j] = 0;
				System.out.print("Resources of type "+ (j+1) + ":");
				required[j]  = in.nextInt();
				out.print(required[j]);
			}
			BankersAlgorithm b = new BankersAlgorithm();
			out.println();
			int returnValue = b.bankersAlgorithm(readData,required,i);
			if(returnValue == 1){
				out.print("Requested number of resources are more than available");
			}
			else if(returnValue == 2){
				out.print("Requested number of resources is more "
						+ "than maximum limit allocated for client");

			}
			else if(returnValue == 3){
				out.println("Its a safe state!!!");
				out.print("Now Available Resources:");
				int availableFinal[] = readData.getAvailable();
				for(int m = 0;m<resources;m++){
					out.print(availableFinal[m]);
				}
			}
			else if(returnValue == 4){
				out.print("Its an unsafe state!!!");
			}       	
			out.println("\n");

		}
		out.close();
	}
}
