# Developer Tools
## Tools and Utilities that improves the Productivity of Developer


[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Please Note : It is just a study project to improve my knowlegde in Java Programming Language and Java Technologies.There are many problems,bugs ,design issues with Application.

DeveloperTools is Java Desktop Application that uses Javafx , intended to provide some common tools , utilities that helps the developer improve the productivity.
It has become some sort of framework to easly create tools and integrate in it.

Tools in the sense , 
String Manipulation [Convert Case , Append Words , Convert to other delimited ...etc]  
File Conversion [CSV ,JSON , XML .etc]
Java Utilites [Regx Tester , Decompiler , Compilier , IDE].
Source Code Generator
.etc

Above mentioned are not fully developed , it is under development only.


## Screenshots

![Alt text](screenshots/home.PNG?raw=true "Main Page")

![Alt text](screenshots/tools1.PNG?raw=true "Tools Page")

![Alt text](screenshots/tools2.PNG?raw=true "Tools Page")

![Alt text](screenshots/tools3.PNG?raw=true "Tools Page")


## Currently Working Features
- String Manipulation Tools


## Planned Features

- Adding more tools
- Adding plugin support to integrate third party javafx Application.

## Problems Facing

- It is using Java 16 , [But the code not even fully follow JDK 8 features .Need to upgrade to latest Java Standards]
- There are some design issues.
- Logging is not done.
- Unit Testing is Not Done.


## Tech

DeveloperTools uses many libraries and Frameworks, DeveloperTools is nothing without them.

- [Openjfx] - The project uses Javafx for GUI.
- [Apache Commons] - Helper Libraries
- [Jsoup] - Currently, not using in the project , it is required later.
- [PreferenceFX] - Currently, not using in the project , it is required later.
- [Hibernate] - In use with Embedded Derby to store Application Data.
- [FontawesomeFX] - For Icons
- [org.zeroturnaround.zt-zip] - Zipping and Unzipping
- [com.esotericsoftware.kryo] - Currently, not using in the project , it is required later.
- [com.github.oshi] - For Task Managers , to get System Information
- [MaterialFX] - Helper GUI Library 
- [org.quartz-scheduler.quartz] - Currently, not using in the project , it is required later.
- [Apache Derby Embedded] - To store data.
- [Boostrapcss3] - CSS Theme for GUI
- [English Dictionary] - https://www.bragitoff.com/2016/03/english-dictionary-in-csv-format/ && http://www.mso.anu.edu.au/~ralph/OPTED/
- [AsyncTask] - https://github.com/victorlaerte/jfx-asynctask
- [icon8.com] - Some icons are downloaded from this site.
- [freepic] - Some background images are taken from https://www.freepik.com/vectors/background
      


## Installation

DeveloperTool Requires Java 16 and Maven.



Run the below command to build the binary.
```sh
mvn install
```

For production environments...

```sh
npm install --production
NODE_ENV=production node app
```



## Development

Want to contribute? Great! Thank you for showing interest in this Project.


## License

Apache 2.0

**Free Software, Hell Yeah!**
