package org.alex.springapp.controller.request;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author zamdirit
 */
public abstract class AbstractRequestData {

    private static final Logger LOG = LogManager.getLogger(AbstractRequestData.class);

    public final Map<String, Object> getFilledFields() {

        Map<String, Object> filledFields = new HashMap<>();

        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(RequestField.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(this) != null) {
                        filledFields.put(field.getName(), field.get(this));
                    }
                } catch (IllegalAccessException e) {
                    LOG.error(e.getMessage());
                }
            }
        }
        return filledFields;
    }

}
