package models;

import java.net.URI;

import org.codehaus.jackson.node.ObjectNode;
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

	@Override
	public ObjectNode toJson() {
		ObjectNode result = super.toJson();
		result.put("file", getFile().toASCIIString());

		return result;
	}

	public String getFileType() {
		String result = "picture";
		String file = getFile().toString();
		String extension = file.substring(file.lastIndexOf('.'));
		if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("gif") || extension.equals("png")) {
			result = "picture";
		}
		if (extension.equals("mp3") || extension.equals("ogg")) {
			result = "sound";
		}
		return result;
	}
}
