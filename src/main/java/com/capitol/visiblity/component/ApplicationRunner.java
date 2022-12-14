package com.capitol.visiblity.component;

import com.capitol.visiblity.service.VisibilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private VisibilityService visibilityService;

    @Override
    public void run(String... args) {
        log.info("EXECUTING : command line runner");
        visibilityService.getVisibilityProducts();
    }
}