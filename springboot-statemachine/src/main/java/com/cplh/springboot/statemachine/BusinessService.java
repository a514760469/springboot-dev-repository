package com.cplh.springboot.statemachine;

import com.cplh.springboot.statemachine.config.Events;
import com.cplh.springboot.statemachine.config.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

/**
 * @author zhanglifeng
 * @since 2020-06-03 14:37
 */
@Service
public class BusinessService {

    @Autowired
    StateMachine<States, Events> stateMachine;

    public void stateChange() {
        stateMachine.sendEvent(Events.E1);
        stateMachine.sendEvent(Events.E2);
    }

}
