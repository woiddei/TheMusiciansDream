One problem I am currently thinking about is animating the trees and the surface of thee water according to data derived from MIDI files.
J3D is quite problematic about this topic and I admit I have little idea about what I'll do.

J3D's animations mostly rely of TransformInterpolator objects, objects that linearly interpolate an alpha value to a certain 3D-transformation.
The alpha value is some function of time, designed to loop every once in a while for creating repeating sequences of animation.
When I will animate the trees, where the animation is mainly ratation-based, the logical thing to do is to create a new type of alpha object with non-looping behavior, that will override the value() function to retrive midi-data-dependent value instead of a regular, sinusidal value.

I am quite concerned, however, about the water animation. The simplistic idea of TransformInterpolators will probably not be enough here. Obviously, I'll have do divide the water's surface into triangles and animate each triangle seperately. The problem is that those triangles will have to be moved in non-trivial ways - somewhere between skewing and rotating.
I should try and decompose this skew transform into rotational and scaling components and transform each component seperated.
I am still trying to figure this one out, but I thought I'll share my thoughts about this subject with you.
