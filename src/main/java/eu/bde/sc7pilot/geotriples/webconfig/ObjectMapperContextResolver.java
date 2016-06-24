package eu.bde.sc7pilot.geotriples.webconfig;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
	 
	    final ObjectMapper defaultObjectMapper;
	 
	    public ObjectMapperContextResolver() {
	        defaultObjectMapper = createDefaultMapper();
	    }
	 
	    @Override
	    public ObjectMapper getContext(Class<?> type) {
	            return defaultObjectMapper;
	        }
	 
	    private static ObjectMapper createDefaultMapper() {
	    	FilterProvider filterProvider=new SimpleFilterProvider().setFailOnUnknownId(false);
	        final ObjectMapper result = new ObjectMapper();
	        //result.disable(MapperFeature.DEFAULT_VIEW_INCLUSION); 
	        result.registerModule(new JodaModule());
	        result.registerModule(new JtsModule());
	        result.setFilterProvider(filterProvider);
	        return result;
	    }
	 
	}
