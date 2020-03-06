package eg.edu.alexu.csd.datastructure.iceHockey;
import java.awt.Point;
public class PlayersFinder implements IPlayersFinder {
	// remove///////////////////////////////////
	char teamLetter ;
	int height, wedth;
	int minX,maxX,minY,maxY;
	int numOfBoxes = 0;
	public Point[] findPlayers(String[] photo, int team, int threshold) {
		height = photo.length;
		wedth = photo[0].length();
		minX = wedth*2; maxX = 0;
		maxY = 0;
		Point[] points = new Point[height * wedth / threshold];
		int pointsCounter = 0;
		teamLetter = (char) (team + '0');
		for (int y=0; y<height; y++) {
			minY = 2*y;
			for (int x=0; x<wedth; x++) {
				if (photo[y].charAt(x) == teamLetter) {
					maxX = x*2+2;
					go(photo, x, y);
					if (threshold <= numOfBoxes*4 ) {
						points[pointsCounter++] = new Point( (maxX-minX)/2 + minX ,(maxY-minY)/2 + minY);
					}
					//resetting the values so the other functions works fine
					minX = wedth*2; maxX = 0;
					maxY = 0;
					numOfBoxes = 0;
				}
			}
		}
		Point[] newPoints = new Point[pointsCounter];
		for (int i=0; i< pointsCounter; i++) {
			newPoints[i] = points[i];
		}
		// now we just need to return the photo to its original state
		////////////////////
		for (int i=0; i<height; i++) {
			for (int j=0; j<wedth; j++) {
				if (photo[i].charAt(j) == ' ') {
					photo[i] = photo[i].substring(0,j) + teamLetter +photo[i].substring(j+1, wedth);
				}
			}
		}
		///////////////////
		return newPoints;
	}
	void go(String[] photo, int x, int y) {
		 // using short circuit evaluation
		if (x>=0 && x < wedth  && y>=0 && y< height && photo[y].charAt(x) == teamLetter) {
			photo[y] = photo[y].substring(0,x) + ' ' + photo[y].substring(x+1, wedth);
			numOfBoxes++;
			if (minX > x*2) {
				minX = x*2;
			}
			if (maxX < (x*2+2)) {
				maxX = x*2 + 2;
			}
			if ( minY > 2*y) {
				maxY = 2*y;
			}
			if ( maxY < (y*2+2)) {
				maxY = 2*y + 2;
			}
			go(photo,x+1,y); // go right
			go(photo,x,y+1); // go down
			go(photo,x-1,y); // go left
			go(photo,x,y-1); // go up
		}
	}
}
