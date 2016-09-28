package ascend.access;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import ascend.Unit;

public class GsonUnitExcluder implements ExclusionStrategy {

	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}
	
	public boolean shouldSkipField(FieldAttributes f) {
		return f.getDeclaredType().equals(Unit.class);
	}

}
