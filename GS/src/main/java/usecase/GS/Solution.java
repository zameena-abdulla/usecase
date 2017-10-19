package usecase.GS;
import java.io.*;
import java.util.*;


class Solution {
  public static void printPairsOfAtoB_BtoA(int[] n){
    for(int i=0;i<n.length;i++){
      for(int j=i+1;j<n.length;j++){
        if(n[i] == n[j])
          continue;
        if(n[i] ==1 && n[j]!= 1)
          continue;
        if(isAtoB_BtoA(n[i],n[j]))
          System.out.println(n[i]+" "+n[j]);        
      }
    }
  }
  
  private static boolean isAtoB_BtoA(int a, int b){
    return Math.pow(a,b) == Math.pow(b,a);    
  }
  public static void main(String[] args) {
    int[] n = {1,2,3,4,5,6,7,8,9,10,11,12};
    printPairsOfAtoB_BtoA(n);
    
  }
}
