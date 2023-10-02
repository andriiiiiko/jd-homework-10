# Protocol HTTP

There is a website https://http.cat. This site has its own cat image for each HTTP response status.

You can get this image by sending a GET request https://http.cat/"CODE".jpg, where a number is substituted for 
"CODE" - the status code.

For example, for a status of 200 (the request was successfully executed), the URL will be as follows - 
https://http.cat/200.jpg.

You can open this URL in a browser and see the picture for the corresponding status. We will work with this site 
programmatically.

You can use standard tools for working with HTTP in Java, or connect any library for convenient work with HTTP 
(for example, JSoup or OkHttp).


### Task #1 - get a link

**Write the HttpStatusChecker class. This class must have one method:**

+ string getStatusImage(int code). It takes a status code and returns a link to the image for that code. If there is no 
image for the corresponding code (site https://http.cat returned 404), the method throws an Exception

For example, a call to getStatusImage(200) should return the string https://http.cat/200.jpg. And the call to 
getStatusImage(10000) should throw an exception because https://http.cat will return a 404 response code.

Test your program by calling it with different arguments.


### Task #2 - download the picture

**Write the HttpStatusImageDownloader class. This class must have one method:**

+ void downloadStatusImage(int code). It accepts a status code, and if there is a picture for this status code, it 
downloads this picture and saves it in the folder where the program was launched. If there is no picture, the method 
throws an Exception

Use the HttpStatusChecker class from the previous task to get the path to the image and determine the presence of the 
image itself.


### Task #3 - create a CLI

**Write the HttpImageStatusCli class. This class must have one method:**

+ void askStatus()

When this method is called, the program must:

+ ask the user for a status code (for example, Enter HTTP status code)
+ the user enters a status code (for example, 200) into the console
+ the program checks whether there is a picture for this status on the website https://http.cat, and if there is, 
it downloads this picture. If there is no image, the phrase There is not image for HTTP status "CODE" is output to the 
console (the status code entered by the user is substituted for "CODE")
+ if the user enters an incorrect number (for example, test), the program should display the phrase Please enter valid 
number

Use the HttpStatusImageDownloader class from the previous task.


### Task #4 - empty code on GitHub

**Create a new repository on GitHub. Add all the necessary files of your project there. Make sure that there are no 
redundant files in the repository.**

The result of the homework is a GitHub repository with three classes:

+ HttpStatusChecker 
+ HttpStatusImageDownloader 
+ HttpImageStatusCli

Additional classes can also be created as needed.