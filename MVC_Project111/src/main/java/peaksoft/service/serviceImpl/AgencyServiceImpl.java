package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.exception.MyException;
import peaksoft.repository.AgencyRepository;
import peaksoft.service.AgencyService;

import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;

    @Autowired
    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public void saveAgency(Agency agency) {
        agencyRepository.saveAgency(agency);
    }

    @Override
    public List<Agency> getAllAgency() {
        return agencyRepository.getAllAgency();
    }

    @Override
    public Agency getById(Long id) {
        return agencyRepository.getById(id);
    }

    @Override
    public void updateAgencyById(Long id, Agency agency) throws MyException {
        agencyRepository.updateAgencyById(id, agency);
    }

    @Override
    public void deleteById(Long id) {
        agencyRepository.deleteById(id);
    }

    @Override
    public List<Agency> searchAgency(String word) {
        return agencyRepository.searchAgency(word);
    }
}
