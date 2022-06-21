package com.ilionx.carapp.api;

import com.ilionx.carapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("api/helloworld")
public class HelloworldController {

    @Autowired
    private CarService carService;

}
