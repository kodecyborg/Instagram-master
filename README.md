Instagram
=========

## Version 1 Instructions


Using the Instagram API, create an Android app that displays photos tagged with hashtag #selfie and arranges them on a list view. Implement an infinite scrolling. Scrolling should be smooth so performance is essential.

 * Implement a LRUCache
 * Use Volley
 * Upload your code to GitHub
 * Name this project as InstagramSelfie
 * Use the NetworkImageView
 * Hints

### Hints

 * Read this article: http://blog.lemberg.co.uk/volley-part-1-quickstart
 * You need to create an Instagram developer account
 * Read the Instagram documentation
 * Use this URL https://api.instagram.com/v1/tags/{tag}/media/recent/?client_id={client_id} to get the data
 * Instagram provides a JSON object with URLs to the mages
 * Use a tool to see the data structure like:
    * http://json.parser.online.fr/
    * http://jsonviewer.stack.hu/

Abstraction
===========

The packages instagram.rest.tag... and the classes names are meant to reflect the instagram api endpoints

Design Paterns used
===================

 * Singleton
 * Builder
 * Observer

Clean code
==========

https://www.ufm.edu/images/0/04/Clean_Code.pdf

Classes and objects should have noun or noun phrase names, page 25

Use Intention-Revealing Names. Methods should have verb or verb phrase names... Page 18

Pick one word for one abstract concept and stick with it. For instance, it’s confusing to. have fetch, retrieve, and get as equivalent methods of different classes... Page 26

Use Solution Domain Names. Remember that the people who read your code will be programmers. So go ahead and use computer science (CS) terms, algorithm names, pattern names ... Page 27

The ideal number of arguments for a function is zero (niladic)... Page 40

The first rule of functions is that they should be small. FUNCTIONS SHOULD DO ONE THING. THEY SHOULD DO IT WELL. THEY SHOULD DO IT ONLY... Pages 34 - 35
