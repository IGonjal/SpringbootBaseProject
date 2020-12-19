This project contains a springboot app that is used as a basis for any future Springboot Implementation

All the code of this project is under the terms of the software GPL v3 license
(https://www.gnu.org/licenses/gpl-3.0-standalone.html).

Author:
Ismael Gonjal

If you need to generate this image, in linux you just must be in the root of the project, and run the following (being 
"springboot-app" the name of the image)

```mvn clean install```
```docker build . -t springboot-app```


After the build process, if you want to run it, just execute the following command taking into account that if you 
changed the name "springboot-app" in the previous step, you should have to change it here too.

```docker run -p 8080:8080 springboot-app```

After this is working, you can send a Get by using postman or any other method to the app in the address:

```http://localhost:8080/example``` 

Also, the health endpoint is up and accessible under the address

```http://localhost:8080/actuator/healt``` 