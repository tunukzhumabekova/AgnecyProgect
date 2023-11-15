package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.entity.Customer;
import peaksoft.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class CustomerRepositoryIImpl implements CustomerRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void saveCustomer( Customer customer) {
        entityManager.persist(customer);
    }


    @Override
    public List<Customer> getAllCustomer() {
        return entityManager.createQuery("select c from Customer c",Customer.class).getResultList();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class,id);
    }

    @Override
    public void updateCustomerById(Long id, Customer customer) {
        Customer customer1 = entityManager.find(Customer.class, id);
        customer1.setName(customer.getName());
        customer1.setSurname(customer.getSurname());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setDateOfBirth(customer.getDateOfBirth());
        customer1.setGender(customer.getGender());
    }

    @Override
    public void deletedCustomerById(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
    }


        @Override
        public void assignCustomerToAgency(Long customerId, List<Long> agencyId) {
            Customer customer = entityManager.find(Customer.class, customerId);
            List<Agency> agencies = new ArrayList<>();
            for (Long id : agencyId) {
                Agency agency = entityManager.find(Agency.class, id);
                customer.getAgencies().add(agency);
                agency.getCustomers().add(customer);
            }
            entityManager.merge(customer);
            entityManager.merge(agencies);
        }


}
