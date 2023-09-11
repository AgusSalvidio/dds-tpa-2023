package ar.edu.utn.frba.dds.persistencesystem;

import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import javax.persistence.EntityTransaction;

public class RelationalDatabasePersistenceSystem implements WithSimplePersistenceUnit {
  public void startManagingUser(User anUser) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(anUser);
    transaction.commit();
  }

  public void startManagingUserDetail(UserDetail anUserDetail) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(anUserDetail);
    transaction.commit();
  }

  public List<User> users() {
    return entityManager().createQuery("from " + User.class.getName()).getResultList();
  }

}
