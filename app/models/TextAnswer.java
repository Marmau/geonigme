package models;

import global.Levenshtein;
import global.StringUtils;

import java.util.Set;

import org.openrdf.annotations.Iri;

@Iri(NS.GNGM + "TextAnswer")
public class TextAnswer extends Answer {

	private Set<String> labels;

	@Iri("http://www.w3.org/2000/01/rdf-schema#label")
	public Set<String> getLabels() {
		return labels;
	}

	@Iri("http://www.w3.org/2000/01/rdf-schema#label")
	public void setLabels(Set<String> labels) {
		this.labels = labels;
	}

	@Override
	public void reset() {
		super.reset();
		this.setLabels(null);
	}

	@Override
	public boolean isCorrect(String answer) {
		answer = StringUtils.removeAccents(answer).toLowerCase();

		for (String label : getLabels()) {
			label = StringUtils.removeAccents(label).toLowerCase();

			if (Levenshtein.computeLevenshteinDistance(answer, label) < label.length() / 4) {
				return true;
			}
		}

		return false;
	}
}
