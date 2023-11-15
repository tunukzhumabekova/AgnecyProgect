package peaksoft.repository;

import peaksoft.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    void saveCustomer(Customer customer);

    List<Customer> getAllCustomer();
    Customer getCustomerById(Long id);
    void updateCustomerById(Long id,Customer customer);
    void deletedCustomerById(Long id);
    void assignCustomerToAgency(Long customerId,List<Long> agencyId);

}
