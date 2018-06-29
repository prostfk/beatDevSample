package by.medvedev.task.controller;

import by.medvedev.task.model.entity.User;
import by.medvedev.task.model.repository.UserRepository;
import by.medvedev.task.model.util.StringsUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public String getIndexPage(HttpSession session){
        User user = (User) session.getAttribute("user");
        var json = new JSONObject();
        try {
            json.put("user data", user);
            json.put("date", new Date().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();

    }

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public List<User> getAllList(){
        Iterator<User> allByIdExists = userRepository.findAll().iterator();
        List<User> users = new ArrayList<>();
        while(allByIdExists.hasNext()){
            User current = allByIdExists.next();
            if (StringsUtil.isUserOffline(current.getLastTimeSeen(), new Date().getTime())){
                userRepository.updateOnlineStatus(false, current.getUsername());
            }
            users.add(current);
        }
        return users;
    }



}
