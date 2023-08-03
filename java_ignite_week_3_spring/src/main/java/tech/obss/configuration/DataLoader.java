package tech.obss.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tech.obss.entity.Role;
import tech.obss.repository.RoleRepository;

@Component
public class DataLoader implements ApplicationRunner {
    public static final String USER_ROLE = "ROLE_USER";
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    private final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    RoleRepository roleRepository;

    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("DataLoader is running");

        var userRoleExists = roleRepository.existsByName(USER_ROLE);
        if (!userRoleExists) {
            var userRole = new Role();
            userRole.setName(USER_ROLE);
            roleRepository.save(userRole);
        }

        var adminRoleExists = roleRepository.existsByName(ADMIN_ROLE);
        if (!adminRoleExists) {
            var adminRole = new Role();
            adminRole.setName(ADMIN_ROLE);
            roleRepository.save(adminRole);
        }
    }
}