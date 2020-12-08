package org.alex.springapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author zamdirit
 * @param <T> : class annotated with @Entity
 */
public class CustomRepositoryImpl<T> implements CustomRepository<T> {
    
    @PersistenceContext
    EntityManager manager;
    
    /**
     * 
     * @param entity: entity object
     * @param id: entity's id
     * @param fields: map with pair <String, Object>. for example: <"age", 24>, <"name", "Andrew">, etc....
     * @return int
     */
    @Transactional
    @Override
    public int updateByFields(T entity, long id, Map<String, Object> fields) {
        
        String entityClassName = entity.getClass().getSimpleName();        
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder
                .append("UPDATE ")
                .append(entityClassName)
                .append(" e SET ");
        
        List<Object> params = new ArrayList<>();
        int paramNumber = 0;
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            params.add(value);
            paramNumber++;
            if (paramNumber > 1) {
               sqlStringBuilder.append(" ,");
            }
            sqlStringBuilder
                    .append("e.")
                    .append(key)
                    .append(" = ?")
                    .append(paramNumber);
        }
        sqlStringBuilder
                .append(" WHERE id=")
                .append(id);
        String sql = sqlStringBuilder.toString();
        Query query = manager.createQuery(sql, entity.getClass());
        paramNumber = 0;
        for (Object param : params) {
            paramNumber++;
            query.setParameter(paramNumber, param);
        }
        return query.executeUpdate();
    }

}
