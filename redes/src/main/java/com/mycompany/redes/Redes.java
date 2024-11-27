package com.mycompany.redes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Collections;

public class Redes {

    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        ArrayList<Integer> binarios= new ArrayList<>();
        
        Result validacion= new Result();
        Result resultadoClase= new Result();
        
        boolean error= true;
        boolean response;
        
        do {
            //Pide al usuario ingresar la red a validar
            System.out.println("Ingresa la red");
            String red= scan.next();
            //Valida si la red ingresada es correcta
            response= validacion.valid(red);
            if (response) {
                System.out.println("\tERROR. Red incorrecta");
            }
        } while (response);//
        
        System.out.println("La clase por default es: \t"+ validacion.resultadoClase);
    }
}

class Result {
    // Declara global para usarla en el main
    public String resultadoClase;
    public String idClase (String red){
        return "retorno cadena 1";
    }
    
    // validar si una cada subcadena es valida
    public Boolean valid(String red){
        boolean error= false;
        //Se divide la el parametro red que se pasa a la funcion mediante .
        //Ejemplo = "10.20.160.192" --> [0] = 10, [1] = 20, [2] = 160 y [3] = 192 
        String[] subcadena= red.split("\\.");
        
        // Valida que la red cuente con los 4 bytes 
        if (subcadena.length>4 || subcadena.length<1) {
            System.out.println("error 1");
            //Retorna error si la red no cuenta con los 4 bytes
            error= true;
        }else{
            //For que toma el valor de cada byte 
            for (int i = 0; i <subcadena.length; i++) {
                Integer scad= Integer.parseInt(subcadena[i]);
                //Valida que el numero sea mayo o igual a 0 y meno a 253
                if (scad<=0 || scad>253) {
                    //Retorna error
                    error= true;
                    break;
                }else {
                    if (i==0) {
                        resultadoClase= clase(scad);
                    }
                }
            }
        }
        return error;
    }

    //Valida que clase de red es.
    public String clase(Integer sCad0){
        if (sCad0>=1 && sCad0<=127) {
            return "Red de clase A";
        }else if (sCad0>=128 && sCad0<=191) {
            return "Red de clase B";
        }else if (sCad0>=192 && sCad0<=253) {
            return "Red de clase C";
        }
    }
    
}
