# CustomCacheForApiGetRequestsProject
Spring Boot application with created Custom Cache without using @Cache annotation of Spring framework. This cache is based on least recently used cache.
In this application I used tech listed below -:
1) Spring Boot for Spring Initializr.
2) Spring JPA for automated Database handeling.
3) Lombok for automated Getters and Setters.
4) Spring Web for the RestController.
5) Spring MySql Connector.

What does this application do ?
This application handles the rest requests like GET, POST, DELETE, PUT and serves with the Json data which is about a Blog. In which there are 4 fields :
i) blogId
ii) blogTitle
iii) blogDescription
iv) blogAuthorName

Also, Made improvement for the get requests. For Example if there are some blogs which are really famous and a lot of clients or people hit the endpoint for that blog than I implemented a Cache system using HashMap and Doubly LinkedList with a capacity which was 4 at the time i was testing it  and could be changed at runtime as per requirements with a slight modification.
What does the Cache does ?
If User asks for let say a blog having id 2 and that blog is not available at cache than we'll fetch that blog from the db and store it into the cache also.
Now if we request for the same blog again we see better performance as this time the response or the data we got was from cache.
Also If the capacity of the cache is 4 than we'll get most recent requested 4 blogs for the newly requested blog the least recent one will get evicted or removed from the cache and this new blog will take it's place in the cache for next request.

