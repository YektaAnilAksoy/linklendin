package com.yektaanil.linklendin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.yektaanil.linklendin.entity.User;
import com.yektaanil.linklendin.utility.EntityTestUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void givenUser_whenSave_thenShouldReturnSavedEntity() {
        final User user = EntityTestUtility.getUser();
        final User savedUser = userRepository.save(user);
        assertEquals(user, savedUser);
    }

}
