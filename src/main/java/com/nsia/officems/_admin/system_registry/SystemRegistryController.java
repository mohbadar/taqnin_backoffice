package com.nsia.officems._admin.system_registry;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._identity.authentication.user.UserService;

@RestController
@RequestMapping({ "/api/sys_reg" })
public class SystemRegistryController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemRegistryService systemRegistryService;

    @Autowired
    private UserService userService;

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping
    public SystemRegistry create(@Valid @RequestBody SystemRegistry systemRegistry) {
        logger.info("Entry SystemRegistry>create() - POST");
        // systemRegistry.setEnvSlug(userService.getCurrentEnv());
        return systemRegistryService.create(systemRegistry);
    }

    @GetMapping(path = { "/{sys_reg_name}/content" })
    public String getContentByName(@PathVariable("sys_reg_name") String sysRegName) {
        logger.info("Entry SystemRegistry>findOneByName() - GET");

        List<SystemRegistry> sysRegistries = systemRegistryService.findByName(sysRegName);
        if(sysRegistries.size() > 0) {
            return sysRegistries.get(0).getContent();
        }
        return null;
    }
    
    @GetMapping(path = { "/{id}" })
    public SystemRegistry findOne(@PathVariable("id") Long id) {
        logger.info("Entry SystemRegistry>findOne() - GET");
        return systemRegistryService.findById(id);
    }

    @PutMapping(path = { "/{id}" })
    public boolean update(@PathVariable("id") Long id, @RequestBody SystemRegistry systemRegistry) {
        logger.info("Entry SystemRegistry>update() - PUT");
        return systemRegistryService.update(id, systemRegistry);
    }

    // @GetMapping
    // public List findAll() {
    //     logger.info("Entry SystemRegistry>findAll() - GET");
    //     String envSlug = userService.getCurrentEnv();
    //     return systemRegistryService.findAllByEnv(envSlug);
    // }

    @RequestMapping(value = "/{id}/xml", method = RequestMethod.GET)
    public String getFormContent(@PathVariable("id") Long id) throws IOException {
        logger.info("Entry SystemRegistry>getFormContent() - GET");
        return systemRegistryService.findById(id).getContent();
    }

    @GetMapping(value = "{slug}/dashboard")
    public String getContentByRegistrySlug(@PathVariable("slug") String slug) {
        logger.info("Entry SystemRegistry>getDashboard() - GET");
        return systemRegistryService.getContentByRegistrySlug(slug);
    }

}
