package forms;

import java.util.List;

import play.data.validation.Constraints.Required;

public class Enigma {
	@Required
	public String description;

	public Answer answer;

	public List<Clue> clues;

	public static class Clue {

		public static final Integer TextClue = 0;
		public static final Integer FileClue = 1;

		public Integer type;

		public String textDescription;

		public String fileDescription;

		public String fileLink;
	}

	public static class Answer {

		public static Integer TextAnswer = 0;
		public static Integer GeolocatedAnswer = 1;

		public Integer type;

		public List<String> possibleTexts;

		public String labelPosition;

		public String position;

		public Float accuracy;
	}

}
