package models.old;

import gngm.Answer;
import gngm.Clue;
import gngm.Step;

import java.util.Set;

public class Enigma extends Resource implements gngm.Enigma {

	private Set<Answer> answers;
	private Set<Clue> clues;
	private Step enigmaOfStep;

	@Override
	public Set<Answer> getAnswers() {
		return answers;
	}

	@Override
	public void setAnswers(Set<? extends Answer> answers) {
		this.answers = (Set<Answer>) answers;

	}

	@Override
	public Set<Clue> getClues() {
		return clues;
	}

	@Override
	public void setClues(Set<? extends Clue> clues) {
		this.clues = (Set<Clue>) clues;

	}

	@Override
	public Step getEnigmaOfStep() {
		return enigmaOfStep;
	}

	@Override
	public void setEnigmaOfStep(Step enigmaOfStep) {
		this.enigmaOfStep = enigmaOfStep;

	}

}
