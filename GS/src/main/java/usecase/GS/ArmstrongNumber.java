package usecase.GS;
import java.io.*;
import java.util.*;


class ArmstrongNumber{
 
  public static boolean isArmStrongNumber(int n){
    int temp = n;
    int sum = 0;
    int length = String.valueOf(n).length();
    while(temp>0)
    {
      int n1 = temp%10;
      sum = sum+ (int)Math.pow(n1,length);
      temp = temp/10;
    }
    return sum==n;
  }
  
  public static void main(String[] args) {
    System.out.println(isArmStrongNumber(371));
    
  }
}
