# FileZipper
# Huffman Coding Compression Project with Spring Boot

## Overview

This project is a Spring Boot web application that utilizes Huffman coding for file compression. Users can upload files, which are then compressed using the Huffman coding algorithm and made available for download as a ZIP file.

<img src="https://github.com/hemantkkashyap/FileZipper/assets/122628923/f3e07a97-5f69-4e0e-8db7-d17cbec8b7fd" alt="Project Image" width="400" height="250">
<img src="https://github.com/hemantkkashyap/FileZipper/assets/122628923/4ac6a3a8-31b8-4c49-8925-89a148d2482b" alt="Project Image" width="400" height="250">
<img src="https://github.com/hemantkkashyap/FileZipper/assets/122628923/76446196-e7f1-40bf-91ed-cf6d9564e959" alt="Project Image" width="400" height="250">

## Features

- **File Upload:**
  - Users can upload one or more files via a user-friendly interface.

- **Huffman Coding Compression:**
  - Files are compressed using the Huffman coding algorithm for efficient data compression.

- **ZIP File Download:**
  - Users can download the ZIP file containing the compressed files.

## System Requirements

- **Java:** Ensure you have Java installed. This project is developed and tested with Java 8 and later versions.

- **Maven :** Build tools are used to manage dependencies and build the project. Ensure you have Maven installed.

- **Web Browser:** For accessing the user interface. The project is tested with modern web browsers like Chrome, Firefox, and Safari.

## Project Structure

- **Main Application Class:**
  - `HelloApplication.java` - Main class annotated with `@SpringBootApplication` to bootstrap the Spring Boot application.

- **Controller Class:**
  - `FileZipperController.java` - Spring MVC controller handling file upload, compression, and download functionalities.

- **Huffman Coding:**
  - Utilizes a separate class or method for Huffman coding compression (`Huffman.java`).

- **Frontend:**
  - The interface using HTML & CSS and Thymeleaf for file upload and result display.

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/your-repository.git
   cd your-repository
2. Run the application:

   ```bash
   mvn spring-boot:run
3. Access the application in your web browser:
   ```bash
   http://localhost:8080

## Using the Docker Image

### Prerequisites
- [Docker](https://www.docker.com/get-started) installed on your machine.

### Pulling the Docker Image
To use the Docker image for this project, follow these steps:

1. Open a terminal.

2. Run the following command to pull the Docker image from Docker Hub:
   ```bash
   docker pull hemantk5/file-zipper-springboot
3. Check the list of locally available images to ensure the successful pull:
   ```bash
   docker images
4. Once you have pulled the Docker image, you can run the container using the following steps:
   ```bash
   # Adjust the port mapping if needed
   docker run -p 8080:8080 hemantk5/file-zipper-springboot

# Usage

1. Open the application in your web browser.
2. Upload one or more files using the provided file input form.
3. The files will be compressed using Huffman coding, and a ZIP file will be created.
4. Download the ZIP file containing the compressed files.

## Contributing

Contributions are welcome! If you'd like to contribute to the project, please follow these steps:

1. Fork the project.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push the changes to your branch (`git push origin feature/new-feature`).
5. Open a pull request.

## License

This project is licensed under the MIT License.
