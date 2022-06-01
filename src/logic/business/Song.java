package logic.business;

public class Song extends Audiovisual {
	private String author;
	private String album;

	public Song(String title, String genre, int duration, String interpreter, String collaborators, int fileSize, String author, String album) {
		super(title, genre, duration, interpreter, collaborators, fileSize);
		this.author = author;
		this.album = album;
	}
	
	public void foo()
	{
		
	}
}
