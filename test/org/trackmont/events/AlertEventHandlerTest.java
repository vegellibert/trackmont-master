package org.trackmont.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.trackmont.BaseTest;
import org.trackmont.model.Event;
import org.trackmont.model.Position;

public class AlertEventHandlerTest extends BaseTest {

    @Test
    public void testAlertEventHandler() throws Exception {
        
        AlertEventHandler alertEventHandler = new AlertEventHandler();
        
        Position position = new Position();
        position.set(Position.KEY_ALARM, Position.ALARM_GENERAL);
        Map<Event, Position> events = alertEventHandler.analyzePosition(position);
        assertNotNull(events);
        Event event = events.keySet().iterator().next();
        assertEquals(Event.TYPE_ALARM, event.getType());
    }

}
