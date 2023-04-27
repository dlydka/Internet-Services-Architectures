package pl.edu.pg.eti.kask.rpg.user.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;
import pl.edu.pg.eti.kask.rpg.user.event.dto.PostTowerRequest;

@Repository
public class TowerEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public TowerEventRepository(@Value("${rpg.mages.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Tower tower) {
        restTemplate.delete("/towers/{name}", tower.getName());
    }

    public void create(Tower tower) {
        restTemplate.postForLocation("/towers", PostTowerRequest.entityToDtoMapper().apply(tower));
    }
}
