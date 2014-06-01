package com.kevin.drools.demo.common;

import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin
 * @date 2014/5/12.
 */
public class TrackingAgendaEventListener extends DefaultAgendaEventListener {

    private static Logger log = LoggerFactory.getLogger(TrackingAgendaEventListener.class);

    private List<String> activationList = new ArrayList<String>();

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        super.beforeMatchFired(event);

        Rule rule = event.getMatch().getRule();

        String ruleName = rule.getPackageName() + " : " + rule.getName();
        activationList.add(ruleName);

        Map<String, Object> ruleMetaDataMap = rule.getMetaData();

        StringBuilder stringBuilder = new StringBuilder("Executing rule: [" + ruleName + ']');

        if (ruleMetaDataMap.size() > 0) {
            stringBuilder.append(" with [");
            stringBuilder.append(ruleMetaDataMap.size());
            stringBuilder.append("] meta-data: {");
            for (Iterator<String> iter = ruleMetaDataMap.keySet().iterator(); iter.hasNext();) {
                String key = iter.next();
                stringBuilder.append("[key = ");
                stringBuilder.append(key);
                stringBuilder.append(", value = ");
                stringBuilder.append(ruleMetaDataMap.get(key));
                if (iter.hasNext()) {
                    stringBuilder.append("], ");
                } else {
                    stringBuilder.append("]");
                }
            }
            stringBuilder.append('}');
        }

        log.info(stringBuilder.toString());
    }

    public boolean isRuleFired(String ruleName) {
        for (String firedRuleName : activationList) {
            if (firedRuleName != null && firedRuleName.equals(ruleName)) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        activationList.clear();
    }

    public final List<String> getActivationList() {
        return activationList;
    }

    public String activationsToString() {
        if (activationList.size() == 0) {
            return "No activations occurred.";
        } else {
            StringBuilder stringBuilder = new StringBuilder("Activations: \n");
            for (Iterator<String> iter = activationList.iterator(); iter.hasNext();) {
                stringBuilder.append("\t[");
                stringBuilder.append(iter.next());
                stringBuilder.append(']');
                if (iter.hasNext()) {
                    stringBuilder.append("\n");
                }
            }
            return stringBuilder.toString();
        }
    }

}
