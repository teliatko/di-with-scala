di-with-scala
=============

Example code from my talk at Vienna Scala User Group on 23th July 2013. You can find the slides [here](http://www.slideshare.net/MichalBigos/dependency-injection-in-scala "Slides").
All the examples are in context of [Play!](http://www.playframework.com/) framework.

Content
-------

I've covered following DI frameworks from Java and Scala world:

1. Spring
2. Spring-Scala
3. CDI
4. Guice
5. SubCut
6. Cake Pattern

Structure
---------

Each sub-folder contains separate [Play!](http://www.playframework.com/) application. So to run it you have to go into subfolder and run:

`play run`

Afterwards you can go to http://localhost:9000/[framework]/[anytext]. You will see the page with text "Bonjour [anytext]". Please look at `routes` file to exact URLs. 

Explanation
-----------

I've used very simple example to show the DI. In package `services` you can find `Translator` service interface and its implementation `FrenchTranslator`.
`Translator` service is injected into `controllers.Translate` controller. Controller takes any string on the query path, assuming that you've put the name there, and greets you accordingly in french.



