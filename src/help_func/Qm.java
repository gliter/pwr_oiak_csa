package help_func;

import java.util.ArrayList;

public class Qm {

	public static void main(String[] args) {
		for(int i = 0; i < 128; i++) {
			ArrayList<Integer> bin = new ArrayList<Integer>();
			ArrayList<Integer> resBin = new ArrayList<Integer>();
			int oneSum = 0;
			int x = i;
			for(int j = 0; j < 7; j++) {
				if(x % 2 == 1) {
					bin.add(0, 1);
					oneSum++;
				}
				else
					bin.add(0, 0);
				x /= 2;
			}
			x = oneSum;
			for(int j = 0; j < 3; j++) {
				if(x % 2 == 1) {
					resBin.add(0, 1);
				}
				else
					resBin.add(0, 0);
				x /= 2;
			}
			if(resBin.get(2) == 1) {
				System.out.print(i + " ");
				/*for(int j = 0; j < 7; j++)
					System.out.print(bin.get(j));
				System.out.print(" " + oneSum + " ");
				for(int j = 0; j < 3; j++)
					System.out.print(resBin.get(j));*/
				System.out.println();
			}
			
		}

	}

}
