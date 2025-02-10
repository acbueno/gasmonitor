package br.com.acbueno.lins.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import br.com.acbueno.lins.model.GasSensor;

@Repository
public interface GasMonitorRepository extends MongoRepository<GasSensor, String> {

  default List<GasSensor> findLast5Readings() {
    return findAll(Sort.by(Sort.Direction.DESC, "timestamp")).stream().limit(5).toList();
  }

}
