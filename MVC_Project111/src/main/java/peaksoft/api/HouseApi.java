package peaksoft.api;

import jakarta.persistence.PersistenceContext;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.House;
import peaksoft.service.HouseService;

@Controller
@RequestMapping("/houses/{agencyId}")
public class HouseApi {

    private final HouseService houseService;

    @Autowired
    public HouseApi(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping
    public String getAllHouse(@PathVariable("agencyId") Long agencyId, String word, Model model) {
        model.addAttribute("houses", houseService.getAllHouse(agencyId,word));
        model.addAttribute("word", word);
        model.addAttribute( "ids",agencyId);
        System.out.println("agency id : "+agencyId);
        return "house/houseMainPage";
    }

    @GetMapping("/new")
    public String createHouse(@PathVariable Long agencyId ,Model model) {
        model.addAttribute("agencyId", agencyId);
        model.addAttribute("house", new House());
        return "house/newHouse";
    }

    @PostMapping("/save")
    public String saveHouse(@PathVariable Long agencyId,@ModelAttribute("house")House house) {
        houseService.saveHouse(agencyId, house);
        return "redirect:/houses/" + agencyId;
    }

//    @GetMapping("{id}")
//    public String getById(@PathVariable("id") Long id , Model model) {
//        model.addAttribute("houses", houseService.getHouseById(id));
//        return "house/getByIdHouse";
//    }

    @PostMapping("/{houseId}edit")
    public String updateHouse(@PathVariable("agencyId") Long id, Model model,@PathVariable Long agencyId) {
      model.addAttribute("house",houseService.getHouseById(id));
      model.addAttribute("agencyId",agencyId);
        return "house/updateHouse";
    }

    @PostMapping("/{houseId}/update")
    public String saveUpdate(@ModelAttribute("house") House house,
                             @PathVariable("houseId") Long id,
                             @PathVariable("agencyId")Long agencyId) {
        houseService.updateHouseById(id, house);
        return "redirect:/houses/" + agencyId;
    }

    @DeleteMapping("/{houseId}/deleteHouse")
    public String delete(@PathVariable Long houseId,@PathVariable("agencyId") Long agencyId) {
        houseService.deleteHouseById(houseId);
        return "redirect:/houses/"+agencyId;
    }

}

