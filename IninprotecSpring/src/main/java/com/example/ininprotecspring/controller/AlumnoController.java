package com.example.ininprotecspring.controller;

import com.example.ininprotecspring.clase.AlumnoCurso;
import com.example.ininprotecspring.clase.PersonalBolsa;
import com.example.ininprotecspring.repositorio.RepositorioAlumno;
import com.example.ininprotecspring.servicio.Servicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller


public class AlumnoController {
@Autowired
    private RepositorioAlumno repository;
@Autowired
private Servicio utilService;
@GetMapping("/")
public String inicio(){
    return "redirect:/login";
}
@GetMapping("/login")
    public String login(Model modelo){
    modelo.addAttribute("alumno",new PersonalBolsa());
    return "login";

}

@GetMapping("/succesfull")
    public String succesfullLogin(@ModelAttribute PersonalBolsa alumno, HttpServletRequest request, RedirectAttributes redirectAttributes){
    String contraseñaHash= utilService.cifrado(alumno.getContrasena());
    PersonalBolsa alumnoBBDD=repository.getByCorreo(alumno.getCorreo());
    if(alumnoBBDD!=null&&alumnoBBDD.getContrasena().equals(contraseñaHash)){
        HttpSession sesion=request.getSession();
        sesion.setAttribute("alumno",alumnoBBDD);
        return "redirect:/"+alumnoBBDD.getNombre()+"/"+alumnoBBDD.getId()+"/curriculum";
    }else if(alumnoBBDD==null){
        redirectAttributes.addFlashAttribute("error", true);
        redirectAttributes.addFlashAttribute("message", "Usuario o contraseña incorrectos");
        return "redirect:/login";
    }else{
        return "redirect:/login";
    }
}
@GetMapping("/{nombre}/{id}/curriculum")
    public String vistaCurriculum(HttpServletRequest request){
    HttpSession sesion= request.getSession();
    PersonalBolsa alumno=(PersonalBolsa) sesion.getAttribute("alumno");
    if(alumno!=null){
        return "curriculum";
    }else{
        return "redirect:/login";
    }


}
@PostMapping("/subirCV")
    public String subirCV(@RequestParam("file") MultipartFile file, RedirectAttributes redirect,HttpServletRequest request){
    HttpSession session=request.getSession();
    PersonalBolsa alumno= (PersonalBolsa) session.getAttribute("alumno");
    if (file.isEmpty()) {
        redirect.addFlashAttribute("message", "Por favor selecciona un archivo para subir.");
        return "redirect:/"+alumno.getNombre()+"/"+alumno.getId()+"/curriculum";
    }
    try {
        alumno.setCurriculumUrl(file.getBytes());
        repository.save(alumno);
        redirect.addFlashAttribute("message", "Has subido exitosamente '" + file.getOriginalFilename() + "'");

    } catch (Exception e) {
        redirect.addFlashAttribute("message", "Error al subir el archivo: " + file.getOriginalFilename() + "!");
    }
    return "redirect:/"+alumno.getNombre()+"/"+alumno.getId()+"/curriculum";

}
@GetMapping("/{nombre}/{id}/cursos")
public String vistaCursos(HttpServletRequest request,Model modelo) {
    HttpSession sesion = request.getSession();
    PersonalBolsa alumno = (PersonalBolsa) sesion.getAttribute("alumno");
    if (alumno != null) {

        return "cursos";
    } else {
        return "redirect:/login";
    }
}

    @PostMapping("/download")
    public ResponseEntity<byte[]> downloadReport(HttpServletRequest request) {
    HttpSession session=request.getSession();
    PersonalBolsa alumno= (PersonalBolsa) session.getAttribute("alumno");
        HttpHeaders header=null;
        byte[] data =null;
        try {
            if(alumno!=null){
                 data = utilService.generateReport(alumno);
                 header = new HttpHeaders();
                header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=IIP Calificaciones.pdf");
                header.add(HttpHeaders.CONTENT_TYPE, "application/pdf");


            }

            return ResponseEntity.ok()
                    .headers(header)
                    .body(data);

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(null);
        }
    }
    @GetMapping("/{nombre}/{id}/perfil")
    public String perfil(HttpServletRequest request){
        HttpSession session=request.getSession();
        PersonalBolsa alumno= (PersonalBolsa) session.getAttribute("alumno");
        if(alumno!=null){
            return "perfil";
        }else{
            return "redirect:/login";
        }
    }

    @GetMapping("/{nombre}/{id}/contrasena")
    public String vistaContraseña(HttpServletRequest request, Model modelo){
        HttpSession session=request.getSession();
        PersonalBolsa alumno= (PersonalBolsa) session.getAttribute("alumno");
        if(alumno!=null){
            return "contraseña";
        }else{
            return "redirect:/login";
        }

    }
    @PostMapping("/cambio")
    public String cambioCOntraseña(HttpServletRequest request, RedirectAttributes redirectAttributes){
    String contraseñaActual=request.getParameter("contrasenaActual");
    String contraseñaNueva=request.getParameter("contrasenaNueva");
    String contraseñaConfirm=request.getParameter("contrasenaConfirm");
        HttpSession session=request.getSession();
        PersonalBolsa alumnoBBD= (PersonalBolsa) session.getAttribute("alumno");
        if(alumnoBBD!=null &&contraseñaNueva.length()>=5){
            if(alumnoBBD.getContrasena().equals(utilService.cifrado(contraseñaActual))&&contraseñaNueva.equals(contraseñaConfirm)){
                alumnoBBD.setContrasena(utilService.cifrado(contraseñaNueva));
                repository.save(alumnoBBD);
                return "redirect:/"+alumnoBBD.getNombre()+"/"+alumnoBBD.getId()+"/contrasena";
            }else{
                redirectAttributes.addFlashAttribute("error", true);
                redirectAttributes.addFlashAttribute("message", "Compruebe de que la contraseña actual sea correcta y que coincida la contraseña nueva con la confirmación");
                return "redirect:/"+alumnoBBD.getNombre()+"/"+alumnoBBD.getId()+"/contrasena";
            }
        }else if(alumnoBBD!=null&&contraseñaNueva.length()<5){
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", "La contraseña nueva debe de contener al menos 5 carácteres");
            return "redirect:/"+alumnoBBD.getNombre()+"/"+alumnoBBD.getId()+"/contrasena";
        }else{
            return "redirect:/login";
        }

    }
@GetMapping("cerrar")
public String logout(HttpServletRequest request){
    HttpSession session=request.getSession();
    session.invalidate();
    return "redirect:/login";
}
    @ModelAttribute("alumno")
    public PersonalBolsa getAlumnoActual(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // false para no crear una nueva sesión
        return (session != null) ? (PersonalBolsa) session.getAttribute("alumno") : null;
    }



}
