package com.example.learn.ServiceInterface;

import com.example.learn.model.UsuarioModel;
import com.example.learn.response.Response;

public interface IUsuarioServiceInterface {
    
    //Crear
    public Response<Object> insertarEmpleado(UsuarioModel usuario); 
    
    //Leer
    public Response<Object> buscarEmpleados(); 
    public Response<Object> buscarEmpleadoId(int id);
    //public Response<Object> buscarEmpleadoNombre(); 
    
    //Actualizar
    public Response<Object> actualizarEmpleado(UsuarioModel usuario, int id); 
    
    //Eliminar
    public Response<Object> eliminarEmpleadoId(int id); 
}
