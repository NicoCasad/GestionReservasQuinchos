package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.FotoQuincho;
import com.Grupo2.ReservaQuinchos.entidades.Quincho;
import com.Grupo2.ReservaQuinchos.excepciones.MyException;
import com.Grupo2.ReservaQuinchos.repositorio.FotoQuinchoRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoQuinchoService {

    @Autowired
    FotoQuinchoRepository fotoQuinchoRepository;

    @Transactional
    public void registrar(String nombre, Quincho quincho) throws MyException{
        validar(nombre, quincho);
        
        FotoQuincho fotoQuincho = new FotoQuincho();
        fotoQuincho.setNombre(nombre);
        fotoQuincho.setQuincho(quincho);
        
        fotoQuinchoRepository.save(fotoQuincho);
    }
    
    public void validar(String nombre, Quincho quincho) throws MyException{
        if(nombre.isEmpty() || nombre == null){
            throw new MyException("el nombre No puede ser nulo o vacio");
        }
        if(quincho == null){
            throw new MyException("el quincho No puede ser nulo");
        }
    }

    public void guardarArchivoFoto(MultipartFile file,String ruta) throws IOException{
            StringBuilder fileNames = new StringBuilder();

            //Path fileNameAndPath = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\Images", file.getOriginalFilename());
            Path fileNameAndPath = Paths.get(System.getProperty("user.dir") + ruta, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());

            Files.write(fileNameAndPath, file.getBytes());
    }

    public List<FotoQuincho> listaDeFotosDeQuinchos(Long id_quincho){
      return fotoQuinchoRepository.buscarPorIDquincho(id_quincho);
    }
    
    @Transactional
    public void eliminar(Long id){
        fotoQuinchoRepository.deleteById(id);
    }

}
