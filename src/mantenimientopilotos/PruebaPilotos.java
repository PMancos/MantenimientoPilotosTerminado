package mantenimientopilotos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PruebaPilotos {

    public static ArrayList<Piloto> listaPilotos = new ArrayList();
    public static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int op;
        //ArrayList <Piloto> listaPilotos=null; 
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream("pilotos.dat");
            oos = new ObjectOutputStream(fos);
            do {
                op = menu();
                switch (op) {

                    case 1:
                        AñadirPiloto();
                        break;
                    case 2:
                        modificarEscuderia();
                        break;
                    case 3:
                        bajaPiloto();
                        break;
                    case 4:
                        mostrarListaPilotos();
                        break;
                    case 5:
                        mostrarAlfabeticamente();
                        break;
                    case 6:
                        mostrarNumPremios();
                        break;
                    case 7:
                        for(int i=0;i<listaPilotos.size();i++){
                            oos.writeObject(listaPilotos.get(i));
                        }
                        break;

                }
            } while (op != 7);

            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PruebaPilotos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PruebaPilotos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(PruebaPilotos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static int menu() {
        int op;

        System.out.println("1. Añadir piloto");
        System.out.println("2. Modificar escudería piloto");
        System.out.println("3. Baja piloto");
        System.out.println("4. Mostrar lista pilotos");
        System.out.println("5. Mostrar pilotos alfabéticamente");
        System.out.println("6. Mostrar pilotos ordenados por nºGP");
        System.out.println("7. Salir");
        do {
            System.out.println("");
            System.out.print("Introduce una opción (1-7):");
            op = teclado.nextInt();
        } while (op < 1 && op < 7);

        return op;
    }

    public static void AñadirPiloto() {
        String nombre, escuderia;
        String condicion;
        int numPremios;
        boolean actividad;

        //RECOGIDA DE DATOS.
        System.out.println("*****Añadir Piloto*****\n-------------------");
        System.out.println("Nombre:");
            teclado.nextLine();
            nombre = teclado.nextLine();
        System.out.println("Escuderia: ");
            escuderia = teclado.nextLine();
        System.out.println("Número de premios ganados: ");
            numPremios = teclado.nextInt();
        System.out.println("Está activo? ");
            teclado.nextLine();
        condicion = teclado.nextLine();
        if (condicion.equalsIgnoreCase("si")) {
            actividad = true;
        } else {
            actividad = false;
        }

        //AÑADIR PILOTO A LA LISTA O ARRAY DE PILOTOS.
        listaPilotos.add(new Piloto(escuderia, nombre, numPremios, actividad));
        System.out.println("Piloto añadido correctamente.\n\n");
    }

    public static void modificarEscuderia() {
        boolean encontrado = false;
        int i = 0;
        String escuderia;
        String nombre;
        
        System.out.println("*****Modificar Escudería*****\n-------------------");
        //RECOGIDA DE DATOS.
            teclado.nextLine();
        System.out.println("A que piloto deseas cambiar de escuderia: ");
            nombre = teclado.nextLine();

        //CAMBIAR ESCUDERÍA
        while (encontrado == false && i < listaPilotos.size()) {

            if (listaPilotos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Escudería: ");
                escuderia = teclado.nextLine();
                listaPilotos.get(i).setEscuderia(escuderia);
                encontrado = true;
                System.out.println("Escuderia cambiada correctamente");
            } else {
                System.out.println("No existe el piloto.");
                System.out.println("");
                System.out.println("A que piloto deseas cambiar de escuderia: ");
                    nombre = teclado.nextLine();
            }

        }
        System.out.println("\n");
    }

    public static void bajaPiloto() {
        boolean encontrado = false;
        int i = 0;
        String nombre;
        
        System.out.println("*****Baja Piloto*****\n-----------------------");
        System.out.println("Nombre del piloto a darle baja: ");
            teclado.nextLine();
        nombre = teclado.nextLine();
        
        //BUSCAR EL PILOTO EN EL ARRAY O LISTA DE PILOTOS
        while (encontrado == false && i < listaPilotos.size()) {

            if (nombre.equalsIgnoreCase(listaPilotos.get(i).getNombre())) {
                encontrado = true;

            }
            if (encontrado) {
                listaPilotos.remove(i);
                System.out.println("Piloto eliminado correctamente.");
            } else {
                System.out.println("Piloto no encontrado.");
            }

        }
    }

    public static void mostrarListaPilotos() {
        System.out.println("");
        System.out.println("*****Mostrar Lista Pilotos\n-----------------------");
        for (int i = 0; i < listaPilotos.size(); i++) {
            System.out.println(listaPilotos.get(i).toString());
        }
        
    }

    public static void mostrarAlfabeticamente() {
        System.out.println("");
        System.out.println("*****Mostrar Lista Pilotos Ordenados Alfabéticamente"
                + "\n---------------------------------------");
        
        //ORDENACIÓN POR DEFECTO (ALFABÉTICAMENTE), HAY QUE IMPLEMENTAR COMPARABLE EN LA CLASE PILOTO.
        Collections.sort(listaPilotos);
        for (int i = 0; i < listaPilotos.size(); i++) {
            System.out.println(listaPilotos.get(i).toString());
        }
        
    }

    public static void mostrarNumPremios() {
        System.out.println("");
        System.out.println("*****Mostrar Lista Pilotos Ordenados por Numero de Premios"
                + "\n---------------------------------------");
        
        //ORDENACIÓN POR NUMERO DE PREMIOS
        Collections.sort(listaPilotos, new OrdenarPorNumeroPremios());
        for (int i = 0; i < listaPilotos.size(); i++) {
            System.out.println(listaPilotos.get(i).toString());
        }
        
    }

}
