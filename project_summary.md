# The Musician's Dream
Re-imagining music.
 _Through the Looking-Glass, and What Alice Found There_, Lewis Carroll, 1871:
>Do you hear the snow against the window-panes, Kitty? How nice and soft it sounds! Just as if some one was kissing the window all over outside. I wonder if the snow loves the trees and fields, that it kisses them so gently? And then it covers them up snug, you know, with a white quilt; and perhaps it says, "Go to sleep, darlings, till the summer comes again."

## Authors
- [Amir Sarid](https://github.com/amirsarid).

## Description
Imaging music from any MIDI file into a 3-dimensional video scene.
I seek to implant musical data into a three-dimentional simulation of a natural scene. Parameters from the scene will be affected both by the music and by the local scenery of the viewer's location - using google's API to determine it.
I hope to make a more fulfilling and intriguing experience out of music, in an age where audio alone is merely a distraction from the flow of visual data.
I do wish to use the location tools to make natural and local-looking scenery, to make the viewer feel comfortable in the scenery the music has thrown him into.

## Code example

```java
    public static float[] getCoordinatesOfLocation(String locationName) throws Exception {
		String source=WebUtils.getSourceCode("http://maps.googleapis.com/maps/api/geocode/json?address="+locationName+"&sensor=true"),
			   fromCoordsString=source.split("\"location\"")[1],
			   latString=fromCoordsString.split("\"lat\" : |,")[1],
			   lngString=fromCoordsString.split("\"lng\" : |}")[1].trim();
		return (new float[] {Float.parseFloat(latString),
							 Float.parseFloat(lngString)});
	}
```

## Images & Videos

This image is an inspiration for my idea of a scene.
![Some inspiration](project_images/cover1.jpg?raw=true "Some inspiration")

[Second demo](http://youtu.be/FjgzSlxphTc)
[First demo](http://youtu.be/QXZ4ai-Jqvw)
