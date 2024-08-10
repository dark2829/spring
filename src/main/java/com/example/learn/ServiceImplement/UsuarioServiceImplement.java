package com.example.learn.ServiceImplement;

import org.springframework.stereotype.Service;
import com.example.learn.ServiceInterface.IUsuarioServiceInterface;
import com.example.learn.model.UsuarioModel;
import com.example.learn.repository.IUsuarioRepository;
import com.example.learn.response.GeneralServiceException;
import com.example.learn.response.NoDataFoundException;
import com.example.learn.response.Response;
import com.example.learn.response.ValidateServiceException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImplement implements IUsuarioServiceInterface{
    
    private final IUsuarioRepository usuarioRepository;

    public UsuarioServiceImplement(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @Override
    public Response<Object> insertarEmpleado(UsuarioModel usuarioParam) {
        String message = ""; 
        HttpStatus http = HttpStatus.BAD_REQUEST;
        UsuarioModel usuario = null; 
        Boolean isOk = false;
        try{
            usuario = usuarioRepository.save(usuarioParam);
            if(usuario != null){
                message = "Usuario creado correctamente";
                isOk = true; 
                http = HttpStatus.CREATED;
            }else{
                message = "Error al crear el usuario";
            }
        }catch(ValidateServiceException e){
            throw e;
        }catch(Exception e){
            throw new GeneralServiceException(e.getMessage());
        }
        return new Response<>(message, isOk, http, usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Response<Object> buscarEmpleados() {
        String message = ""; 
        HttpStatus http = HttpStatus.BAD_REQUEST; 
        List<UsuarioModel> usuarios = null;
        Boolean isSuccess = false; 
        try{
            
            usuarios = usuarioRepository.findAll();
            if(usuarios != null){
                message = "Se encontraron " + usuarios.size() + " registros";
                http = HttpStatus.OK;
            }else{
                message = "No existen registros en el sistema";
                http = HttpStatus.NO_CONTENT;
            }            
            isSuccess = true; 
        }catch (NoDataFoundException e) {
            message = "Ocurrio un error inesperado ";
            throw e; 
        }catch (Exception e){
            throw new GeneralServiceException(e.getMessage());
        }
        return new Response(message, isSuccess, http, usuarios);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Response<Object> buscarEmpleadoId(int id){
        String message = ""; 
        HttpStatus http = HttpStatus.BAD_REQUEST; 
        UsuarioModel usuario = null;
        Boolean isSuccess = false; 
        try{
            usuario = usuarioRepository.findById(id).orElseThrow(() -> new NoDataFoundException("No exste el registro"));
            message = "Recurso encontrado";
            isSuccess = true; 
            http = HttpStatus.OK;
        }catch(NoDataFoundException e){
            message = "No existe informacion";
            throw e; 
        }catch( Exception e){
            throw new GeneralServiceException(e.getMessage());
        }
        return new Response<>(message, isSuccess, http, usuario);
    }
    
    @Override
    public Response<Object> actualizarEmpleado(UsuarioModel usuarioParam, int id) {
        String message = "";
        HttpStatus http = HttpStatus.BAD_REQUEST;
        boolean isOk = false; 
        UsuarioModel usuario = null; 
        
        try{
            usuario = usuarioRepository.findById(id).orElseThrow(() -> new NoDataFoundException("No existe registro con el id " + id));
            usuarioParam.setId(id);
            UsuarioModel newUser = usuarioRepository.save(usuarioParam);
            
            if(newUser != null){
                message = "Modificacion a usuario " + id + " realizada correctamente";
                isOk = true; 
                http = HttpStatus.OK;
            }else{
                message = "No fue posible realizar la modificaion al usuario seleccionado";
            }
        }catch(NoDataFoundException e){
            message = "Sin registros";
            throw e;
        }catch(Exception e){
            throw new GeneralServiceException(e.getMessage());
        }
        return new Response<>(message, isOk, http, usuario);
    }
    
    @Override
    public Response<Object> eliminarEmpleadoId(int id) {
        String message = ""; 
        HttpStatus http = HttpStatus.BAD_REQUEST;
        boolean isOk = false; 
        UsuarioModel user = null; 
        try{
            user = usuarioRepository.findById(id).orElseThrow(() -> new NoDataFoundException("No existe el registro"));
            usuarioRepository.deleteById(id);
            message = "Operacion realizada con exito";
            isOk = true; 
            http = HttpStatus.OK;
        }catch(NoDataFoundException e){
            throw e; 
        }catch(Exception e){
            throw new GeneralServiceException(e.getMessage());
        }
        return new Response<>(message, isOk, http, user);
    }
    
}
