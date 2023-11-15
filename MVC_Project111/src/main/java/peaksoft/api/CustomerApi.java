package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Customer;
import peaksoft.enums.Gender;

import peaksoft.service.AgencyService;

import peaksoft.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers/{agencyId}")
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerService customerService;
    private final AgencyService agencyService;

    @GetMapping()
    public String getAllCustomer(@PathVariable Long agencyId, Model model){
        model.addAttribute("customers",customerService.getAllCustomer());
        model.addAttribute(agencyId);
        return "customer/customerMainPage";
    }

    @GetMapping("/new")
    public String createCustomer(@PathVariable Long agencyId,Model model){
        model.addAttribute("newCustomer",new Customer());
        model.addAttribute(agencyId);
        model.addAttribute("male", Gender.MALE.name());
        model.addAttribute("female", Gender.FEMALE.name());
        return "customer/newCustomer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute ("newCustomer") Customer customer,
                               @PathVariable Long agencyId) {
        customerService.saveCustomer(customer);
        return "redirect:/customers/" + agencyId;
    }

    @DeleteMapping("/delete/{customerId}")
    public String deleteCustomerById(@PathVariable Long agencyId,
                                     @PathVariable Long customerId){
        customerService.deletedCustomerById(customerId);
        return "redirect:/customers/{agencyId}";
    }

    @GetMapping("/edit/{customerId}")
    public String editCustomer(@PathVariable Long agencyId,
                               @PathVariable Long customerId,Model model){
        model.addAttribute("customer",customerService.getCustomerById(customerId));
        return "customer/updateCustomer";
    }
    @PutMapping("/update/{customerId}")
    public String updateCustomer(@PathVariable Long agencyId,
                                 @PathVariable Long customerId,
                                 @ModelAttribute ("customer") Customer customer){
        customerService.updateCustomerById(customerId,customer);
        return "redirect:/customers/{agencyId}";
    }

    @GetMapping("assign/{customerId}")
    public String assign(@PathVariable Long agencyId,
                         @PathVariable Long customerId,Model model){
        model.addAttribute("assignCustomer",customerId);
        model.addAttribute("assignAgency",agencyService.getAllAgency());
        return "customer/assignCustomers";
    }
    @PostMapping("/assignCustomerToAgency/{customerId}")
    public String assignCustomerToAgency(@PathVariable Long customerId,
                                         @PathVariable List<Long> agencyId) {
        customerService.assignCustomerToAgency(customerId, agencyId);
        return "redirect:/customers/{agencyId}";
    }


}