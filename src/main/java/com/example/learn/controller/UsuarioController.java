package com.example.learn.controller;

import com.example.learn.ServiceInterface.IUsuarioServiceInterface;
import com.example.learn.model.UsuarioModel;
import com.example.learn.response.Response;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
@CrossOrigin
public class UsuarioController {
    
    private final IUsuarioServiceInterface usuarioInterface; 

    public UsuarioController(IUsuarioServiceInterface usuarioInterface) {
        this.usuarioInterface = usuarioInterface;
    }
    
    @PostMapping(value = "/crear")
    public ResponseEntity<Response<Object>> crearUsuario(@RequestBody @Valid UsuarioModel usuarioParam, BindingResult bind){
        Map<String, Object> errors = new HashMap<>();
        if(bind.hasErrors()){
            List<String> errores = new ArrayList<>();
            bind.getFieldErrors().forEach(error -> {
                errores.add(error.getDefaultMessage());
            });
            errors.put("data", errores);
            Response<Object> res = new Response("Faltan argumentos", false, HttpStatus.BAD_REQUEST, errors);
            return res.responseEntity(HttpStatus.BAD_REQUEST);
        }
        Response<Object> usuario = usuarioInterface.insertarEmpleado(usuarioParam);
        return usuario.responseEntity(usuario.getHttp());
    }
    
    @GetMapping(value = "/usuarios")
    public ResponseEntity<Response<Object>> buscarUsuarios(){
        Response<Object> usuarios = usuarioInterface.buscarEmpleados();
        return usuarios.responseEntity(usuarios.getHttp());
    }
    
    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<Response<Object>> buscarUusarioId(@PathVariable int id){
        Response<Object> usuario = usuarioInterface.buscarEmpleadoId(id);
        return usuario.responseEntity(usuario.getHttp());
    }
    
    @PutMapping(value = "/usuarioUpdate/{id}")
    public ResponseEntity<Response<Object>> actualizarUsuario(@PathVariable int id, @RequestBody UsuarioModel usuarioParam){
        Response<Object> usuario = usuarioInterface.actualizarEmpleado(usuarioParam, id);
        return usuario.responseEntity(usuario.getHttp());
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Response<Object>> deleteEmpleado(@PathVariable int id){
        Response<Object> response = usuarioInterface.eliminarEmpleadoId(id);
        return response.responseEntity(response.getHttp());
    }
}
