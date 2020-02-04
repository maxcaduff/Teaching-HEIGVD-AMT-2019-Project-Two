# Teaching-HEIGVD-AMT-2019-Project-Two

## report

This repo implements 2 api made with spring boot, linked to a mysql container with separate users and databases. Each api has its own docker container, and a Traefik reverse proxy acts as a single entry point to access both apis. the whole pack is launchable via the "launch.sh" script, which runs maven for the 2 apis, moves the generated jars in the respective docker folders, and then uses docker compose to start the 4 containers. If launched separately, (not covered here) the database must be up before the other containers launches. This project also assumes that your docker ip is: 192.168.99.100.

The 2 spring boot projects were generated via swagger editor from an open api 3 specification, which is available in the respective src/main/ressources folders. The generated specifications are viewable at 192.168.99.100/api and 192.168.99.100/authApi

The first api handles users, they can connect by retrieving a JWT valid for 10 minutes, edit their infos and delete the account, to do some operations they must be logged. When they create an account, a random generated password is sent to the provided email, so it must be valid for users to connect. Nothing was implemented to delete the user in case the mail was invalid. The user can also reset his password, another mail is sent with the new random password. The emails are sent via a real email address to which spring boot connects. 


The second api is intended for a company which offers a set of activities in various places (all activities are available in all locations.)  
A user with a valid token from the first api can book activities in a location at a certain date for a given number of people, if he makes the same query again places will be added, only one booking per person/day/activity/location is created. Users can also get the list of their booked activities (previous or future), and delete some of their bookings.  
Admins (defined in the database script, no control from the api) can add, edit and delete activities and locations. Everyone, even not logged can consult the list of activities and locations, and a road is also implemented to get the places left for each activity on a certain day. In this api, the GET queries returning a lot of results are paginated and the api returns by default the first page, a page parameter can be used to retrieve more results.  
The admin and users authentication are implemented via an interceptor who performs checks on every protected road and returns an error code if the user is not allowed to access the ressource.

For the 2 apis the received parameters are checked and an error code is sent if any required parameter is missing or invalid.

The booking api has been covered with cucumber tests, checking all basic features offered by the api, as well as some invalid cases; almost every http code sent by the api has a corresponding test. The invalid parameters check being handled automatically by spring boot, it hasn't been extensively covered by the tests. To launch the tests, the booking api and the db must be up (e.g. with the launch script). To be certain of this, check the spec at 192.168.99.100/api. Then launch the tests.sh script in the main folder. Tests are meant to be launched right after the containers creation and only once, since they partly use generated ids, the only way to launch the tests fresh again is to wipe the booking database, re-launching the containers does this job, but relaunching the creation script on the db container is more efficient.
