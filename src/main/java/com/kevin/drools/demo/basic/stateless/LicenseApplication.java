package com.kevin.drools.demo.basic.stateless;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;


/**
 * @author Kevin
 * @date 2014/4/19.
 */
public class LicenseApplication {
    private static final Logger log = LoggerFactory.getLogger(LicenseApplication.class);

    public static void main(String[] args) {
        LicenseApplication.execute();
    }

    public static void execute() {
        KieServices kServices = KieServices.Factory.get();
        KieFileSystem kfs = kServices.newKieFileSystem();
        kfs.write(kServices.getResources().newClassPathResource("com/kevin/drools/demo/basic/stateless/licenseApplication.drl"));
        KieBuilder kBuilder = kServices.newKieBuilder(kfs);
        kBuilder.buildAll();
        KieContainer kContainer = kServices.newKieContainer(kServices.getRepository().getDefaultReleaseId());
        StatelessKieSession kSession = kContainer.newStatelessKieSession();
        kSession.setGlobal("log", log);

        Applicant john = new Applicant("John", 16);
        Application applicationOfJohn =   new Application();

        log.info("Before executing rule: [" + applicationOfJohn.isValid() + ']');
        kSession.execute(Arrays.asList(new Object[] {john, applicationOfJohn}));
        log.info("After executing rule: [" + applicationOfJohn.isValid() + ']');

        Applicant brain = new Applicant("Brain", 19);
        Application applicationOfBrain =   new Application();

        log.info("Before executing rule: [" + applicationOfBrain.isValid() + ']');
        kSession.execute(Arrays.asList(new Object[] {brain, applicationOfBrain}));
        log.info("After executing rule: [" + applicationOfBrain.isValid() + ']');

        Application secondApplicationOfBrain =   new Application();
        secondApplicationOfBrain.setDateApplied(new Date());
        log.info("Before executing rule: [" + secondApplicationOfBrain.isValid() + ']');
        kSession.execute(Arrays.asList(new Object[] {brain, secondApplicationOfBrain}));
        log.info("After executing rule: [" + secondApplicationOfBrain.isValid() + ']');
    }
}
