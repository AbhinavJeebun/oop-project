package audio;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

	// Song and effect identifiers
	public static int MENU_1 = 0;
	public static int LEVEL_1 = 1;
	public static int LEVEL_2 = 2;

	public static int DIE = 0;
	public static int JUMP = 1;
	public static int GAMEOVER = 2;
	public static int LVL_COMPLETED = 3;
	public static int ATTACK_ONE = 4;
	public static int ATTACK_TWO = 5;
	public static int ATTACK_THREE = 6;

	// Arrays to store song and effect clips
	private Clip[] songs, effects;

	// Identifier for the currently playing song
	private int currentSongId;

	// Volume control
	private float volume = 0.5f;

	// Mute settings for songs and effects
	private boolean songMute, effectMute;

	// Random number generator
	private Random rand = new Random();

	// Constructor
	public AudioPlayer() {
		loadSongs();
		loadEffects();
		playSong(MENU_1);
	}

	// Load song clips
	private void loadSongs() {
		String[] names = { "menu", "level1", "level2" };
		songs = new Clip[names.length];
		for (int i = 0; i < songs.length; i++)
			songs[i] = getClip(names[i]);
	}

	// Load effect clips
	private void loadEffects() {
		String[] effectNames = { "die", "jump", "gameover", "lvlcompleted", "attack1", "attack2", "attack3" };
		effects = new Clip[effectNames.length];
		for (int i = 0; i < effects.length; i++)
			effects[i] = getClip(effectNames[i]);

		updateEffectsVolume();
	}

	// Load an audio clip from a resource
	private Clip getClip(String name) {
		URL url = getClass().getResource("/audio/" + name + ".wav");
		AudioInputStream audio;

		try {
			audio = AudioSystem.getAudioInputStream(url);
			Clip c = AudioSystem.getClip();
			c.open(audio);
			return c;

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

		return null;
	}

	// Set the overall volume
	public void setVolume(float volume) {
		this.volume = volume;
		updateSongVolume();
		updateEffectsVolume();
	}

	// Stop the currently playing song
	public void stopSong() {
		if (songs[currentSongId].isActive())
			songs[currentSongId].stop();
	}

	// Set the appropriate level song based on the level index
	public void setLevelSong(int lvlIndex) {
		if (lvlIndex % 2 == 0)
			playSong(LEVEL_1);
		else
			playSong(LEVEL_2);
	}

	// Play the level completed effect
	public void lvlCompleted() {
		stopSong();
		playEffect(LVL_COMPLETED);
	}

	// Play a random attack sound effect
	public void playAttackSound() {
		int start = 4;
		start += rand.nextInt(3);
		playEffect(start);
	}

	// Play the specified effect
	public void playEffect(int effect) {
		if (effects[effect].getMicrosecondPosition() > 0)
			effects[effect].setMicrosecondPosition(0);
		effects[effect].start();
	}

	// Play the specified song
	public void playSong(int song) {
		stopSong();

		currentSongId = song;
		updateSongVolume();
		songs[currentSongId].setMicrosecondPosition(0);
		songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
	}

	// Toggle song mute settings
	public void toggleSongMute() {
		this.songMute = !songMute;
		for (Clip c : songs) {
			BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
			booleanControl.setValue(songMute);
		}
	}

	// Toggle effect mute settings
	public void toggleEffectMute() {
		this.effectMute = !effectMute;
		for (Clip c : effects) {
			BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
			booleanControl.setValue(effectMute);
		}
		if (!effectMute)
			playEffect(JUMP);
	}

	// Update song volume based on the current volume setting
	private void updateSongVolume() {
		FloatControl gainControl = (FloatControl) songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN);
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = (range * volume) + gainControl.getMinimum();
		gainControl.setValue(gain);
	}

	// Update effects volume based on the current volume setting
	private void updateEffectsVolume() {
		for (Clip c : effects) {
			FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
			float range = gainControl.getMaximum() - gainControl.getMinimum();
			float gain = (range * volume) + gainControl.getMinimum();
			gainControl.setValue(gain);
		}
	}
}
