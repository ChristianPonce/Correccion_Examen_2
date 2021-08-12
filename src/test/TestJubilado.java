package test;
import dominio.JubiladoDiscapacidad;
import dominio.JubiladoVejez;
import dominio.Jubilado;
import dominio.JubiladoPatron;
import java.util.ArrayList;
import java.util.Scanner;

public class TestJubilado {

    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Jubilado> jubilados = new ArrayList<>();

    public static void main(String[] args) {
        int op = 0;        
        String cedula, nombres;
        int anios;
        do {
            op = menu(op);
            if (op != 5 && op != 4) {
                entrada.nextLine();
                System.out.print("Cedula: ");
                cedula = entrada.nextLine();
                System.out.print("Nombre: ");
                nombres = entrada.nextLine();
                System.out.print("Anios de Aportacion:  ");
                anios = entrada.nextInt();
                datos(op, cedula, nombres, anios);
            }
            if(op==4){
                System.out.println("Datos de jubulacion patronal:\n");
                for (Jubilado jub : jubilados) {
                    jub.calculaPensioni();
                    jub.calcularPension();
                    if (jub instanceof JubiladoPatron) {
                        ((JubiladoPatron) jub).bonoSueldo();
                        System.out.println(jub);
                    } 
                }
                System.out.println("Datos de jubilados por Discapacidad:\n");
                for (Jubilado jub : jubilados) {
                    jub.calculaPensioni();
                    jub.calcularPension();
                    if (jub instanceof JubiladoDiscapacidad) {
                        System.out.println(jub);
                    }
                }
                System.out.println("Datos de jubilados por vejez:\n");
                for (Jubilado jub : jubilados) {
                    jub.calculaPensioni();
                    jub.calcularPension();
                    if (jub instanceof JubiladoVejez) {
                        System.out.println(jub);
                    }
                }
            }
        } while (op != 5);
    }
    public static int menu(int op) {
        System.out.println("    Menu de Jubilados (IESS)");
        System.out.print("1. Vejez\n2. Invalidez\n3. Patronal\n4. Reporte\n5. Salir\n Elija una opcion: ");
        op = entrada.nextInt();
        return op;
    }
    public static void datos(int op, String cedula, String nombres, int anios) {
        double porcentaje;
        int tipo;
        switch (op) {
            case 1:
                jubilados.add(new JubiladoVejez(cedula, nombres, anios));                
                break;
            case 2:
                System.out.print("Porcentaje de Discapacidad: ");
                porcentaje = entrada.nextDouble();
                jubilados.add(new JubiladoDiscapacidad(cedula, nombres, anios, porcentaje));
                break;
            case 3:
                System.out.print("Porcentaje de Inflacion: ");
                porcentaje = entrada.nextDouble();
                System.out.print("Tipo de Empresa publica <<1>>  privada <<2>> ");
                tipo = entrada.nextInt();
                jubilados.add(new JubiladoPatron(cedula, nombres, anios, porcentaje, tipo));
        }
    }
}
