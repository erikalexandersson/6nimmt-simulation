package se.erikalexandersson.sixnimmt;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
	public static <T> T getRandomFromSet(Set<T> set) {
		if (set == null || set.isEmpty()) {
			throw new IllegalArgumentException("The Set cannot be empty.");
		}
		int randomIndex = ThreadLocalRandom.current().nextInt(set.size());
		int i = 0;
		for (T element : set) {
			if (i == randomIndex) {
				return element;
			}
			i++;
		}
		throw new IllegalStateException("Something went wrong while picking a random element.");
	}
}
