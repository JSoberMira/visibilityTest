package com.capitol.visiblity.component;

import com.capitol.visiblity.service.VisibilityOrquestatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private VisibilityOrquestatorService visibilityOrquestatorService;

    @Override
    public void run(String... args) {
        log.warn("EXECUTING : command line runner");
        log.info( visibilityOrquestatorService.getVisibilityProducts() );
    }
}