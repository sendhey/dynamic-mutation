import java.io.*;

public class Solution {
	static int[][] holderTable;
	static int[][] solutionTable;
	
	public static void main(String[] args) {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
			String current = br.readLine();
			String first = current;
			String firstAllign = "";
			current = br.readLine();
			String second = current;
			String secondAllign = "";
			solutionTable = new int[first.length()+1][second.length()+1];
			holderTable = new int[first.length()+1][second.length()+1];
			System.out.println(dynamicMethod(first,second));
			int firstLength = first.length();
			int secondLength = second.length();
			while(true){
				if(firstLength == 0 && secondLength == 0){
					break;
				}else if(firstLength == 0){
					firstAllign = "_" + firstAllign;
					secondAllign = second.charAt(secondLength-1) + secondAllign;
					secondLength--;
					
 				}else if(secondLength == 0){
 					secondAllign = "_" + secondAllign;
 					firstAllign = first.charAt(firstLength-1) + firstAllign;
					firstLength--;
				}else{
					if(holderTable[firstLength][secondLength] == 1){
						firstAllign = "_" + firstAllign;
						secondAllign = second.charAt(secondLength-1) +secondAllign;
						secondLength--;
					}else if(holderTable[firstLength][secondLength] == 2){
						secondAllign = "_" + secondAllign;
						firstAllign = first.charAt(firstLength-1) + firstAllign;
						firstLength--;
					}else if(holderTable[firstLength][secondLength] == 3){
						firstAllign = first.charAt(firstLength-1) + firstAllign;
						firstLength--;
						secondAllign = second.charAt(secondLength-1) +secondAllign;
						secondLength--;
					}
					
					
				}
			}
			System.out.println(firstAllign);
			System.out.println(secondAllign);
		}catch (IOException e){
			e.printStackTrace();
		}

	}

	static int dynamicMethod(String x, String y){
		int score = 0;
		for (int i = 0; i < x.length() + 1; i++){
			for (int j = 0; j < y.length()+1; j++){
				if (j==0 && i==0){
					solutionTable[i][j] = 0;
				}else if(j==0){
					solutionTable[i][j] = solutionTable[i-1][j] -1;
				}else if(i==0){
					solutionTable[i][j] = solutionTable[i][j-1]-1;
				}else{
					if(y.charAt(j-1) == x.charAt(i-1)){
						score = 2; 
					}else{
						score = -1;
					}
					int one = solutionTable[i][j-1] - 1;
					int two = solutionTable[i-1][j] - 1;
					int three = solutionTable[i-1][j-1] + score;
					if(Math.max(Math.max(one,two), three) == one){
						solutionTable[i][j] = one;
						holderTable[i][j] = 1;
					}else if(Math.max(Math.max(one,two), three) == two){
						solutionTable[i][j] = two;
						holderTable[i][j] = 2;
					}else{
						solutionTable[i][j] = three;
						holderTable[i][j] = 3;
					}
					
				}
			}
		}
		return solutionTable[x.length()][y.length()];
	}
}