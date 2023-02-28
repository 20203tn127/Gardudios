package mx.edu.utez.adoptaMe.controller;

import mx.edu.utez.adoptaMe.model.MascotaModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/mascotas")
public class MascotaController {



    @GetMapping(value = { "/listarAll","/listarAll/{esAdopcion}", "/listar/{tipoMascotaRuta}", "listar/{tipoMascotaRuta}/{esAdopcion}"})
    public String listar(@PathVariable("tipoMascotaRuta") Optional<String> tipoMascotaRuta, @PathVariable("esAdopcion") Optional<Boolean> esAdopcion,  Model modelo){

        MetodosMascotas mascotas = new MetodosMascotas();
        LinkedList<MascotaModel> mascotasLista = mascotas.regresarMascotas();

        LinkedList<MascotaModel> sublistaPerros =
                mascotasLista.stream().filter(m -> m.getTipoMascota().equals("perro"))
                        .collect(Collectors.toCollection(LinkedList::new));

        LinkedList<MascotaModel> sublistaGatos =
                mascotasLista.stream().filter(m -> m.getTipoMascota().equals("gato"))
                        .collect(Collectors.toCollection(LinkedList::new));



        if (esAdopcion.isPresent() || tipoMascotaRuta.isPresent()){
            System.out.println(tipoMascotaRuta.isPresent());
            System.out.println(esAdopcion.isPresent());
            if (esAdopcion.isPresent()){
                if (tipoMascotaRuta.isPresent() && tipoMascotaRuta.get().equals("perros")){;
                    System.out.println("Todos perros son para adopcion - true ");
                    LinkedList<MascotaModel> sublistaPerrosAdopcion =
                            sublistaPerros.stream().filter(m -> m.getDisponibleAdopcion().equals(true))
                                    .collect(Collectors.toCollection(LinkedList::new));
                    modelo.addAttribute("subListaPerritos",sublistaPerrosAdopcion);
                    modelo.addAttribute("status", true);
                    return "pets/dogs";

                }else if (tipoMascotaRuta.isPresent() && tipoMascotaRuta.get().equals("gatos")){
                    System.out.println("Todos gatos son para adopcion - true");
                    LinkedList<MascotaModel> sublistaGatosAdopcion =
                            sublistaGatos.stream().filter(m -> m.getDisponibleAdopcion().equals(true))
                                    .collect(Collectors.toCollection(LinkedList::new));
                    modelo.addAttribute("subListaGatitos",sublistaGatosAdopcion);
                    modelo.addAttribute("status", true);
                    return "pets/cats";

                }else {
                    System.out.println("Todas las mascotas son para adopcion - true");
                    LinkedList<MascotaModel> sublistaAdopcionGeneral =
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

    @PostMapping("/save")
    public String guardarMascota(
            @RequestParam("nombreMascota") String nombreNew,
            @RequestParam("edadMascota") Integer edadNew,
            @RequestParam(name = "desMascota", required = false) String descripcionNew,
            @RequestParam("tipoMascota") String tipoMascotaNew,
            @RequestParam("imagenMascota") String imagenMascotaNew,
            @RequestParam(name = "disponibleAdopcionTrue",required = false) Boolean disponibleAdopcionTrue,
            @RequestParam(name = "disponibleAdopcionFalse",required = false) Boolean disponibleAdopcionFalse,
            Model modelo){
            MetodosMascotas mascotas = new MetodosMascotas();
            LinkedList<MascotaModel> mascotasLista = mascotas.regresarMascotas();
            MascotaModel mascotaNew = new MascotaModel();
            mascotaNew.setNombre(nombreNew);
            mascotaNew.setEdad(edadNew);
            mascotaNew.setDescripcion(descripcionNew);
            mascotaNew.setTipoMascota(tipoMascotaNew);
            mascotaNew.setImagen(imagenMascotaNew);
            mascotaNew.setDisponibleAdopcion(disponibleAdopcionTrue);
            mascotaNew.setDisponibleAdopcion(disponibleAdopcionFalse);
            mascotasLista.add(mascotaNew);
            modelo.addAttribute("ListaMascotas", mascotasLista);
            return "pets/home";
    }

}
