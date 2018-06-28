package by.medvedev.task.controller;

import by.medvedev.task.model.entity.User;
import by.medvedev.task.model.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseBody
    public String getUserPage(@PathVariable String username, HttpSession session) throws JSONException {
        User user = (User) session.getAttribute("user");
        JSONObject json = new JSONObject();
        User byUsername = userRepository.findUserByUsername(username);
        if (user!=null){
            if (!user.getUsername().equals(username)) {//nullptr
                json.put("error", "you have no access to this page");
            } else {
                json.put("your data", user.toString());
            }
        }else{
            json.put("message", "please, auth");
        }
        if (byUsername!=null) json.put("online", byUsername.isOnline());
        return json.toString();
    }

    @GetMapping(value = "/online")
    @ResponseBody
    public String getOnlineUsers() throws JSONException {
        List<User> usersByOnline = userRepository.findUsersByOnline(true);
        JSONObject json = new JSONObject();
        if (!usersByOnline.isEmpty()){
            for (User user : usersByOnline) {
                json.put("username", user.getUsername());
            }
        }else{
            json.put("message", "there are no users online");
        }
        return json.toString();
    }





}
