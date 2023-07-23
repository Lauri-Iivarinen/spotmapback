package lauriiivarinen.spotmapback.domain;

import org.springframework.data.repository.CrudRepository;

public interface SpotRepository extends CrudRepository<Spot, Long> {
	Spot findByName(String name);
	Spot findByUser(User user);
}
