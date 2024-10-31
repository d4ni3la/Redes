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
        
        binarios.add(1); binarios.add(2); binarios.add(4); binarios.add(8); binarios.add(16);
        binarios.add(32); binarios.add(64); binarios.add(128); 
        Collections.reverse(binarios);
        
        Result validacion= new Result();
        
        boolean error= true;
        
        boolean response;
        //String y= Integer.toBinaryString(x);
        //System.out.println(y);
        do {
            System.out.println("Ingresa la red");
            String red= scan.next();
            response= validacion.valid(red);
            if (response) {
                System.out.println("\tERROR. Red incorrecta");
            }
        } while (response);
        
        /*
        System.out.println("Ingresa la mascara");
        String mask= scan.next();
        System.out.println("Ingresa el numero de subredes a segmentar: ");
        int nSeg= scan.nextInt();*/
        
        System.out.println(binarios);
        
    }
}

class Result {
    public static ArrayList<Integer> And(String red, String mask){
        ArrayList<Integer> arr= new ArrayList<>();
        
        
        
        return arr;
    }
    
    public String idClase (String red){
        return "retorno cadena 1";
    }
    
    public Boolean valid(String red){
        boolean error= false;
        String[] subcadena= red.split(".");
        
        // numero de elementos en la cadena
        if (subcadena.length!=4) {
            error= true;
        }
        for (int i = 0; i <subcadena.length; i++) {
            Integer scad= Integer.parseInt(subcadena[i]);
            if (scad<0 || scad>253) {
                error= true;
                break;
            }else {
                if (i==0) {
                    if (scad>0 && scad<=127) {
                        
                    }
                }
            }
        }
        return error;
    }
    
    public String clase(Integer sCad0){
         
       return "";
    }
    
}