package com.seohan1010.ch8_4.controller;


import com.seohan1010.ch8_4.service.UserService;
import com.seohan1010.ch8_4.to.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;


    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public ResponseEntity<HttpStatus> logout(HttpSession session){

        session.invalidate();
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> login(@RequestBody UserDto userDto, HttpServletRequest request) {
        UserDto user = null;
        HttpSession session = request.getSession();
        try {
            user = userService.findUser(userDto.getEmail());
            //이메일이 null이 아니고, 입력받은 password가 db의 password와 일치하지 않으면
            if (user == null || !user.getPassword().equals(userDto.getPassword())) {

                return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
            }
            setUserInfo(user.getEmail(), user.getName(), user.getPassword(), session);

            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {

            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }

    }// login 메서드의 끝

    private static void setUserInfo(String email, String name, String password, HttpSession session) {

        session.setAttribute("email", email);
        session.setAttribute("name", name);
        session.setAttribute("password", password);
        String email2=(String)session.getAttribute("email");
        System.out.println("<<<<<<<<<<<<<<<<< email2 = " + email2);
        ServletContext servlet = session.getServletContext();
        System.out.println("<<<<<<<<<<<<<< servlet = " + servlet);

    }

}
