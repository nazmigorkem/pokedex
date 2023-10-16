package obss.pokemon.config;

import obss.pokemon.entity.Role;
import obss.pokemon.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements ApplicationRunner {
    public static final String TRAINER_ROLE = "ROLE_TRAINER";
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    private final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    RoleRepository roleRepository;

    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("DataLoader is running");

        var userRoleExists = roleRepository.existsByNameIgnoreCase(TRAINER_ROLE);
        if (!userRoleExists) {
            var userRole = new Role();
            userRole.setName(TRAINER_ROLE);
            roleRepository.save(userRole);
        }

        var adminRoleExists = roleRepository.existsByNameIgnoreCase(ADMIN_ROLE);
        if (!adminRoleExists) {
            var adminRole = new Role();
            adminRole.setName(ADMIN_ROLE);
            roleRepository.save(adminRole);
        }
    }
}
