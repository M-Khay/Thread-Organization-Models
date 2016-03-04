import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadFile {

	private int resources = 0, client = 0;
	private int totResource[] = null;
	private int available[] = null;
	private int possesedRes[] = null;
	private int resAssigned[][] = null;
	private int resStilltoAssign[][] = null;

	String line = null;
	int i = 0;

	public void readFile(){

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader("input.txt");

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line);
				String token = null;
				int j = 0;
				while (st.hasMoreTokens()) {
					token = st.nextToken();
					System.out.print(token +" ");

					if(i==2 && j > 1){
						totResource[j-2] = Integer.parseInt(token);  
					}
					if(i>3 && i<= client + 3){
						resAssigned[i-4][j] = Integer.parseInt(token);   

					}
					if(i > client + 4 && i<= 2*client + 4){
						resStilltoAssign[i- client - 5][j] = Integer.parseInt(token);   

					}
					j++;
				}
				System.out.print("\n");
				if(i==0){
					resources = Integer.parseInt(token);
				}
				else if(i==1){
					client = Integer.parseInt(token);
					totResource = new int[resources];
					available = new int[resources];
					possesedRes = new int[resources];
					resAssigned = new int[client][resources];
					resStilltoAssign = new int[client][resources];

				}
				i++;
			}   
		}
		catch(IOException ex) {
			System.out.println("Error reading file ");  
			ex.printStackTrace();

		}

		for(int k = 0; k<client;k++){
			for(int m = 0;m<resources;m++){
				possesedRes[m]+= resAssigned[k][m];
			}
		}

		for(int m = 0;m<resources;m++){
			available[m] = totResource[m] - possesedRes[m];
		}

	}

	public int getResources() {
		return resources;
	}

	public int getClient() {
		return client;
	}

	public int[] getTotResource() {
		return totResource;
	}

	public int[] getAvailable() {
		return available;
	}

	public int[] getPossesedRes() {
		return possesedRes;
	}

	public int[][] getResAssigned() {
		return resAssigned;
	}

	public int[][] getResStilltoAssign() {
		return resStilltoAssign;
	}
}
