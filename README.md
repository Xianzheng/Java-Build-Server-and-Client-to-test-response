# Java-Build-Server-and-Client-to-test-response
This Assignment is about change Web Server to Poem Server, and the core principle remains unchanged. 
The client calls server and server gives the response. 
The interesting thing in this assignment is how to parse the poem file. In least assignment, 
we read the whole html and put it into the browser. This time we read poem file and parse it into each poem. 
And give client response according the client’s request. 
My idea is create class to store each poem’s information, and the poem class include title, id, author and content. \
Then, I create a linkedlist to store each class. I can take it out from the linked list when I want to give the client’s response. 
This time, we are just parsing file more specially than the last assignment, In part 2, we need to calculate the prime number 
according the range which client given.
We use socket to get the specifically number and use it to count.
