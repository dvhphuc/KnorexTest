# Project Setup Guide

## Prerequisites
Ensure you have the following installed on your system:
- **JDK 17**: Download and install from [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or use OpenJDK.
- **Maven**: Make sure Apache Maven is installed and available in your system's PATH. You can download it from [Maven's official website](https://maven.apache.org/download.cgi).

## Steps to Run the Project

1. **Install JDK 17**  
   Ensure JDK 17 is installed and properly set up. You can check your Java version using:
   ```sh
   java -version

2. **Open pom.xml**
   Navigate to the project's root directory and open the pom.xml file to verify dependencies.

## Kindly Note
Since I have no experience with MongoDB and have only worked with RDBMS so far, I will skip question 4 in the test.
My implementation idea for the test:
- Create two classes responsible for reading XML content: one for reading from a file path and another for reading from a URL. These classes receive the data source path and return the XML content as a string.
- Create a parser class that takes an XML string as input and returns the target model. Each target XML tag has a specific method for handling it, ensuring a structured implementation.
- Create an exporter class responsible for converting the target model into a JSON string.
- The unit test will be skipped because lacking of time.
