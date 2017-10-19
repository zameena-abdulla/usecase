package usecase.GS;

import java.io.*;
import java.util.*;


class StringCompression { 
 
   public static String stringCompress(String s){
     for(int i=0;i<s.length();i++){
       int count =1;
       for(int j=i+1;j<s.length();j++){
         if(!(s.charAt(i) == s.charAt(j))){
           s=s.substring(0,i)+count+s.substring(j-1);
           i++;
           break;
         }
         else{
           count++;
         }
       }     
   }
      return s; 
   }
  public static void main(String[] args) {
    System.out.println(stringCompress("ssssstppq"));   
  }
}