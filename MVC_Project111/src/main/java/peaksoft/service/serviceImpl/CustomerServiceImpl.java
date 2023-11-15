package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Customer;
import peaksoft.repository.CustomerRepository;
import peaksoft.service.CustomerService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.saveCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.getAllCustomer();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public void updateCustomerById(Long id, Customer customer) {
        customerRepository.updateCustomerById(id,customer);
    }

    @Override
    public void deletedCustomerById(Long id) {
          customerRepository.deletedCustomerById(id);
    }

    @Override
    public void assignCustomerToAgency(Long customerId, List<Long> agencyId) {
        customerRepository.assignCustomerToAgency(customerId,agencyId);
    }


}
