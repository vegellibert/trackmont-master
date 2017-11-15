/*
 * Copyright 2017 Vicente Venegas (vicente@trackmont.com)
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
package org.trackmont.database;

import org.trackmont.model.Attribute;

public class AttributesManager extends ExtendedObjectManager<Attribute> {

    public AttributesManager(DataManager dataManager) {
        super(dataManager, Attribute.class);
    }

    @Override
    public void updateCachedItem(Attribute attribute) {
        Attribute cachedAttribute = getById(attribute.getId());
        cachedAttribute.setDescription(attribute.getDescription());
        cachedAttribute.setAttribute(attribute.getAttribute());
        cachedAttribute.setExpression(attribute.getExpression());
        cachedAttribute.setType(attribute.getType());
    }

}