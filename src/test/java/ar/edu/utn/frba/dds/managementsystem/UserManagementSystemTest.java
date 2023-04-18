package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.userdetails.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserManagementSystemTest {
    private UserDetails userDetails() {
        return new UserDetails("Hugo", "Ibarra", "ibarraneta@gmail.com");
    }

    private PersistenceSystem persistenceSystem() {
        return new PersistenceSystem();
    }

    @Test
    @DisplayName("Start managing an user")
    public void startManagingAnUserTest() throws Exception {

        UserManagementSystem userManagementSystem = UserManagementSystem.workingWith(this.persistenceSystem());
        User user = User.composedBy("ibarranetaYPF", "theBestPassword", this.userDetails());

        userManagementSystem.startManaging(user);

        Assertions.assertEquals(userManagementSystem.user(user), user);

    }

}
