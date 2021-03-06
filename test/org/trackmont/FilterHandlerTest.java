package org.trackmont;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.trackmont.database.IdentityManager;
import org.trackmont.model.Device;
import org.trackmont.model.Position;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FilterHandlerTest {

    static {
        Context.init(new IdentityManager() {

            private Device createDevice() {
                Device device = new Device();
                device.setId(1);
                device.setName("test");
                device.setUniqueId("123456789012345");
                return device;
            }

            @Override
            public Device getById(long id) {
                return createDevice();
            }

            @Override
            public Device getByUniqueId(String uniqueId) {
                return createDevice();
            }
            
            @Override
            public Position getLastPosition(long deviceId) {
                return null;
            }
            
            @Override
            public boolean isLatestPosition(Position position) {
                return true;
            }

            @Override
            public boolean lookupAttributeBoolean(
                    long deviceId, String attributeName, boolean defaultValue, boolean lookupConfig) {
                return defaultValue;
            }

            @Override
            public String lookupAttributeString(
                    long deviceId, String attributeName, String defaultValue, boolean lookupConfig) {
                return "alarm,result";
            }

            @Override
            public int lookupAttributeInteger(
                    long deviceId, String attributeName, int defaultValue, boolean lookupConfig) {
                return defaultValue;
            }

            @Override
            public long lookupAttributeLong(
                    long deviceId, String attributeName, long defaultValue, boolean lookupConfig) {
                return defaultValue;
            }

        });
    }

    private FilterHandler filtingHandler;
    private FilterHandler passingHandler;

    @Before
    public void setUp() {
        passingHandler = new FilterHandler();
        filtingHandler = new FilterHandler();
        filtingHandler.setFilterInvalid(true);
        filtingHandler.setFilterZero(true);
        filtingHandler.setFilterDuplicate(true);
        filtingHandler.setFilterFuture(5 * 60);
        filtingHandler.setFilterApproximate(true);
        filtingHandler.setFilterStatic(true);
        filtingHandler.setFilterDistance(10);
        filtingHandler.setFilterMaxSpeed(500);
        filtingHandler.setSkipLimit(10);
    }

    @After
    public void tearDown() {
        filtingHandler = null;
        passingHandler = null;
    }

    private Position createPosition(
            long deviceId,
            Date time,
            boolean valid,
            double latitude,
            double longitude,
            double altitude,
            double speed,
            double course) {

        Position p = new Position();
        p.setDeviceId(deviceId);
        p.setTime(time);
        p.setValid(valid);
        p.setLatitude(latitude);
        p.setLongitude(longitude);
        p.setAltitude(altitude);
        p.setSpeed(speed);
        p.setCourse(course);
        return p;
    }

    @Test
    public void testFilterInvalid() throws Exception {

        Position position = createPosition(0, new Date(), true, 10, 10, 10, 10, 10);

        assertNotNull(filtingHandler.decode(null, null, position));
        assertNotNull(passingHandler.decode(null, null, position));

        position = createPosition(0, new Date(Long.MAX_VALUE), true, 10, 10, 10, 10, 10);

        assertNull(filtingHandler.decode(null, null, position));
        assertNotNull(passingHandler.decode(null, null, position));

        position = createPosition(0, new Date(), false, 10, 10, 10, 10, 10);

        assertNull(filtingHandler.decode(null, null, position));
        assertNotNull(passingHandler.decode(null, null, position));

        position.set(Position.KEY_ALARM, Position.ALARM_GENERAL);
        filtingHandler.setSkipAttributes(true);
        assertNotNull(filtingHandler.decode(null, null, position));
    }

}
