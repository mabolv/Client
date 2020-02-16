package com.example.Client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.ui.Model;


@Controller
@SpringBootApplication
public class DemoApplication {    
	
	@GetMapping("/users")
    public String calculatorForm(Model model) {
      model.addAttribute("calculator", new Calculator());
      return "menu";
    }

    
    @PostMapping("/add")
    public String calculatorSubmit(@ModelAttribute Calculator ClientBackend) {
      return "result";
    }
    
    @PostMapping("/delete")
    public String calculatorSubmit2(@ModelAttribute Calculator ClientBackend) {
      return "result2";
    }
    
    
    @PostMapping("/users")
    public String calculatorSubmit3(@ModelAttribute Calculator ClientBackend) {
      return "result3";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
