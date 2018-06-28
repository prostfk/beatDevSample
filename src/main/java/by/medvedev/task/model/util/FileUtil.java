package by.medvedev.task.model.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

    public static String saveFile(MultipartFile file, String username){
        String path = null;
        if (!file.isEmpty()){
            try{
                byte []bytes = file.getBytes();
                String name = file.getOriginalFilename();
                File directory = new File("src/main/webapp/resources/userFiles/" + username);
                if (!directory.exists()){
                    directory.mkdirs();
                }
                String pathName = directory.getAbsolutePath() + File.separator + name;
                File uploadedFile = new File(pathName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                path = "/resources/userFiles/" + username + "/" + file.getOriginalFilename();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return path;
    }

}
