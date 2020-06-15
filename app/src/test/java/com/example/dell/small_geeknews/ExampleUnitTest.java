package com.example.dell.small_geeknews;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private int temp;

    @Test
    public void addition_isCorrect() throws Exception {
      int [] arr ={9,20,1,17,5,8,3,2};
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j>0;j--){
                if(arr[j] < arr[j-1]){
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    System.out.println(arr[j]);
                }else{         //不需要交换
                    break;
                }
            }
        }



//          String s1="123";
//          String s2="123";
////        System.out.println(s1==s2);//true
//        StringBuffer sf1 = new StringBuffer("123");
//        StringBuffer sf2 = new StringBuffer("123");
////        System.out.println(sf1==sf2);//false
//
////        System.out.println(s1.equals(s2));/true
////        System.out.println(sf1.equals(sf2));//false
//
//        ArrayList<String> list = new ArrayList<>();
//         list.add("123");
//         ArrayList<String> list2 = new ArrayList<>();
//         list.add("123");
////        System.out.println(list==list2);//false
////        System.out.println(list.equals(list2));//false
//
//           int  a=10;
//           int  b=10;
//           Integer c=10;
//        System.out.println(a==b);
//        System.out.println(a==c);
    }
}