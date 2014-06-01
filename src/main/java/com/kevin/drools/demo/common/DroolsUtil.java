package com.kevin.drools.demo.common;

import org.kie.api.KieServices;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;

import java.util.Collection;

/**
 * @author Kevin
 * @date 2014/5/8.
 */
public class DroolsUtil {
    private KieServices kieServices;
    private KieContainer kieContainer;

    private DroolsUtil () {
        kieServices = KieServices.Factory.get();
        kieContainer = kieServices.getKieClasspathContainer();
    }

    private static final class DroolsUtilsContainer {
        private static final DroolsUtil instance = new DroolsUtil();
    }

    public static DroolsUtil getInstance() {
        return DroolsUtilsContainer.instance;
    }

    public KieServices getKieServices() {
        return kieServices;
    }

    public void setKieServices(KieServices kieServices) {
        this.kieServices = kieServices;
    }

    public KieContainer getKieContainer() {
        return kieContainer;
    }

    public void setKieContainer(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public KieSession newKieSession() {
        return newKieSession(null);
    }

    public KieSession newKieSession(String name) {
        if (name == null || name.isEmpty()) {
            return kieContainer.newKieSession();
        } else {
            return kieContainer.newKieSession(name);
        }
    }

    public KieSession newKieSessionWithLogging(Logger log) {
        return newKieSessionWithLogging(null, log);
    }

    public KieSession newKieSessionWithLogging(String name, Logger log) {
        KieSession kieSession = newKieSession(name);
        kieSession.addEventListener(new TrackingAgendaEventListener());
        kieSession.setGlobal("log", log);
        return kieSession;
    }

    public StatelessKieSession newStatelessKieSession() {
        return newStatelessKieSession(null);
    }

    public StatelessKieSession newStatelessKieSession(String name) {
        if (name == null || name.isEmpty()) {
            return kieContainer.newStatelessKieSession();
        } else {
            return kieContainer.newStatelessKieSession(name);
        }
    }
    public StatelessKieSession newStatelessKieSessionWithLogging(Logger log) {
        return newStatelessKieSessionWithLogging(null, log);
    }

    public StatelessKieSession newStatelessKieSessionWithLogging(String name, Logger log) {
        StatelessKieSession statelessKieSession = newStatelessKieSession(name);
        statelessKieSession.addEventListener(new TrackingAgendaEventListener());
        statelessKieSession.setGlobal("log", log);
        return statelessKieSession;
    }

    public void logActivations(KieSession session) {
        if (session == null) {
            return;
        }

        Logger log = (Logger) session.getGlobal("log");

        logActivations(session, log);
    }

    public void logActivations(Object session, Logger log) {
        if (log == null || session == null) {
            return;
        }

        Collection<AgendaEventListener> listeners;
        if (session instanceof KieSession) {
            listeners = ((KieSession) session).getAgendaEventListeners();
        } else if (session instanceof StatelessKieSession) {
            listeners = ((StatelessKieSession) session).getAgendaEventListeners();
        } else {
            log.info("TrackingAgendaEventListener is disabled");
            return;
        }

        TrackingAgendaEventListener listener = null;
        for (AgendaEventListener agendaEventListener : listeners) {
            if (agendaEventListener instanceof TrackingAgendaEventListener) {
                listener = (TrackingAgendaEventListener) agendaEventListener;
                break;
            }
        }
        if (listener == null) {
            log.info("TrackingAgendaEventListener is disabled");
        } else {
            log.info(listener.activationsToString());
        }
    }

    public void disposeSession(KieSession session) {
        session.dispose();
    }

    public void disposeSessionWithLogging(KieSession session) {
        logActivations(session);
        disposeSession(session);
    }

}
