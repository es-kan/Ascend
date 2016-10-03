package ascend.access;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import ascend.Unit;

/*This class is basically useless now that simple game representations are being sent to the front instead.
 * but it's pretty cool and I would've been proud if it if I was using it.*/

public class GsonUnitExcluder implements ExclusionStrategy {

	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}
	
	public boolean shouldSkipField(FieldAttributes f) {
		return f.getDeclaredType().equals(Unit.class);
	}

}
