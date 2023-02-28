package mx.edu.utez.adoptaMe.controller;

import mx.edu.utez.adoptaMe.model.MascotaModel;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class MetodosMascotas {

    public LinkedList<MascotaModel> regresarMascotas (){
        LinkedList<MascotaModel> mascotasLista = new LinkedList<>();
        mascotasLista.add(new MascotaModel("Dachshund",2,"Perro","perro","perro.jpeg",true));
        mascotasLista.add(new MascotaModel("Bills",10,"Gato egipcio","gato","bills.jpg",false));
        mascotasLista.add(new MascotaModel("Willson",4,"Gato de elrubius","gato","wilson.jpg",true));
        mascotasLista.add(new MascotaModel("Aleman",7,"Perro Aleman","perro","pastorAl.jpg",false));
        mascotasLista.add(new MascotaModel("Gato generico",6,"Gato generico","gato","gatogen.jpg",true));
        mascotasLista.add(new MascotaModel("Perrito god",8,"Perro gods god","perro","god.jpeg",true));

        return mascotasLista;
    }
}
