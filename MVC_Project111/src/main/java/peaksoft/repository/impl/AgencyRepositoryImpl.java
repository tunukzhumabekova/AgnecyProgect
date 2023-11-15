package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.exception.MyException;
import peaksoft.repository.AgencyRepository;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class AgencyRepositoryImpl implements AgencyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AgencyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveAgency(Agency agency) {
        boolean isFalse = false;
        for (Agency a : getAllAgency()) {
            if (a.getName().equals(agency.getName())) {
                isFalse = true;
                break;
            }
        }
        if (!isFalse){
            entityManager.merge(agency);
        }else {
            try {
                throw new MyException("Not found!!!");
            } catch (MyException e) {
                System.err.println(e.getMessage());
            }
        }


    }

    @Override
    public List<Agency> getAllAgency() {
        return entityManager.createQuery("select a from Agency a", Agency.class).getResultList();

    }

    @Override
    public Agency getById(Long id) {
        try {
            boolean isFalse = false;
            for (Agency a : getAllAgency()) {
                if (Objects.equals(a.getId(), id)) {
                    isFalse = true;
                }
                if (isFalse) {
                    return entityManager.createQuery("select a from Agency a where a.id=:id", Agency.class)
                            .setParameter("id", id).getSingleResult();
                } else {
                    throw new MyException("Agency by ID : " + id + " not found!");
                }
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateAgencyById(Long id, Agency agency) {
        Agency agency1 = entityManager.find(Agency.class, id);
        if (agency1 == null) {
            try {
                throw new MyException(String.format("User with id %s not found", id));
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }
        agency1.setName(agency.getName());
        agency1.setCountry(agency.getCountry());
        agency1.setPhoneNumber(agency.getPhoneNumber());
        agency1.setEmail(agency.getEmail());
        agency1.setImageLink(agency.getImageLink());
        entityManager.merge(agency);
    }

    @Override
    public void deleteById(Long id) {
        try {
            Agency agency = entityManager.find(Agency.class, id);
            if (agency.getId().equals(id)) {
                entityManager.remove(agency);
            } else {
                throw new MyException("Agent of this"
                        + id + "was not found");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Agency> searchAgency(String word) {
        return entityManager.createQuery("select u from Agency u where u.name like :word", Agency.class)
                .setParameter("word", "%" + word + "%").getResultList();
    }
}
