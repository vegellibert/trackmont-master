/*
 * Copyright 2017 Vicente Venegas (vicente@republik.ec)
 * Copyright 2017 Andrey Kunitsyn (andrey@trackmont.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.trackmont.events;

import java.util.Collections;
import java.util.Map;

import org.trackmont.BaseEventHandler;
import org.trackmont.Context;
import org.trackmont.model.Event;
import org.trackmont.model.Position;

public class DriverEventHandler extends BaseEventHandler {

    @Override
    protected Map<Event, Position> analyzePosition(Position position) {
        if (!Context.getIdentityManager().isLatestPosition(position)) {
            return null;
        }
        String driverUniqueId = position.getString(Position.KEY_DRIVER_UNIQUE_ID);
        if (driverUniqueId != null) {
            String oldDriverUniqueId = null;
            Position lastPosition = Context.getIdentityManager().getLastPosition(position.getDeviceId());
            if (lastPosition != null) {
                oldDriverUniqueId = lastPosition.getString(Position.KEY_DRIVER_UNIQUE_ID);
            }
            if (!driverUniqueId.equals(oldDriverUniqueId)) {
                Event event = new Event(Event.TYPE_DRIVER_CHANGED, position.getDeviceId(), position.getId());
                event.set(Position.KEY_DRIVER_UNIQUE_ID, driverUniqueId);
                return Collections.singletonMap(event, position);
            }
        }
        return null;
    }

}
