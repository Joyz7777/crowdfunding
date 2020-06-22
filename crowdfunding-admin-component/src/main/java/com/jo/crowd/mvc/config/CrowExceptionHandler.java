package com.jo.crowd.mvc.config;


import com.google.gson.Gson;
import com.jo.crowd.util.*;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CrowExceptionHandler {



    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView userAlreadyExistsHandler(UserAlreadyExistsException e,
                                               HttpServletRequest request,
                                               HttpServletResponse response) throws IOException {
        String viewName = "admin-add";
        return  commonExceptionHandler(viewName, request, response, e);

    }



    @ExceptionHandler(AccessForbiddenException.class)
    public ModelAndView AccessForbiddenHandler(AccessForbiddenException e,
                                               HttpServletRequest request,
                                               HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        return  commonExceptionHandler(viewName, request, response, e);

    }




    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView loginFailedHandler(LoginFailedException e,
                                           HttpServletRequest request,
                                           HttpServletResponse response
                                            ) throws IOException {
        String viewName = "admin-login.";
        return  commonExceptionHandler(viewName, request, response, e);
    }

    public ModelAndView commonExceptionHandler(String viewName, HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        if (CrowdUtil.judgeRequestType(request)) {

            // ajax 异常
            ResultEntity<String> resultEntity = new ResultEntity<String>();
            resultEntity.setMessage(e.getMessage());
            resultEntity.setResult(e.toString());
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);

            response.getWriter().write(String.valueOf(json));
            return null;

        } else {

            ModelAndView view = new ModelAndView(viewName);
            view.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, e);

            return view;

        }


    }
}
