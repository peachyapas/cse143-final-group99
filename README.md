# CSE 143 Final Project: User Guide

This is for [CSE 143's final project](https://courses.cs.washington.edu/courses/cse143/20au/project/#status-report)

This is a console-based application that can be run by typing the command below in terminal. Make sure you're in the root directory.

```java
javac ChemistryMeme.java && java ChemistryMeme
```

The user is prompted to enter a word. If the word is spellable using elements from the periodic table, the programme returns all possible 
combinations of the word spelled using elements from the periodic table, as well as the name of the elements and number of elements that were 
used to spell the word. For instance, if the user inputs `No`, it'll print `The word can be symbolised as NO using 2 element(s): Nitrogen, Oxygen`
and `The word can be symbolised as No using 1 element(s): Nobelium` as the answer to all possible ways the word "no" can be spelled using the periodic
table elements.

The user is then asked if they want to play the game again (meme with another word), and the game repeats until the user enters "no" when asked 
if they want to play another game. The programme is run using the console.

Currently (this has to be fixed), if the user inputs a string that cannot be spelled, the programme only prints out the elements that are in the string.
For instance, if we enter "Brian", it will give us "Iodine Bromine Boron". The distinction between spellable and non-spellable words (using elements from the
periodic table) is that the spellable ones have the text "This word can be symbolised as ___ using __ element(s)" printed. There is another bug as well
in printing out the name of the elements when enumerating all possible ways the word can be spelled. For instance, not all elements names are enumerated if
the input word is "irresponsibilities".

Here is a link to a google drive with the [user guide video](https://drive.google.com/drive/folders/1ic0f211qd6M4_IvK05jXaGVNphH9q_6w?usp=sharing). If that link doesn't work, [this youtube link hopefully works](https://youtu.be/m2VIvKmVhS4).

The design document and reflection text document can be found under the "user documents" folder.

[//]: <> ( User guide )

[//]: <> (Write a user guide for your project in the form of a file called README.txt or README.md at least several paragraphs in length. Though the structure of your user guide is entirely up to you, it should be clear to the staff how and where, if applicable, to compile, configure, and use your project. It should not be necessary for us to contact you with questions regarding your project after its submission. Hold our hand with this documentation: be sure to answer in your documentation any questions that you think we might have while testing your work.)

[//]: <> (The guide should also include a link to a short video no more than 5 minutes long that presents your project to the world with slides, screenshots, voiceover, and/or live action. Your video should somehow include your project title, your names, and any other details that you’d like to convey to viewers. We recommend recording a video through Zoom for ease of screensharing and so that all collaborators can present.)

