package com.kevin.drools.demo.agenda;

import com.kevin.drools.demo.common.DroolsUtil;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Kevin
 * @date 2014/5/10.
 */
public class CashFlowMain {
    private static final Logger log = LoggerFactory.getLogger(CashFlowMain.class);

    public static void main(String[] args) throws Exception {

        KieSession kieSession = DroolsUtil.getInstance().newKieSessionWithLogging("cashFlowKS", log);

        AccountPeriod acp = new AccountPeriod(date( "2013-01-01"), date( "2013-03-31"));

        Account ac = new Account(1, 0);

        CashFlow cf1 = new CashFlow(date( "2013-01-12"), 100, CashFlowType.CREDIT, 1 );
        CashFlow cf2 = new CashFlow(date( "2013-02-2"), 200, CashFlowType.DEBIT, 1 );
        CashFlow cf3 = new CashFlow(date( "2013-05-18"), 50, CashFlowType.CREDIT, 1 );
        CashFlow cf4 = new CashFlow(date( "2013-03-07"), 75, CashFlowType.CREDIT, 1 );

        FactHandle fh = kieSession.insert(acp);
        kieSession.insert(ac);

        kieSession.insert(cf1);
        kieSession.insert(cf2);
        kieSession.insert(cf3);
        kieSession.insert(cf4);

        Agenda agenda = kieSession.getAgenda();
        agenda.getAgendaGroup( "report" ).setFocus();
        agenda.getAgendaGroup( "calculation" ).setFocus();

        kieSession.fireAllRules();

        acp.setStart(date("2013-04-01"));
        acp.setEnd(date("2013-06-31"));
        kieSession.update(fh, acp);

        kieSession.fireAllRules();

        DroolsUtil.getInstance().disposeSessionWithLogging(kieSession);



    }

    public static Date date(String str) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(str);
    }
}
