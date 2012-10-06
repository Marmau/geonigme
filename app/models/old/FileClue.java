package models.old;

import java.net.URI;

public class FileClue extends Clue implements gngm.FileClue {

	private URI file;
	
	@Override
	public URI getFile() {
		return file;
	}

	@Override
	public void setFile(URI file) {
		this.file = file;		
	}

}
