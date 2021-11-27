#1 MVVBeers
(Stands for Example/Template application with MVVM pattern about beers, but could be any api oriented app)

#2 Motivation
Some years ago I had the feeling that Android had come to its maturity, RxJava taught us how to work with 
under a reactive paradigm, we had MVVM and MVP patterns to clean our massive Activities, and of course 
Clean Architecture was the mantra any "decent" developer should use while setting up any new project.
Other libraries came to fill other gaps (even if we were not aware of the gaps) Retrofit, Dagger, DataBinding.....
And to keep all under control we had unit testing to prevent our stressed minds to make any mistake with all that zoo.

And with all that, well, we finally start to build massive projects, and for some time it was.....ok.

But ....ok was not enough, and some game changers appear on scene, for me it were 3 of them:
Kotlin, Coroutines and Kodein (or Koin). I remember the day we remove the RxJava dependency from our project.
It was like swimming with a coat and suddenly be able to swim only with the swim suit. Same feelings when we got rid of 
Dagger, not to mention that the whole project was started from scratch with no one single line of java.

Of course there were not all wind and roses, there were still some noise inside the engine, mvvm was nicer than 
mvp (or felt more modern) but you needed other dependencies like DataBinding LiveData.. and Coroutines were not exactly
the same as RxJava, but they keep evolving and after watching some videos and reading some posts, it looks like right
now ... everything flows.

*TLDR; Let's use coroutines, and flows to build an app, and see how many dependencies we can kill on our way.

#2 Tech stack
Kotlin
Clean Architecture
Coroutines
Flow (in 3 flavours)
and maybe jetpack compose.

#2 First approach
V1.0.0 - It shows a list of beers, of course it can be any api that returns a list of something.

