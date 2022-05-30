package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("customers/customerPage")
    public String showCustomerList(Model model) {

        List<Customer> customerList = customerService.listAll();
        model.addAttribute("customerList", customerList);
        return "customers/customerPage";
    }

    @PostMapping("customers/search")
    public String searchCustomer(Model model, @RequestParam("keyword") String keyword) {
        List<Customer> searchResult = customerService.searchCustomer(keyword);
        model.addAttribute("customerList", searchResult);
        return "customers/customerPage";
    }

    @GetMapping("/customers/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("newCustomer", new Customer());
        model.addAttribute("pageTitle", "Add new customer");
        return "customers/newCustomerForm";
    }

    @PostMapping("/customers/save")
    public String saveNewCustomer(Customer customer, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Customer has been saved successfully.");
        customerService.saveCustomer(customer);
        return "redirect:/customers/customerPage";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Customer customer = customerService.getCustomerById(id);
            model.addAttribute("newCustomer", customer);
            model.addAttribute("pageTitle", "Edit customer (ID: " + id + " )");
            redirectAttributes.addFlashAttribute("message", "Customer has been saved successfully.");
            return "customers/newCustomerForm";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/customers/customerPage";
        }
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteCustomerById(id);
            redirectAttributes.addFlashAttribute("message", "Customer ID: " + id + " has been saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/customers/customerPage";
    }

    @GetMapping("customers/chooseCustomer")
    public String chooseFromCustomerList(Model model) {

        List<Customer> customerList = customerService.listAll();
        model.addAttribute("customerList", customerList);
        return "customers/chooseCustomer";
    }






    //test functions
    @GetMapping("/customers/addToBooking/{id}")
    public String addCustomerToBookingTest(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {

        try {
            Customer customer = customerService.getCustomerById(id);
            model.addAttribute("customerToAdd", customer);
            redirectAttributes.addFlashAttribute("message", "Customer ID: " + id + " has been added to the booking successfully.");
            return "customers/addCustomerTest";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/customers/customerPage";
        }
    }

    @GetMapping("customers/addMore/{id}")
    public String addMoreTest(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        List<Customer> customerList = customerService.listAll();
        model.addAttribute("customerList", customerList);
        try {
            Customer customer1 = customerService.getCustomerById(id);
            model.addAttribute("customerToAdd", customer1);
            return "customers/choose2CustomerTest";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/customers/customerPage";
        }


    }

    @GetMapping("/customers/addCustomer2Test/{id}/{id2}")
    public String addCustomer2ToBookingTest(@PathVariable("id") Integer id, @PathVariable("id2") Integer id2, Model model, RedirectAttributes redirectAttributes) {

        try {
            Customer customer = customerService.getCustomerById(id);
            Customer customer2 = customerService.getCustomerById(id2);
            model.addAttribute("customerToAdd", customer);
            model.addAttribute("customerToAdd2", customer2);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/customers/customerPage";
        }

        return "customers/addCustomer2Test";
    }


}
