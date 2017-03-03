package silverclaw.vividbirds.common.entity.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class LyrebirdSounds {

	private static final Random rand = new Random();
	
	public static final int LIVING_SOUND_NUMBER = 7;
	public static final int HURT_SOUND_NUMBER = 3;
	
	public static final String [] DEFAULT_LIVING_SOUNDS = new String [] {
			
			"minecraft:mob.cat.meow",
			"minecraft:mob.cat.purr",
			"minecraft:mob.cat.purreow",
			"minecraft:mob.cow.say",
			"minecraft:mob.chicken.say",
			"minecraft:mob.sheep.say",
			"minecraft:mob.pig.say",
			"minecraft:mob.silverfish.say",
			"minecraft:mob.wolf.bark",
			"minecraft:mob.wolf.howl",
			"minecraft:dig.cloth",
			"minecraft:dig.grass",
			"minecraft:dig.gravel",
			"minecraft:dig.sand",
			"minecraft:dig.snow",
			"minecraft:dig.stone",
			"minecraft:dig.wood",
			"minecraft:fire.fire",
			"minecraft:fire.ignite",
			"minecraft:fireworks.blast",
			"minecraft:fireworks.blast_far",
			"minecraft:fireworks.largeBlast",
			"minecraft:fireworks.largeBlast_far",
			"minecraft:fireworks.launch",
			"minecraft:random.burp"
	};
	
	public static final String [] DEFAULT_HURT_SOUNDS = new String [] {
			
			"minecraft:mob.cat.hiss",
			"minecraft:mob.horse.angry",
			"minecraft:mob.endermen.scream",
			"minecraft:mob.endermen.stare",
			"minecraft:mob.creeper.say",
			"minecraft:mob.ghast.moan",
			"minecraft:mob.blaze.hit",
			"minecraft:mob.skeleton.say",
			"minecraft:game.neutral.hurt.fall.big",
			"minecraft:game.neutral.hurt.fall.small",
			"minecraft:game.player.hurt.fall.big",
			"minecraft:game.player.hurt.fall.small",
			"minecraft:game.hostile.hurt.fall.big",
			"minecraft:game.hostile.hurt.fall.small",
			"minecraft:game.player.hurt",
			"minecraft:game.neutral.hurt",
			"minecraft:game.hostile.hurt",
			"minecraft:mob.bat.hurt",
			"minecraft:mob.chicken.hurt",
			"minecraft:mob.cow.hurt",
			"minecraft:random.explode",
			"minecraft:creeper.primed"	
	};
	
	public static final String [] DEFAULT_DEATH_SOUNDS = new String [] {
			
			"minecraft:game.player.die",
			"minecraft:game.neutral.die",
			"minecraft:game.hostile.die",
			"minecraft:mob.bat.death",
			"minecraft:mob.blaze.death",
			"minecraft:mob.creeper.death",
			"minecraft:mob.endermen.death",
			"minecraft:mob.horse.death",
			"minecraft:mob.horse.donkey.death",
			"minecraft:mob.pig.death"	
	};
	
	public static List<String> LIVING_SOUNDS = Collections.emptyList();
	
	public static List<String> HURT_SOUNDS = Collections.emptyList();

	public static List<String> DEATH_SOUNDS = Collections.emptyList();
	
		
	private static List<String> selectSounds(
			List<String> available, int size) {
	
		Collections.shuffle(available, rand);
		List<String> selected = new ArrayList<>(size);
		for(int i = 0; i< size; i++) {
			selected.add(available.get(i));
		}
		return selected;
	}
	
	public static final String randomSound(List<String> available) {
		
		if(available.size() == 0) return null;
		return available.get(rand.nextInt(available.size()));
	}
	
	public static List<String> selectLivingSounds() {
		
		return selectSounds(LIVING_SOUNDS, LIVING_SOUND_NUMBER);
	}
	
	public static List<String> selectHurtSounds() {
		
		return selectSounds(HURT_SOUNDS, HURT_SOUND_NUMBER);
	}
	
	public static String selectDeathSound() {
		
		return randomSound(DEATH_SOUNDS);
	}

	private static List<String> mergeSounds(
			List<String> sounds1, List<String> sounds2,
			List<String> fillers, int limit) {
		
		// we do not want duplicate sounds
		Set<String> mergedSounds = new HashSet<>(
				sounds1.size() + sounds2.size());
		mergedSounds.addAll(sounds1);
		mergedSounds.addAll(sounds2);
		mergedSounds.add(randomSound(fillers));
		
		return mergedSounds.stream().limit(limit)
			.collect(Collectors.toList());
	}
	
	
	public static List<String> mergeLivingSounds(
			List<String> sounds1, List<String> sounds2) {
		return mergeSounds(sounds1, sounds2,
				LIVING_SOUNDS, LIVING_SOUND_NUMBER);

	}

	public static List<String> mergeHurtSounds(
			List<String> sounds1, List<String> sounds2) {
		return mergeSounds(sounds1, sounds2,
				HURT_SOUNDS, HURT_SOUND_NUMBER);
	}
}
