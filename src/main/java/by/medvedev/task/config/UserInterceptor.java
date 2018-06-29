package by.medvedev.task.config;

import by.medvedev.task.controller.MainController;
import by.medvedev.task.model.entity.User;
import by.medvedev.task.model.repository.UserRepository;
import by.medvedev.task.model.util.StringsUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Iterator;

@Component
@Configurable
public class UserInterceptor implements HandlerInterceptor {




}
