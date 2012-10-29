package models;

import java.net.URI;
import org.openrdf.annotations.Iri;

@Iri(NS.GNGM + "FileClue")
public class FileClue extends Clue {

	private URI file;

	@Iri(NS.GNGM + "file")
	public URI getFile() {
		return file;
	}

	@Iri(NS.GNGM + "file")
	public void setFile(URI file) {
		this.file = file;
	}
	
	@Override
	public void reset() {
		super.reset();
		this.setFile(null);
	}
}
