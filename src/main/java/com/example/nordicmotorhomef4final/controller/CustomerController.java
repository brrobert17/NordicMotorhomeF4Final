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

    // retrieving and passing the information needed for the customerPage.html
    @GetMapping("customers/customerPage")
    public String showCustomerList(Model model) {

        List<Customer> customerList = customerService.listAll();
        model.addAttribute("customerList", customerList);
        return "customers/customerPage";
    }

    // after submitting the 'keyword' of the search from, the results are handled and passed on
    @PostMapping("customers/search")
    public String searchCustomer(Model model, @RequestParam("keyword") String keyword) {
        List<Customer> searchResult = customerService.searchCustomer(keyword);
        model.addAttribute("customerList", searchResult);
        return "customers/customerPage";
    }

    // mapping for the newCustomerForm.html page with an empty 'Customer' object that gets filled with information by the html form
    @GetMapping("/customers/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("newCustomer", new Customer());
        model.addAttribute("pageTitle", "Add new customer");
        return "customers/newCustomerForm";
    }

    // as the form of the newCustomerForm.html is submitted the complete 'Customer' object is saved
    // the confirmation message gets passed onto the customerPage.html as a 'RedirectAttribute'
    @PostMapping("/customers/save")
    public String saveNewCustomer(Customer customer, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Customer has been saved successfully.");
        customerService.saveCustomer(customer);
        return "redirect:/customers/customerPage";
    }

    // as a 'Customer' object is chosen for editing its 'customerId' is passed on as a 'PathVariable'
    // the 'Customer' object is retrieved by the 'id' and passed onto the form of the newCustomerForm.html file

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

    // as a 'Customer' object is chosen for deleting its 'customerId' is passed on as a 'PathVariable'
    // the 'Customer' object is deleted by the 'id' and a confirmation message is passed onto the customerPage.html file
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


}
