package com.kevin.drools.demo.basic.stateful;

import com.kevin.drools.demo.common.DroolsUtil;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin
 * @date 2014/4/22.
 */
public class FireAlarmMain {
    private static final Logger log = LoggerFactory.getLogger(FireAlarmMain.class);

    public static void main(String[] args) {
        KieSession kieSession = DroolsUtil.getInstance().newKieSessionWithLogging("firmAlarmKS", log);
        String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
        Map<String,Room> name2room = new HashMap<String,Room>();
        for( String name: names ){
            Room room = new Room(name);
            name2room.put(name, room);
            kieSession.insert(room);
            Sprinkler sprinkler = new Sprinkler(room);
            kieSession.insert(sprinkler);
        }
        kieSession.fireAllRules();

        Fire fire = new Fire(name2room.get("livingroom"));
        FactHandle fireHandle = kieSession.insert(fire);
        kieSession.fireAllRules();

        DroolsUtil.getInstance().disposeSessionWithLogging(kieSession);
    }
}
