# The Musician's Dream
 _Through the Looking-Glass, and What Alice Found There_, Lewis Carroll, 1871:
>Do you hear the snow against the window-panes, Kitty? How nice and soft it sounds! Just as if some one was kissing the window all over outside. I wonder if the snow loves the trees and fields, that it kisses them so gently? And then it covers them up snug, you know, with a white quilt; and perhaps it says, "Go to sleep, darlings, till the summer comes again."

I try and visualise musical data as scenic three-dimensional video. I imagine a wintery natural view created from data on a MIDI-file, with rain falling into a lake, each drop representing a note. The location of the drop will roughly correspond to its height of the note and its size to the pressure applied.

I feel like music is becoming a part of my life which goes with me everywhere: on the bus, in the shower, while coding... But the more music I have in my life, the less I think of it as a special, immersing experience and more of a distraction.
I feel like just listening to music has become quite hard for me - I can't be fully concentrated on it without any visual data.
So, creating this visual data seemed like a reasonable choice for a project. I chose winter as a theme for this visualization because winter intuitively connects in my mind to life and to music, because there is so much motion and action in a winter scenery.
I will try and make it, as I think of it, a natural way to continue musical data into a 3d scene - something like analytic continuation for music. A visual data to company the music and align with it perfectly, creating a more fulfilling experience while keeping the music in the center of the creation.

## Project Structure
- Please remember that right now, everything is just a draft.
- Project code: I created a basic applet to tweak and try stuff using java 3d (which is a java high-level package written on top of OpenGL).
- Sounds and textures: I found some rain sounds and images of snow and ground, distributed under a CC0 license - details can be found at project_sounds and project_textures.

## Ideas
- Water modeling: I think a drop at the origin should affect the water's surface somewhat like h(r,phi,t)=sin(r-t+3)/(r-t+3), with some neccesary tweaking for larger drops and so on.
- Tree modeling: I just found an array of J. Weber and J. Penn called [Creation and Rendering of Realistic Trees](https://www.cs.duke.edu/courses/fall02/cps124/resources/p119-weber.pdf), which seems very detailed but also complicated. I'll see what I can make of it.

## Approximated Schedule
- [x] 11.2 First ideas and drafts. Construction of a basic 3d applet.
- [x] 15.2 Create a basic 3d scene in java. Realize how musical parameters should affect the scene.
- [ ] 29.2 Model some trees and create a full scene.
- [ ] 10.3 Basic java applet working.
- [ ] 22.3 full applet ready.
- [ ] 28.3 Demonstrations uploaded here and on youtube. Final debugging. Additional options.
