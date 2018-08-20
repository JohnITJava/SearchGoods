package com.SearchGoods.SearchGoods.controller;

import com.SearchGoods.SearchGoods.model.Cars;
import com.SearchGoods.SearchGoods.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/index")
//@SessionAttributes("roles")
public class AppController {

    @Autowired
    CarsService carsService;

    //Метод адресации на index.jsp
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * This method will list all existing cars.
     */
    @RequestMapping(value = { "/list" }, method = RequestMethod.GET)
    public String listCars(ModelMap model) {

        List<Cars> cars = carsService.findAllCars();
        model.addAttribute("cars", cars);
        return "carslist";
    }

    /**
     * This method will provide the medium to add a new car.
     */
    @RequestMapping(value = { "/newcar" }, method = RequestMethod.GET)
    public String newCar(ModelMap model) {
        Cars car = new Cars();
        model.addAttribute("car", car);
        model.addAttribute("edit", false);
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving car in database. It also validates the car input
     */
    @RequestMapping(value = { "/newcar" }, method = RequestMethod.POST)
    public String saveCar(@Valid Cars car, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        carsService.saveCar(car);

        model.addAttribute("success", "Car " + car.getName() + " registered successfully");
        //return "success";
        return "registrationsuccess";
    }


    /**
     * This method will provide the medium to update an existing car.
     */
    @RequestMapping(value = { "/edit-car-{id}" }, method = RequestMethod.GET)
    public String editCar(@PathVariable Integer id, ModelMap model) {
        Cars car = carsService.findById(id);
        model.addAttribute("car", car);
        model.addAttribute("edit", true);
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-car-{name}" }, method = RequestMethod.POST)
    public String updateCar(@Valid Cars car, BindingResult result,
                             ModelMap model, @PathVariable String name) {

        if (result.hasErrors()) {
            return "registration";
        }

        carsService.updateCar(car);

        model.addAttribute("success", "Car " + car.getName() + " updated successfully");
        return "registrationsuccess";
    }


    /**
     * This method will delete an car by it's Name value.
     */
    @RequestMapping(value = { "/delete-car-{name}" }, method = RequestMethod.GET)
    public String deleteCar(@PathVariable String name) {
        carsService.deleteCarByName(name);
        return "redirect:/list";
    }


    /**
     * This method will provide UserProfile list to views

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }*/

}