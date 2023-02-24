package mx.edu.utez.adoptaMe.controller;

import mx.edu.utez.adoptaMe.model.mascotaModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/mascotas")
public class mascotaController {
    @GetMapping(value = { "/listarAll","/listarAll/{esAdopcion}", "/listar/{tipoMascotaRuta}", "listar/{tipoMascotaRuta}/{esAdopcion}"})
    public String listar(@PathVariable("tipoMascotaRuta") Optional<String> tipoMascotaRuta, @PathVariable("esAdopcion") Optional<Boolean> esAdopcion,  Model modelo){

        LinkedList<mascotaModel> mascotasLista = new LinkedList<>();
        mascotasLista.add(new mascotaModel("Dachshund",2,"Perro","perro","perro.jpeg",true));
        mascotasLista.add(new mascotaModel("Bills",10,"Gato egipcio","gato","bills.jpg",false));
        mascotasLista.add(new mascotaModel("Willson",4,"Gato de elrubius","gato","wilson.jpg",true));
        mascotasLista.add(new mascotaModel("Aleman",7,"Perro Aleman","perro","pastorAl.jpg",false));
        mascotasLista.add(new mascotaModel("Gato generico",6,"Gato generico","gato","gatogen.jpg",true));
        mascotasLista.add(new mascotaModel("Perrito god",8,"Perro gods god","perro","god.jpeg",true));

        LinkedList<mascotaModel> sublistaPerros =
                mascotasLista.stream().filter(m -> m.getTipoMascota().equals("perro"))
                        .collect(Collectors.toCollection(LinkedList::new));

        LinkedList<mascotaModel> sublistaGatos =
                mascotasLista.stream().filter(m -> m.getTipoMascota().equals("gato"))
                        .collect(Collectors.toCollection(LinkedList::new));



        if (esAdopcion.isPresent() || tipoMascotaRuta.isPresent()){
            System.out.println(tipoMascotaRuta.isPresent());
            System.out.println(esAdopcion.isPresent());
            if (esAdopcion.isPresent()){
                if (tipoMascotaRuta.isPresent() && tipoMascotaRuta.get().equals("perros")){;
                    System.out.println("Todos perros son para adopcion - true ");
                    LinkedList<mascotaModel> sublistaPerrosAdopcion =
                            sublistaPerros.stream().filter(m -> m.getDisponibleAdopcion().equals(true))
                                    .collect(Collectors.toCollection(LinkedList::new));
                    modelo.addAttribute("subListaPerritos",sublistaPerrosAdopcion);
                    modelo.addAttribute("status", true);
                    return "pets/dogs";

                }else if (tipoMascotaRuta.isPresent() && tipoMascotaRuta.get().equals("gatos")){
                    System.out.println("Todos gatos son para adopcion - true");
                    LinkedList<mascotaModel> sublistaGatosAdopcion =
                            sublistaGatos.stream().filter(m -> m.getDisponibleAdopcion().equals(true))
                                    .collect(Collectors.toCollection(LinkedList::new));
                    modelo.addAttribute("subListaGatitos",sublistaGatosAdopcion);
                    modelo.addAttribute("status", true);
                    return "pets/cats";

                }else {
                    System.out.println("Todas las mascotas son para adopcion - true");
                    LinkedList<mascotaModel> sublistaAdopcionGeneral =
                            mascotasLista.stream().filter(m -> m.getDisponibleAdopcion().equals(true))
                                    .collect(Collectors.toCollection(LinkedList::new));
                    modelo.addAttribute("ListaMascotas", sublistaAdopcionGeneral);
                    modelo.addAttribute("status", true);
                    return "pets/home";
                }
            }else {
                if (tipoMascotaRuta.get().equals("perros")){
                    System.out.println("Todos perros");
                    modelo.addAttribute("status", false);
                    modelo.addAttribute("subListaPerritos",sublistaPerros);
                    return "pets/dogs";
                }else {
                    System.out.println("Todos gatos");
                    modelo.addAttribute("status", false);
                    modelo.addAttribute("subListaGatitos",sublistaGatos);
                    return "pets/cats";
                }
            }

        }else {
            modelo.addAttribute("ListaMascotas", mascotasLista);
            modelo.addAttribute("status", false);
            System.out.println(sublistaGatos + "espacio memoria");
            return "pets/home";
        }

    }

}
