package org.trackmont;

import org.trackmont.database.IdentityManager;
import org.trackmont.model.Device;
import org.trackmont.model.Position;

public class BaseTest {
    
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
                return defaultValue;
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

}