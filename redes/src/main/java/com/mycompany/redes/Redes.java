/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.redes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Collections;

/**
 *
 * @author danielaperez
 */
public class Redes {

    public static void main(String[] args) {
        System.out.println("");
        Scanner scan= new Scanner(System.in);
        ArrayList<Integer> binarios= new ArrayList<>();
        ArrayList<Integer> binarios2= new ArrayList<>(binarios);
        
        binarios.add(1); binarios.add(2); binarios.add(4); binarios.add(8); binarios.add(16);
        binarios.add(32); binarios.add(64); binarios.add(128); 
        Collections.reverseOrder();
        
        
        Integer x= 240;
        
        //String y= Integer.toBinaryString(x);
        //System.out.println(y);
        
        System.out.println("Ingresa la red");
        String red= scan.next();
        System.out.println("Ingresa la mascara");
        String mask= scan.next();
        System.out.println("Ingresa el numero de subredes a segmentar: ");
        int nSeg= scan.nextInt();
        
        System.out.println(binarios);
        
    }
}

class Result {
    public static ArrayList<Integer> And(String red, String mask){
        ArrayList<Integer> arr= new ArrayList<>();
        
        
        
        return arr;
    }
    
    public String idClase (String red){
        return "";
    }
    
}