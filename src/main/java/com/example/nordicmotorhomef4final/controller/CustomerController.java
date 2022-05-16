package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.service.CustomerNotFoundException;
import com.example.nordicmotorhomef4final.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/customers/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("newCustomer", new Customer());
        model.addAttribute("pageTitle", "Add new customer");
        return "customers/newCustomerForm";
    }

    @PostMapping("/customers/save")
    public String saveNewCustomer(Customer customer, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Customer has been saved successfully.");
        customerService.saveCustomer(customer);
        return "redirect:customerPage";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Customer customer = customerService.getCustomerById(id);
            model.addAttribute("newCustomer", customer);
            model.addAttribute("pageTitle", "Edit customer (ID: "+ id+" )");
            redirectAttributes.addFlashAttribute("message", "Customer has been saved successfully.");
            return "newCustomerForm";
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:customers/customerPage";
        }
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteCustomerById(id);
            redirectAttributes.addFlashAttribute("message", "Customer ID: "+ id +" has been saved successfully.");
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:customers/customerPage";
    }
}
