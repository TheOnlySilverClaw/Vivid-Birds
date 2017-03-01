package silverclaw.vividbirds.common.entity.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import scala.actors.threadpool.Arrays;

public class LyrebirdSoundManager {

	private static final Random rand = new Random();
	
	public static final int LIVING_SOUND_NUMBER = 7;
	public static final int HURT_SOUND_NUMBER = 3;
	
	private static String [] LIVING_SOUNDS = {
			
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
		
		private static String [] HURT_SOUNDS = {
			
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
		

		private static String [] DEATH_SOUNDS = {
			
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
		
		private static String[] selectSounds(String [] available, int size) {
		
			String [] toFill = new String[size];
			for(int i = 0; i < toFill.length; i++) {
				toFill[i] = randomSound(available);
			}
			return toFill;
		}
		
		public static final String randomSound(String [] available) {
		
			return available[rand.nextInt(available.length)];
		}
		
		public static String[] selectLivingSounds() {
			return selectSounds(LIVING_SOUNDS, LIVING_SOUND_NUMBER);
		}
		
		public static String [] selectHurtSounds() {
			return selectSounds(HURT_SOUNDS, HURT_SOUND_NUMBER);
		}
		
		public static String selectDeathSound() {
			return randomSound(DEATH_SOUNDS);
		}

		private static String [] mergeSounds(
				String[] sounds1, String[] sounds2,
				String [] fillers, int optimal) {
			
			
			System.out.println(Arrays.toString(sounds1));
			System.out.println(Arrays.toString(sounds2));

			// we do not want duplicate sounds
			Set<String> mergedSounds =
					new HashSet<>(LIVING_SOUND_NUMBER);
			
			for(String sound : sounds1) {
				if(rand.nextBoolean()) {
					mergedSounds.add(sound);
				}
			}
			
			for(String sound : sounds2) {
				if(rand.nextBoolean()) {
					mergedSounds.add(sound);
				}
			}
						
			System.out.println("merged: " + mergedSounds);

			if(mergedSounds.size() < optimal) {
				System.out.println("filling up");
				mergedSounds.add(randomSound(fillers));
				System.out.println(mergedSounds);
			} else if(mergedSounds.size() > optimal) {
				System.out.println("removing one sound");
				mergedSounds.iterator().remove();
			}
			
			return mergedSounds.toArray(
					new String[mergedSounds.size()]);
		}
		
		
		public static String[] mergeLivingSounds(
				String[] sounds1, String[] sounds2) {
			System.out.println("merging living sounds");
			return mergeSounds(sounds1, sounds1,
					LIVING_SOUNDS, LIVING_SOUND_NUMBER);

		}

		public static String[] mergeHurtSounds(
				String[] sounds1, String[] sounds2) {
			System.out.println("merging hurt sounds");
			return mergeSounds(sounds1, sounds2,
					HURT_SOUNDS, HURT_SOUND_NUMBER);
		}
}
