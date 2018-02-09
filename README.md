# Android-App-for-e-notice-board

Provides common notices and notices for all branches distinctly,placement and sports information of the college.


Whenever the professor uploads the notice, all the studnets should get notified about the notice though a push notification.As soon as the app is installed from the google,each user is provided with a token from the Firebase cloud messaging.Thsi token represents the user and is registered in the remote database which in my case was a web hosting server that used phpMyAdmin(php and MySQL).The token along with the message is sent to the FCM(Firebase cloud messaging) which in turn pushes a notification to all the registered tokens.


The care of authentication part for the professors is also considered.The notices can be uploaded in any form like images,PDF's,spreadsheets or in text form.


While uploading the notices,the flags for students of different brances and years are set in the database.This is useful while downloading the notices from the server as the notices can be segregated in tabs of different branches and years.
The notices are uploaded and downloaded using php scripts.The messages are sent to the Firebase from the server using a php script.
There are two services which are created in the android studio namely FirebaseInstanceID Service and Firebase Messaging service.
