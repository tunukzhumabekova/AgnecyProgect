package peaksoft.service.serviceImpl;

import org.springframework.stereotype.Service;
import peaksoft.entity.House;
import peaksoft.repository.HouseRepository;
import peaksoft.service.HouseService;

import java.util.List;
@Service

public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;

    public HouseServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public void saveHouse(Long agencyId, House house) {
        houseRepository.saveHouse(agencyId,house);
    }

    @Override
    public List<House> getAllHouse(Long agencyId,String word) {
        return houseRepository.getAllHouse(agencyId,word);
    }

    @Override
    public House getHouseById(Long id) {
        return houseRepository.getHouseById(id);
    }

    @Override
    public void updateHouseById(Long id, House house) {
        houseRepository.updateHouseById(id,house);
    }

    @Override
    public void deleteHouseById(Long id) {
        houseRepository.deleteHouseById(id);
    }

    @Override
    public List<House> sortHouseByHouseType(String ascOrDesc) {
        return houseRepository.sortHouseByHouseType(ascOrDesc);
    }

    @Override
    public List<House> searchHouse(String word) {
        return houseRepository.searchHouse(word);
    }
}
