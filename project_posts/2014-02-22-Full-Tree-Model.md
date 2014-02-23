So, after like a week's effort my tree model is ready and includes everything.
I didn't post much in the last few days so I will explain a little about what I did in this time.
First of all, I added a Trunk and TrunkShape object and united various similarities of Branch and Trunk objects into a new common parent object called Bough (not botanically correct, I know, but whatever), and did a similar thing for BoughShape to integrate common aspects of TrunkShape and BranchShape.
I created an test.app directory containing old Main files for the applet. Most of them don't currently work because I changed many data structures but I commented out the incorrect parts, so anyone struggling to figure out j3d like I did may find it helpful.
I then added a Tree object which is basically a large group of 3d objects for all branches and the trunk.
The very last thing I did was just adding the leaves as the final addition to the tree model.
After I finished everything last night, I created some full-scale trees and tested them. In an hour at most a new app.Main class should be uploaded with a fully-functional tree example.
I am very, very excited about this. Good night!
