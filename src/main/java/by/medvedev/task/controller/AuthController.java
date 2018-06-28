package by.medvedev.task.controller;

import by.medvedev.task.model.entity.User;
import by.medvedev.task.model.repository.UserRepository;
import by.medvedev.task.model.util.FileUtil;
import by.medvedev.task.model.util.StringsUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage(){
        return new ModelAndView("registration", "user", new User());
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public String postRegistration(User user, @RequestParam("file") MultipartFile file) throws JSONException {
        User byUsername = userRepository.findUserByUsername(user.getUsername());
        JSONObject json = new JSONObject();
        if (byUsername==null && user.getUsername().length() >= 4 && user.getPassword().equals(user.getSubmitPassword())){
            user.setPassword(StringsUtil.hash(user.getPassword()));
            user.setPathToProfilePhoto(FileUtil.saveFile(file,user.getUsername()));
            user.setOnline(false);
            user.setLastTimeSeen(new Date().getTime());
            userRepository.save(user);
            json.put("id", userRepository.findUserByUsername(user.getUsername()).getId());
        }else{
            json.put("id", "a user with that username already exists");
        }
        return json.toString();
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView getAuthPage(){
        return new ModelAndView("auth", "user", new User());
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public String postAuth(User user, HttpSession session) throws JSONException {
        User byUsername = userRepository.findUserByUsername(user.getUsername());
        JSONObject jsonObject = new JSONObject();
        if (byUsername == null){
            jsonObject.put("message", "No such user");
            return jsonObject.toString();
        }
        if (StringsUtil.hash(user.getPassword()).equals(byUsername.getPassword())){
            session.setAttribute("user", byUsername);
            byUsername.setOnline(true);
            userRepository.updateOnlineStatus(byUsername.isOnline(), byUsername.getUsername());
            userRepository.updateLastVisitedTime(new Date().getTime(), byUsername.getUsername());
            try{
                jsonObject.put("id",byUsername.getId());
                jsonObject.put("username", byUsername.getUsername());
                jsonObject.put("mail",byUsername.getMail());
                jsonObject.put("profilePath", byUsername.getPathToProfilePhoto());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject.toString();
        }
        jsonObject.put("auth error", "check your data");
        return jsonObject.toString();
//        return "redirect:/auth";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpSession session) throws JSONException {
        JSONObject json = new JSONObject();
        User user = (User) session.getAttribute("user");
        User byUsername = userRepository.findUserByUsername(user.getUsername());
        if (byUsername!=null){
            byUsername.setOnline(false);
            byUsername.setLastTimeSeen(new Date().getTime());
            userRepository.updateOnlineStatus(byUsername.isOnline(), byUsername.getUsername());
            session.removeAttribute("user");
            json.put("message", "you successfully leaved your account");
        }else{
            json.put("message", "you are not authorized");
        }
        return json.toString();
    }


}
