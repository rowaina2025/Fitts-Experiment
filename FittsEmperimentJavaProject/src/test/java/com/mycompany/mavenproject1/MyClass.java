package com.mycompany.mavenproject1;

public class MyClass {
    
    public int Sqr(int n){
        return n * n;
    }
    
    public static void main(String[] args){
        int n = 7;
        MyClass ob = new MyClass();
        System.out.println(ob.Sqr(n));
    }
}
 