# Demo Microservice

A simple completion OpenAI microservice  built with Spring Boot and Docker.

## Getting started

### Prerequisites
- Java Development Kit (JDK) 11 or higher installed
- Docker installed and running on your machine
- Basic knowledge of Java, Spring Boot, and Docker

To run the microservice, you need to have Docker installed on your machine. Then, follow these steps:

1. Open a terminal window and navigate to the root directory of the project.
2. Build the Docker image by running the following command: `docker build -t completion-chatgpt .`
3. Start the Docker container by running the following command: `docker run -p 8080:8080 completion-chatgpt`
4. Open a web browser and navigate to "http://localhost:8080/swagger-ui.html" to see the API documentation.
5. Test with curl request in postman
<img src="images/requestCurl.png">
<img src="images/responseCurl.png">

## API documentation

The API documentation is generated with Swagger. To see the documentation, open a web browser and navigate to "http://localhost:8080/swagger-ui.html".

## License

This microservice is licensed under the MIT License. See the LICENSE file for more information.