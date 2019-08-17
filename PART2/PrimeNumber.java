/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author markf
 */
public class PrimeNumber {
    public int getPrimeNumber(int start,int end){
        int max =0;
        int primArr[] = new int[end-start];
        if(end<start){
            System.out.println("Parameter is wrong");
        }else{
            int index = 0;
            for(int i = start;i<=end;i++){
                if(isPrime(i)){
                    primArr[index] = i;
                    index ++;
                }
            }
        }
        if(primArr.length>0){
             max = primArr[0];

		for (int x = 1; x < primArr.length; x++) {
			if (primArr[x] > max)
				max = primArr[x];

		}

        }
        return max;
    }

        public static boolean isPrime(int number){
        if(number<2){
            return false;
        }

        for(int i=1;i<number;i++){
            if(number % i == 0){
                if(number == i){
                    return true;
                }else if(i!=1){
                    return false;
                }
            }
        }
        return true;
    }
}
