/*Bankers Algorithm Simulation: Considering the Resource Assigned matrix, Resources still to be assigned matrix,total resources,
 * total clients,and total existing resources matrix is given in an input.txt file.The input file is read in the class ReadFile
 * and the availableResource matrix is computed. all the matrices are used in the bankers algorithm to compute the safe state.
 * Driver program cycles through each of the client and asks for their request for resources. If its safe to grant them resources
 * then they are granted and available matrix is updated accordingly. If it leads to unsafe state then the resources are not 
 * granted and corresponding message is shown.The output is shown on console as well as saved in output.txt file.
 * 
 */
import java.io.IOException;
import java.util.Arrays;

public class BankersAlgorithm {

	private int resources = 0, client = 0;
	private int availableRes[] = null;
	private int resAssigned[][] = null;
	private int resStilltoAssign[][] = null;

	public int bankersAlgorithm(ReadFile readData, int required[],int clientNumber) throws IOException {

		client 				= readData.getClient();
		resources 			= readData.getResources();
		availableRes 		= new int[resources];
		resAssigned  		= new int[client][resources];
		resStilltoAssign 	= new int[client][resources];
		resAssigned 		= readData.getResAssigned();
		System.arraycopy(readData.getAvailable(), 0,availableRes , 0,readData.getAvailable().length );
		for(int k = 0;k<client;k++){
			resStilltoAssign[k] = Arrays.copyOf(readData.getResStilltoAssign()[k], readData.getResStilltoAssign().length);
		}

		int terminated[] = new int[client];
		int safe = 0;
		int unsafe = 0;
		int j;

		for(int i = 0;i<client;i++){
			terminated[i] = 0;
		}

		for(int k = 0;k<resources;k++){
			if(resStilltoAssign[clientNumber][k] >= required[k]){
				if(availableRes[k] >= required[k]){
					availableRes[k]= availableRes[k] - required[k];
					resStilltoAssign[clientNumber][k]-=required[k];
				}
				else{
					System.out.println("Requested number of resources is more than available");
					return 1;

				}
			}
			else{
				System.out.println("Requested number of resources is more "
						+ "than maximum limit allocated for client:"+ (clientNumber + 1));
				return 2;
			}
		}
		System.out.println("Checking to see if its a safe state!!!");
		while(safe != client && unsafe != client){
			unsafe++;
			for(int i = 0;i<client;i++){
				if(terminated[i] == 0){
					for(j = 0;j<resources;j++){
						if(resStilltoAssign[i][j] > availableRes[j] ){
							break;
						}
					}
					if(j == resources){
						System.out.println("Processing client:"+ (i+1));
						terminated[i] = 1;
						safe++;
						for(int k = 0;k<resources;k++){
							availableRes[k]+=resAssigned[i][k];
						}
					}	
				}
			}
		}
		if(safe == client){
			System.out.println("Its a safe state!!!");
			int availableFinal[] = readData.getAvailable();
			int resStilltoAssignFinal[][] = readData.getResStilltoAssign();
			System.out.println("Now available resources:");
			for(int m = 0;m<resources;m++){
				availableFinal[m]= availableFinal[m] - required[m];
				resStilltoAssignFinal[clientNumber][m]-=required[m];
				System.out.print(availableFinal[m] + " ");
			}
			System.out.println("\n");
			return 3;
		}
		else{
			System.out.println("Deadlock!!!Its an unsafe state");
			return 4;
		}

	}
}		




